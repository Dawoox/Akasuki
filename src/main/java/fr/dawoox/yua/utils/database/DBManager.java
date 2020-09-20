package fr.dawoox.yua.utils.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DBManager {

    private static final MongoClient mongoClient = MongoClients.create("mongodb+srv://bob:motdepassedebob@acme.ypiul.gcp.mongodb.net/yua?retryWrites=true&w=majority");
    private static final MongoDatabase database = mongoClient.getDatabase("yua");
    public static MongoDatabase getDatabase() { return database; }
    public static boolean isLink() { if (database == null) { return false; } else { return true; } }

}
