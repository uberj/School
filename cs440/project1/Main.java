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
	public static OperationStatus ret = null; 
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
                dbs.getDb().put(null, key, data);
            } catch (DatabaseException e) {
                System.err.println("Caught DatabaseException during creation: ");
                e.printStackTrace();
            }
        }
		dbs.close();
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
		XMLFile xml = null;
        try {
            DatabaseEntry foundKey = new DatabaseEntry();
            DatabaseEntry foundData = new DatabaseEntry();
            DatabaseEntry sizeKey = new DatabaseEntry(sizeKeyb);
            secCursor = dbs.getSecDb().openSecondaryCursor(null, null);
            while (secCursor.getNext(sizeKey, foundData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
				xml = (XMLFile) binding.entryToObject(foundData);
				if (xml.getSize() != fileSize) {
					break;
				} 
                foundEntries.add(xml);
            }
        } catch (DatabaseException e) {
            System.err.println("Database Error: " + e.toString());
            e.printStackTrace();
        } catch (NullPointerException npe) { 
			System.err.println("Record not found.");
			System.exit(1); 
		} 

		dbs.close();
        return foundEntries;
    }


    public static XMLFile imdbPointQuery(String fileName) {
        XMLFile foundEntry = null;
        try {
            dbs.setup(dbName);
       } catch (DatabaseException e) {
           System.err.println("Caught Exception creating datatbase :");
           e.printStackTrace();
       }
        XMLFileBinding binding = new XMLFileBinding();
        Cursor cursor = null;
        try {
            DatabaseEntry foundData = new DatabaseEntry();
            DatabaseEntry nameKey = new DatabaseEntry(fileName.getBytes());
            dbs.getDb().get(null, nameKey, foundData, null);
			foundEntry =  (XMLFile) binding.entryToObject(foundData);
        } catch (DatabaseException e) {
            System.err.println("Database Error: " + e.toString());
            e.printStackTrace();
        } catch (NullPointerException npe) { 
			System.err.println("Record not found.");
			System.exit(1); 
		} 
		dbs.close();
        return foundEntry;
    }
/*
	public static ArrayList<XMLFile> imdbRangeQuery(String fileName1, String fileName2) { 
		ArrayList<XMLFile> foundEntries = new ArrayList<XMLFile>();
		try { 
			dbs.setup(dbName);
		} catch (DatabaseException e) { 
			System.err.println("Caught Exception creating database :");
			e.printStackTrace();
		}	
		XMLFileBinding binding = new XMLFileBinding();
		Cursor cursor = null;
		cursor = dbs.getDb().openCursor(null, null);
		ret = cursor.getSearchKeyRange(nameKey, foundData, LockMode.DEFAULT);
		xml = (XMLFile) binding.entryToObject(foundData);
//		if (ret == OperationStatus.SUCCESS && xml.get
		while (cursor.getNext(nameKey, foundData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
			
                foundEntries.add((XMLFile) binding.entryToObject(foundData));
//				if(
		}
				
	
		

	}
*/
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
            dbs.getDb().put(null, key, data);
            DatabaseEntry new_data = new DatabaseEntry();
            dbs.getDb().get(null, key, new_data, null);
			
            XMLFile newxml = (XMLFile) binding.entryToObject(new_data);
        } catch (DatabaseException e) {
            System.err.println("Caught DatabaseException during creation: ");
            e.printStackTrace();
        }         SecondaryCursor secCursor = null;
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
		DatabaseEntry key1 = new DatabaseEntry("dicks".getBytes());
        // bind stuff
        binding.objectToEntry(xml, data);

        try {
           // db.getDb().put(null, key, data);
            DatabaseEntry new_data = new DatabaseEntry();
            db.getDb().get(null, key1, new_data, null);

            XMLFile newxml = (XMLFile) binding.entryToObject(new_data);
        } catch (DatabaseException e) {
            System.err.println("Caught DatabaseException during creation: ");
            e.printStackTrace();
        } catch (NullPointerException npe) { 
			System.err.println("Record not found.");
			System.exit(1); 
		} 

    }


    public static void main(String[] args) {
        simpleTest();
//        simpleTest2();
/*
		if(args.length < 1) { 
			System.out.println("Insufficient args. . ERROR!");
			System.exit(1);
		}	
		switch(Integer.parseInt(args[0])) { 

		case 1: 
			System.out.println("Populating database with XML files. . ."); 
			populateDB();
			break;
		case 2:
			System.out.println("Performing point query on file " + args[1]);
			imdbPointQuery(args[1]); 		
			break;
		case 3:
			System.out.println("Performing range query over files fom " + args[1] + " - " + args[2]);
			// TODO: Add range query function 
			break;
		case 4:
			System.out.println("Performing point query on file size " + args[1]);	
			imdbPointQuery(args[1]);
			break;
		case 5:
			System.out.println("Performing range query over file size from " + args[1] + " - " + args[2]);
			// TODO: Add range query function
			break;
		case 6:
			System.out.println("Performing range query over file name and size from files " + args[1] + " - " + args[2] + " and size " + args[3] + " - "  + args[4]);

		default:
			System.out.println("Invalid query type.");
		}
*/
    }
}
