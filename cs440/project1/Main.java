import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import com.sleepycat.db.Cursor;
import com.sleepycat.db.DatabaseException;
import com.sleepycat.db.DatabaseEntry;
import com.sleepycat.db.LockMode;
import com.sleepycat.db.OperationStatus;
import com.sleepycat.db.SecondaryCursor;


public class Main {
    // Fi
    //
    public static String dbName = "imdb";
    public static XMLFileBinding binding = new XMLFileBinding();
    public static Dbs dbs = new Dbs();

    public static byte[] getSizeByteArray(long sizeKey) {
        byte b[] = new byte[8];
        ByteBuffer buf = ByteBuffer.wrap(b);
        buf.putLong(sizeKey);
        return b;
    }


    public static void populateDB() {
        File rootPath = new File("/scratch/cs440/imdb");
        ArrayList<File> paths = new ArrayList<File>();
        paths = FileData.walkPath(rootPath);
        try {
            dbs.setup(dbName);
        } catch (DatabaseException e){
            System.err.println("Caught Exception creating datatbase :");
            e.printStackTrace();
        }
        XMLFile xml = null;

        DatabaseEntry key = null;
        DatabaseEntry data = null;
        XMLFile found = null;

        for(File path:paths) {
            try {
                xml = new XMLFile(path);
                key = new DatabaseEntry(xml.getName().getBytes());
                data = new DatabaseEntry();
                binding.objectToEntry(xml, data);
                dbs.getDB().put(null, key, data);
            } catch (DatabaseException e) {
                System.err.println("Caught DatabaseException during creation: ");
                e.printStackTrace();
            }
        }
    }


    public static ArrayList<XMLFile> imdbPointQuery(long fileSize) {
        ArrayList<XMLFile> foundEntries = new ArrayList<XMLFile>();
        byte sizeKeyb[] = getSizeByteArray(fileSize);
        try {
            dbs.setup(dbName);
       } catch (DatabaseException e){
           System.err.println("Caught Exception creating datatbase :");
           e.printStackTrace();
       }
        XMLFileBinding binding = new XMLFileBinding();
        SecondaryCursor secCursor = null;
        try {
            DatabaseEntry foundKey = new DatabaseEntry();
            DatabaseEntry foundData = new DatabaseEntry();
            DatabaseEntry sizeKey = new DatabaseEntry(sizeKeyb);

            secCursor = dbs.getSecDb().openSecondaryCursor(null, null);
            while (secCursor.getNext(sizeKey, foundData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                foundEntries.add((XMLFile) binding.entryToObject(foundData));
            }
        } catch (DatabaseException e) {
            System.err.println("Database Error: " + e.toString());
            e.printStackTrace();
        }
        return foundEntries;
    }


    public static ArrayList<XMLFile> imdbPointQuery(String fileName) {
        ArrayList<XMLFile> foundEntries = new ArrayList<XMLFile>();
        try {
            dbs.setup(dbName);
       } catch (DatabaseException e){
           System.err.println("Caught Exception creating datatbase :");
           e.printStackTrace();
       }
        XMLFileBinding binding = new XMLFileBinding();
        Cursor cursor = null;
        try {
            DatabaseEntry foundKey = new DatabaseEntry();
            DatabaseEntry foundData = new DatabaseEntry();
            DatabaseEntry nameKey = new DatabaseEntry(fileName.getBytes());

            cursor = dbs.getSecDb().openCursor(null, null);
            while (cursor.getNext(nameKey, foundData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                foundEntries.add((XMLFile) binding.entryToObject(foundData));
            }
        } catch (DatabaseException e) {
            System.err.println("Database Error: " + e.toString());
            e.printStackTrace();
        }
        return foundEntries;
    }


    public static void simpleTest2() {

        try {
            dbs.setup(dbName);
       } catch (DatabaseException e){
           System.err.println("Caught Exception creating datatbase :");
           e.printStackTrace();
       }
        XMLFileBinding binding = new XMLFileBinding();

        // create xml file object
        XMLFile xml = new XMLFile("asdf", 123, "asdfdf");

        // create db entry
        DatabaseEntry key = new DatabaseEntry(xml.getName().getBytes());
        DatabaseEntry data = new DatabaseEntry();
        // bind stuff
        binding.objectToEntry(xml, data);

        try {
            dbs.getDB().put(null, key, data);
            DatabaseEntry new_data = new DatabaseEntry();
            dbs.getDB().get(null, key, new_data, null);

            XMLFile newxml = (XMLFile) binding.entryToObject(new_data);
        } catch (DatabaseException e) {
            System.err.println("Caught DatabaseException during creation: ");
            e.printStackTrace();
        }
        SecondaryCursor secCursor = null;
        try {
            DatabaseEntry foundKey = new DatabaseEntry();
            DatabaseEntry foundData = new DatabaseEntry();
            DatabaseEntry sizeKey = new DatabaseEntry(xml.getSizeByteArray());
            secCursor = dbs.getSecDb().openSecondaryCursor(null, null);
            OperationStatus ret = secCursor.getSearchKey(sizeKey, foundKey, foundData, LockMode.DEFAULT);
            XMLFile newxml2 = (XMLFile) binding.entryToObject(foundData);
        } catch (DatabaseException e) {
            System.err.println("Database Error: " + e.toString());
            e.printStackTrace();
        }
    }


    public static void simpleTest() {
        BaseDatabase db = new BaseDatabase();
        try {
            db.setup(dbName);
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


    public static void main(String[] args) {
        simpleTest();
        simpleTest2();
    }
}
