import com.sleepycat.db.Database;
import com.sleepycat.db.DatabaseConfig;
import com.sleepycat.db.DatabaseException;
import com.sleepycat.db.DatabaseType;
import com.sleepycat.db.SecondaryDatabase;
import com.sleepycat.db.SecondaryConfig;

import java.io.FileNotFoundException;

public class BaseDatabase {

    private Database imdb = null;
    private String imdb_name = "imdb";

    public BaseDatabase() {}

    public void setup(String dbNames)
        throws DatabaseException {

        DatabaseConfig dbConfig = new DatabaseConfig();

        dbConfig.setErrorStream(System.err);
        dbConfig.setErrorPrefix("Databases");
        dbConfig.setType(DatabaseType.BTREE);
        dbConfig.setAllowCreate(true);

        try {
            imdb_name = dbNames + "/" + imdb_name;
            System.out.println("Database at: " + imdb_name);
            imdb = new Database(imdb_name, null, dbConfig);
        } catch(FileNotFoundException notFound) {
            System.err.println(" HI Databases: " + notFound.toString());
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
		
		SecondaryDatabase sizeDB = new SecondaryDatabase(secName, null, imdb, secConfig)
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
