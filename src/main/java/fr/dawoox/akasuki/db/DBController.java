package fr.dawoox.akasuki.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import fr.dawoox.akasuki.data.Config;

/**
 * Database controller
 * @author Dawoox
 * @version 2.0.0
 */
public class DBController {

    private static final String USER = Config.DB_USER;
    private static final String PASSWORD = Config.DB_PASSWD;
    private static final String IP = Config.DB_IP;
    private static final boolean RETRY = Config.DB_RETRY;
    private static final String MAIN = Config.DB_MAIN;

    private static final MongoClient mongoClient = MongoClients.create("mongodb+srv://" + USER + ":" + PASSWORD + "@" + IP + "/" + MAIN + "?retryWrites=" + RETRY + "&w=majority");
    private static final MongoDatabase database = mongoClient.getDatabase(MAIN);

    /**
     * Get the MongoDB Database Object
     * @return MongoDatabase
     * Return MongoDatabase link to the database
     */
    public static MongoDatabase getDatabase() { return database; }

}
