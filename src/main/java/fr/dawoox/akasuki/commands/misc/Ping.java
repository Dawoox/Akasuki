package fr.dawoox.akasuki.commands.misc;

import fr.dawoox.akasuki.utils.Command;
import fr.dawoox.akasuki.utils.LogsManager;
import fr.dawoox.akasuki.utils.TimeManager;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;

public class Ping {

    public static void reg(Map<String, Command> commands){
        commands.put("ping", event -> {
            Instant messageInstant = event.getMessage().getTimestamp();
            Objects.requireNonNull(event.getMessage().getChannel().block()).createMessage("Pinging...");
            Instant botInstant = Instant.now();
            String reply = "La latence de Yua est de `" + TimeManager.diffInMillis(messageInstant, botInstant) + "ms` actuellement" +
                    "\nUn problème de latence ? Venez nous l'indiquer sur notre Discord de support";
            Objects.requireNonNull(Objects.requireNonNull(event.getMessage().getChannel().block()).getLastMessage().block()).delete("Yua auto ping message deleted");
            Objects.requireNonNull(event.getMessage().getChannel().block()).createMessage(reply).block();
            LogsManager.logAction("Ping", Objects.requireNonNull(event.getMessage().getAuthorAsMember().block()), Ping.class);
        });
    }

}
