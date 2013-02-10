import com.sleepycat.db.Database;
import com.sleepycat.db.DatabaseConfig;
import com.sleepycat.db.DatabaseException;
import com.sleepycat.db.DatabaseType;
import com.sleepycat.db.SecondaryDatabase;
import com.sleepycat.db.SecondaryConfig;

import java.io.FileNotFoundException;

public class Dbs {

    private Database imdb = null;
    private String imdbName = "imdb";
    private SecondaryDatabase sizeDb = null;
    private SizeKeyCreator = null; 
    public Dbs() {}

    public void setup(String dbNames) throws DatabaseException {
        DatabaseConfig dbConfig = new DatabaseConfig();
        SecondaryCfonig  secdbConfig = new SecondaryConfig();
        dbConfig.setErrorStream(System.err);
        dbConfig.setErrorPrefix("Databases");
        dbConfig.setType(DatabaseType.BTREE);
        dbConfig.setAllowCreate(true);
        secdbConfig.setErrorStream(System.err);
        secdbConfig.setErrorPrefix("Secondary");
        secdbConfig.setType(DatabaseType.BTREE);
        secdbConfig.setAllowCreate(true);

        try {
            imdbName = dbNames + "/" + imdbName;
            System.out.println("Database at: " + imdbName);
            imdb = new Database(imdbName, null, dbConfig);
        } catch(FileNotFoundException notFound) {
            System.err.println(" HI Databases: " + notFound.toString());
            notFound.printStackTrace();
            System.exit(-1);
        }



    }

	public void createSecondary(String secName) {
		SecondaryConfig secConfig = new SecondaryConfig();
		secConfig.setAllowCreate(true);
		secConfig.setType(DatabaseType.BTREE);
		secConfig.setSortedDuplicates(true);
		SizeKeyCreator secKey = new SizeKeyCreator();
		secConfig.setKeyCreator(secKey);
		SecondaryDatabase sizeDB = new SecondaryDatabase(secName, null, imdb, secConfig);
        return sizeDb
	}


    public Database getDB() {
        return imdb;
    }

    public void close() {
        try {
            if (imdb != null) {
                imdb.close();
            }

        } catch(DatabaseException dbe) {
            System.err.println("Error closing Databases: " + dbe.toString());
            System.exit(-1);
        }
    }
}
