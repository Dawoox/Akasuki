package fr.dawoox.akasuki.utils.database;

import com.mongodb.client.MongoCollection;

import java.util.Objects;

public class DBUtils {

    public static String getRandomLink(String db) {
        MongoCollection collection = DBController.getDatabase().getCollection(db);

        int R = (int) Math.floor(Math.random() * collection.countDocuments());
        return Objects.requireNonNull(collection.find().limit(1).skip(R).first()).toString()
                .substring(45, Objects.requireNonNull(collection.find().limit(1).skip(R).first()).toString().length() - 2);
    }

}
