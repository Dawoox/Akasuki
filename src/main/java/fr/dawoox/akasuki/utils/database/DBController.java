package fr.dawoox.akasuki.utils.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.dawoox.akasuki.utils.ConfigReader;

import java.util.Objects;

/**
 * Database controller
 * @author Dawoox
 * @version 2.0.0
 */
public class DBController {

    private static final String USER = ConfigReader.getEntry("db_user");
    private static final String PASSWORD = ConfigReader.getEntry("db_passwd");
    private static final String IP = ConfigReader.getEntry("db_ip");
    private static final String RETRY = ConfigReader.getEntry("db_retry");
    private static final String MAIN = ConfigReader.getEntry("db_main");

    private static final MongoClient mongoClient = MongoClients.create("mongodb+srv://" + USER + ":" + PASSWORD + "@" + IP + "/" + MAIN + "?retryWrites=" + RETRY + "&w=majority");
    private static final MongoDatabase database = mongoClient.getDatabase(MAIN);

    /**
     * Get the MongoDB Database Object
     * @return MongoDatabase
     * Return MongoDatabase link to the database
     */
    public static MongoDatabase getDatabase() { return database; }

}
