package fr.dawoox.akasuki.features;

import discord4j.core.event.domain.message.MessageCreateEvent;
import fr.dawoox.akasuki.utils.API.DiscordPhishingLinksAPI;
import fr.dawoox.akasuki.utils.embeds.DiscordWebhook;

import java.awt.*;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class containing the anti-scam links module of the auto-mod
 * @author Dawoox
 * @version 1.2.0
 */
public class ScamOMeter {

    private static final String URL_REGEX = "(http(s)?:\\/\\/.)?(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";
    private static final Pattern p = Pattern.compile(URL_REGEX);

    private static String[] domains = DiscordPhishingLinksAPI.requestDomains().domains;
    private static Instant lastLookupTime = Instant.now();

    /**
     * Analyse a Message to search for scam links
     * @param event The event which trigger the auto-mod
     * @return true if the message is a scam, false if not
     */
    public static boolean analyse(MessageCreateEvent event) {
        //Search for a link
        final Matcher m = p.matcher(event.getMessage().getContent());

        if (!m.find()) return false;

        String matchContent = m.toMatchResult().group().intern();

        //If the domains list is older than 6 hours, refresh it
        if (Instant.now().minusSeconds(21600).isAfter(lastLookupTime)) {
            domains = DiscordPhishingLinksAPI.requestDomains().domains;
            lastLookupTime = Instant.now();
        }

        if (Arrays.stream(domains).anyMatch(matchContent::equals)) {
            try {
                sendAuditLog(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        return false;
    }

    /**
     * Log the scam message for further investigations
     * @param event The event which trigger the auto-mod
     * @throws IOException Send an exception if it cannot send the webhook
     */
    private static void sendAuditLog(MessageCreateEvent event) throws IOException {
        String messageUrl = "https://discord.com/channels/"+event.getGuildId().get().asString()+"/"+event.getMessage().getChannel().block().getId().asString()
            +"/"+event.getMessage().getId().asString();
        DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/942455489088856094/awodYXyDDQm3TRQM5GjRcHONuusfn-R2OrrpRBcte9MfCwmDE_8IYxJ8gRhRIkTJai9D");
        webhook.setAvatarUrl("https://png.pngtree.com/element_our/png_detail/20180913/creative-abstract-open-lock-logo.-security-logo-concept.-png_94971.jpg");
        webhook.setUsername("Akasuki Logs");
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle("Security Audit | Scam Link Detected")
                .setAuthor("User "+event.getMember().get().getUsername()+" ("+event.getMember().get().getId().asString()+")", messageUrl, null)
                .setFooter("Akasuki Auto-Mod | triggered at "+ Date.from(Instant.now()), null)
                .setDescription(event.getMessage().getContent())
                .setColor(Color.RED));
        webhook.execute();
    }
}
