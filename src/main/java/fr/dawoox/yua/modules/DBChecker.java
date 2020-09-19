package fr.dawoox.yua.modules;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import fr.dawoox.yua.utils.database.DBManager;
import org.apache.commons.logging.LogFactory;

public class DBChecker {
    /*
    final static MongoCollection collection = DBManager.getDatabase().getCollection("modules");

    public static boolean check(String module){
        BasicDBObject query = new BasicDBObject("guildId", "714164965539446785");
        DBCursor cursor = (DBCursor) collection.find(query);

        DBObject jo = cursor.one();
        LogFactory.getLog(DBChecker.class).info(cursor.one().get("gifs"));

        return false;
    }
*/
}
