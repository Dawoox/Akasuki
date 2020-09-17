package fr.dawoox.yua.commands.social;

import com.mongodb.client.MongoCollection;
import fr.dawoox.yua.utils.Command;
import fr.dawoox.yua.utils.database.DBManager;

import java.util.Map;

public class Marry {

    public static void reg(Map<String, Command> commands){
        commands.put("hug", event -> {
            MongoCollection collection = DBManager.getDatabase().getCollection("marry");

            if (event.getMessage().getUserMentionIds().isEmpty()){
                event.getMessage().getChannel().block().createMessage("Vous devez vous marier avec une personne, tout seul ça ne fonctionne pas");
                return;
            }
        });
    }

}
