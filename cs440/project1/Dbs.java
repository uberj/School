import com.sleepycat.db.Database;
import com.sleepycat.db.DatabaseConfig;
import com.sleepycat.db.DatabaseException;
import com.sleepycat.db.DatabaseType;
import com.sleepycat.db.SecondaryDatabase;
import com.sleepycat.db.SecondaryConfig;

import java.util.Comparator;
import java.io.FileNotFoundException;

public class Dbs {

    private Database imdb = null;
    private String textName = "textDB";
    private String imdbName = "primary";
    private String secName = "secondary";
	private String textName = "text";
    private SecondaryDatabase textdb = null;
    private SecondaryDatabase sizeDb = null;
	private SecondaryDatabase textDb = null;
    private XMLFileBinding dbBind = null;
    private SizeKeyCreator secKey = null;
	private IndexComparator indexCmp = new IndexComparator();
    private TextIndexKeyCreator textKey = null;
    public Dbs() {}

    public void setup(String dbNames) throws DatabaseException {
        dbBind = new XMLFileBinding();
        secKey = new SizeKeyCreator(dbBind);
		textKey = new TextIndexKeyCreator(dbBind);
        DatabaseConfig dbConfig = new DatabaseConfig();
        SecondaryConfig secDbConfig = new SecondaryConfig();
		SecondaryConfig textDbConfig = new SecondaryConfig();
        textKey = new TextIndexKeyCreator(dbBind);
        DatabaseConfig dbConfig = new DatabaseConfig();
        SecondaryConfig secDbConfig = new SecondaryConfig();
        SecondaryConfig textConfig = new SecondaryConfig();
        
        dbConfig.setErrorStream(System.err);
        dbConfig.setErrorPrefix("Databases");
        dbConfig.setType(DatabaseType.BTREE);
        dbConfig.setAllowCreate(true);
        dbConfig.setTransactional(false);
        dbConfig.setCacheSize(10000);
		dbConfig.setBtreeComparator(indexCmp);

        secDbConfig.setErrorStream(System.err);
        secDbConfig.setErrorPrefix("Secondary");
        secDbConfig.setKeyCreator(secKey);
        secDbConfig.setType(DatabaseType.BTREE);
        secDbConfig.setSortedDuplicates(true);
        secDbConfig.setAllowPopulate(true);
        secDbConfig.setAllowCreate(true);
        secDbConfig.setTransactional(false);
        secDbConfig.setCacheSize(10000);
		secDbConfig.setBtreeComparator(indexCmp);

        textDbConfig.setErrorStream(System.err);
        textDbConfig.setErrorPrefix("textdb");
        textDbConfig.setMultiKeyCreator(textKey);
        textDbConfig.setType(DatabaseType.BTREE);
        textDbConfig.setSortedDuplicates(true);
        textDbConfig.setAllowPopulate(true); 
        textDbConfig.setAllowCreate(true);
        textDbConfig.setTransactional(false);
        textDbConfig.setCacheSize(10000);


        textConfig.setErrorStream(System.err);
        textConfig.setErrorPrefix("textIndex");
        textConfig.setMultiKeyCreator(textKey);
        textConfig.setSortedDuplicates(true);
        textConfig.setType(DatabaseType.BTREE);
        textConfig.setAllowPopulate(true);
        textConfig.setAllowCreate(true);
        textConfig.setTransactional(false);
        textConfig.setCacheSize(10000);


        try {
            imdbName = dbNames + "/" + imdbName;
            System.out.println("Database at: " + imdbName);
            imdb = new Database(imdbName, null, dbConfig);
        } catch(FileNotFoundException notFound) {
            System.err.println("Error creating primary database: " + notFound.toString());
            notFound.printStackTrace();
            System.exit(-1);
        }

        try {
            secName = dbNames + "/" + secName;
            sizeDb = new SecondaryDatabase(secName, secName, imdb, secDbConfig);
        } catch(FileNotFoundException e) {
            System.err.println("Error creating FileSize database: " + e.toString());
            e.printStackTrace();
            System.exit(-1);
        }

        try {
            textName = dbNames + "/" + textName;
            System.out.println("Database at: " + textName);
            textdb = new SecondaryDatabase(textName, textName, imdb, textConfig);
        } catch(FileNotFoundException notFound) {
            System.err.println("Error in creating FullText database: " + notFound.toString());
            notFound.printStackTrace();
            System.exit(-1);
        }

		try {
			textName = dbNames + "/" + textName;
			textDb = new SecondaryDatabase(textName, textName, imdb, textDbConfig);
		} catch(FileNotFoundException e) {
			System.err.println("Error in TextDB creation :" + e.toString());
			e.printStackTrace();
		}
    }

    public Database getDb() {
        return imdb;
    }

    public SecondaryDatabase getSecDb() {
        return sizeDb;
    }

	public SecondaryDatabase getTextDb() {
		return textDb;
	}
    public SecondaryDatabase getTextDb() {
        return textdb;
    }

    public void close() {

        try {
			if (textDb != null) {
				textDb.close();
			}

			if (sizeDb != null) { 
			if (sizeDb != null) {
				sizeDb.close();
			}
            if (textdb != null) {
                textdb.close();
            }
            if (imdb != null) {
                imdb.close();
            }

        } catch(DatabaseException dbe) {
            System.err.println("Error closing Databases: " + dbe.toString());
            System.exit(-1);
        }
    }
}
