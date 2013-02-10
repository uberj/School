import com.sleepycat.db.SecondaryKeyCreator;
import com.sleepycat.db.DatabaseEntry;
import com.sleepycat.db.DatabaseException;
import com.sleepycat.db.SecondaryDatabase;
import java.io.IOException;

import java.lang.Long;

public class SizeKeyCreator implements SecondaryKeyCreator {

    private XMLFileBinding theBinding;

    public SizeKeyCreator(XMLFileBinding theBinding1) {
        theBinding = theBinding1;
    }

    public boolean createSecondaryKey(SecondaryDatabase secDb,
        DatabaseEntry keyEntry,
        DatabaseEntry dataEntry,
        DatabaseEntry resultEntry) {
        XMLFile xml = (XMLFile) theBinding.entryToObject(dataEntry);
        resultEntry.setData(xml.getSizeByteArray());
        return true;
    }
}
