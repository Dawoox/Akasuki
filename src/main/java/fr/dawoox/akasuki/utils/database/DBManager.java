package fr.dawoox.akasuki.utils.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.dawoox.akasuki.utils.ConfigReader;

import java.util.Objects;

public class DBManager {

    private static final String USER = ConfigReader.getEntry("db_user");
    private static final String PASSWORD = ConfigReader.getEntry("db_passwd");
    private static final String IP = ConfigReader.getEntry("db_ip");
    private static final String RETRY = ConfigReader.getEntry("db_retry");
    private static final String MAIN = ConfigReader.getEntry("db_main");

    private static final MongoClient mongoClient = MongoClients.create("mongodb+srv://" + USER + ":" + PASSWORD + "@" + IP + "/" + MAIN + "?retryWrites=" + RETRY + "&w=majority");
    private static final MongoDatabase database = mongoClient.getDatabase(MAIN);
    public static MongoDatabase getDatabase() { return database; }
    public static boolean isLink() { if (database == null) { return false; } else { return true; } }

    public static String getRandomLink(String db) {
        MongoCollection collection = DBManager.getDatabase().getCollection(db);

        int R = (int) Math.floor(Math.random() * collection.countDocuments());
        String randomLink = Objects.requireNonNull(collection.find().limit(1).skip(R).first()).toString()
                .substring(45, Objects.requireNonNull(collection.find().limit(1).skip(R).first()).toString().length() - 2);

        return randomLink;
    }

}
