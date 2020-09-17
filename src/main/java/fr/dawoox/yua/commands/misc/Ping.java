package fr.dawoox.yua.commands.misc;

import fr.dawoox.yua.utils.Command;
import fr.dawoox.yua.utils.LogsManager;
import fr.dawoox.yua.utils.TimeManager;

import java.time.Instant;
import java.util.Map;

public class Ping {

    public static void reg(Map<String, Command> commands){
        commands.put("ping", event -> {
            Instant messageInstant = event.getMessage().getTimestamp();
            event.getMessage().getChannel().block().createMessage("Pinging...");
            Instant botInstant = Instant.now();
            String reply = "La latence de Yua est de `" + TimeManager.diffInMillis(messageInstant, botInstant) + "ms` actuellement" +
                    "\nUn problème de latence ? Venez nous l'indiquer sur notre Discord de support";
            event.getMessage().getChannel().block().getLastMessage().block().delete("Yua auto ping message deleted");
            event.getMessage().getChannel().block().createMessage(reply).block();
            LogsManager.logAction("Ping", event.getMessage().getAuthorAsMember().block(), Ping.class);
        });
    }

}
