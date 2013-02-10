import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.db.SecondaryKeyCreator;
import com.sleepycat.db.DatabaseEntry;
import com.sleepycat.db.DatabaseException;
import com.sleepycat.db.SecondaryDatabase;
import java.io.IOException;

public class SizeKeyCreator implements SecondaryKeyCreator {

    private TupleBinding theBinding;

    public FullNameKeyCreator(TupleBinding theBinding1) {
        theBinding = theBinding1;
    }
    public boolean createSecondaryKey(SecondaryDatabase secDb,
            DatabaseEntry keyEntry,
            DatabaseEntry dataEntry,
            DatabaseEntry resultEntry) {
    try {
        // TODO
    } catch (IOException willNeverOccur) {}
        return true;
    }
}
