package fr.dawoox.yua.utils.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import fr.dawoox.yua.Yua;

public class DBManager {

    private static final String[] args= Yua.getArgs();
    private static final String USER = args[0];
    private static final String PASSWORD = args[1];

    private static final MongoClient mongoClient = MongoClients.create("mongodb+srv://" + USER + ":" + PASSWORD + "@acme.ypiul.gcp.mongodb.net/yua?retryWrites=true&w=majority");
    private static final MongoDatabase database = mongoClient.getDatabase("yua");
    public static MongoDatabase getDatabase() { return database; }
    public static boolean isLink() { if (database == null) { return false; } else { return true; } }

}
