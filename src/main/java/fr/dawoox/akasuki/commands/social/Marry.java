package fr.dawoox.akasuki.commands.social;

import com.mongodb.client.MongoCollection;
import discord4j.core.object.entity.Member;
import fr.dawoox.akasuki.utils.Command;
import fr.dawoox.akasuki.utils.database.DBManager;
import org.bson.Document;

import java.util.Map;

public class Marry {

    public static void reg(Map<String, Command> commands){
        commands.put("marry", event -> {
            MongoCollection collection = DBManager.getDatabase().getCollection("marry");

            if (event.getMessage().getUserMentionIds().isEmpty()){
                event.getMessage().getChannel().block().createMessage("Vous devez vous marier avec une personne, tout seul ça ne fonctionne pas").block();
                return;
            }

            Member sender = event.getMessage().getAuthorAsMember().block();
            Member target = event.getMessage().getUserMentions().blockFirst().asMember(event.getGuildId().get()).block();



            Document doc = new Document("member1", sender.getId().toString());
            doc.put("member2", target.getId().toString());
            collection.insertOne(doc);
            event.getMessage().getChannel().block().createMessage("Tadaa tu est marié(e) avec " + target.getUsername()).block();
        });
    }

}
