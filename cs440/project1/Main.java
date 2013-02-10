import java.io.*;
import java.util.ArrayList;
import com.sleepycat.db.DatabaseException;
import com.sleepycat.db.DatabaseEntry;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Dbs dbs = new Dbs();
        String dbName = "imdb";

        File rootPath = new File("/scratch/cs440/imdb");
        ArrayList<File> paths = new ArrayList<File>();
        paths = FileData.walkPath(rootPath);
        simpleTest();
    }

    public static void simpleTest() {
        BaseDatabase db = new BaseDatabase();
        try {
            db.setup("imdb");
        } catch (DatabaseException e) {
            System.err.println("Caught DatabaseException during setup: ");
            e.printStackTrace();
        }
        // create binding
        XMLFileBinding binding = new XMLFileBinding();

        // create xml file object
        XMLFile xml = new XMLFile("asdf", 123, "asdfdf");

        // create db entry
        DatabaseEntry key = new DatabaseEntry(xml.getName().getBytes());
        DatabaseEntry data = new DatabaseEntry();

        // bind stuff
        binding.objectToEntry(xml, data);

        try {
            db.getDB().put(null, key, data);
            DatabaseEntry new_data = new DatabaseEntry();
            db.getDB().get(null, key, new_data, null);

            XMLFile newxml = (XMLFile) binding.entryToObject(new_data);
        } catch (DatabaseException e) {
            System.err.println("Caught DatabaseException during creation: ");
            e.printStackTrace();
        }
    }

}
