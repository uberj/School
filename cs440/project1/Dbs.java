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
    private String imdbName = "primary";
    private String secName = "secondary";
    private SecondaryDatabase sizeDb = null;
    private XMLFileBinding dbBind = null;
    private SizeKeyCreator secKey = null;
    public Dbs() {}

    public void setup(String dbNames) throws DatabaseException {
        dbBind = new XMLFileBinding();
        secKey = new SizeKeyCreator(dbBind);
        DatabaseConfig dbConfig = new DatabaseConfig();
        SecondaryConfig  secDbConfig = new SecondaryConfig();
        dbConfig.setErrorStream(System.err);
        dbConfig.setErrorPrefix("Databases");
        dbConfig.setType(DatabaseType.BTREE);
        dbConfig.setAllowCreate(true);
        dbConfig.setTransactional(false);
        dbConfig.setCacheSize(10000);

        secDbConfig.setErrorStream(System.err);
        secDbConfig.setErrorPrefix("Secondary");
        secDbConfig.setKeyCreator(secKey);
        secDbConfig.setType(DatabaseType.BTREE);
        secDbConfig.setSortedDuplicates(true);
        secDbConfig.setAllowPopulate(true); 
        secDbConfig.setAllowCreate(true);
        secDbConfig.setTransactional(false);
        secDbConfig.setCacheSize(10000);

        try {
            imdbName = dbNames + "/" + imdbName;
            System.out.println("Database at: " + imdbName);
            imdb = new Database(imdbName, null, dbConfig);
        } catch(FileNotFoundException notFound) {
            System.err.println(" HI Databases: " + notFound.toString());
            notFound.printStackTrace();
            System.exit(-1);
        }

        try {
            secName = dbNames + "/" + secName;
            sizeDb = new SecondaryDatabase(secName, secName, imdb, secDbConfig);
        } catch(FileNotFoundException e) {
            System.err.println(" Error in Secondary creation : " + e.toString());
            e.printStackTrace();
        }
    }

    public Database getDb() {
        return imdb;
    }

    public SecondaryDatabase getSecDb() {
        return sizeDb;
    }

    public void close() {

        try {
			if (sizeDb != null) { 
				sizeDb.close();
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
