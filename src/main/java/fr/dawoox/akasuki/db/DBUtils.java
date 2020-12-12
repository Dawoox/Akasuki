package fr.dawoox.akasuki.db;

import com.mongodb.client.MongoCollection;

import java.util.Objects;

/**
 * Wrapper class for interaction with Database
 * @author Dawoox
 * @version 1.0.0
 */
public class DBUtils {

    /**
     * Get a random link in a collection
     * @param db
     * String with collection name
     * @return String
     * String with the random link
     * @since 1.0.0
     */
    public static String getRandomLink(String db) {
        MongoCollection collection = DBController.getDatabase().getCollection(db);

        int R = (int) Math.floor(Math.random() * collection.countDocuments());
        return Objects.requireNonNull(collection.find().limit(1).skip(R).first()).toString()
                .substring(45, Objects.requireNonNull(collection.find().limit(1).skip(R).first()).toString().length() - 2);
    }

}
