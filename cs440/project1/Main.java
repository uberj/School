import java.io.*;
import com.sleepycat.db.DatabaseException;
import com.sleepycat.db.DatabaseEntry;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        BaseDatabase db = new BaseDatabase();
        try {
            db.setup("imdb");
        } catch (DatabaseException e) {
            System.out.println("Caught DatabaseException during setup: ");
            System.out.println(e);
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
        }
    }

}
