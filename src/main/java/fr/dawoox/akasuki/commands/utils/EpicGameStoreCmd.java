package fr.dawoox.akasuki.commands.utils;

import com.sun.tools.javac.util.List;
import discord4j.rest.util.Color;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.core.command.CommandCategory;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.Context;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public class EpicGameStoreCmd extends BaseCmd {

    public EpicGameStoreCmd() {
        super(CommandCategory.UTILS, CommandPermission.USER, List.of("egs", "epicgamestore"));
    }

    @Override
    public void execute(Context context) {
        ArrayList<JSONObject> gameFree = new ArrayList<>();
        ArrayList<JSONObject> gameSoonFree = new ArrayList<>();

        try {
            URL url = new URL("https://store-site-backend-static.ak.epicgames.com/freeGamesPromotions?locale=FR&country=FR&allowCountries=FR");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();

                JSONObject data_obj = (JSONObject) new JSONParser().parse(inline);

                JSONObject obj1 = (JSONObject) data_obj.get("data");
                JSONObject obj2 = (JSONObject) obj1.get("Catalog");
                JSONObject obj3 = (JSONObject) obj2.get("searchStore");
                JSONArray arr = (JSONArray) obj3.get("elements");

                for (int i = 0; i < arr.size(); i++) {
                    JSONObject new_obj = (JSONObject) arr.get(i);
                    JSONObject promotions = (JSONObject) new_obj.get("promotions");

                    if (promotions != null){
                        JSONArray promotionalOffers = (JSONArray) promotions.get("promotionalOffers");

                        if (!promotionalOffers.isEmpty()) {
                            JSONObject price = (JSONObject) new_obj.get("price");
                            JSONObject totalPrice = (JSONObject) price.get("totalPrice");

                            if (totalPrice.get("discountPrice").toString().equalsIgnoreCase("0")) {
                                gameFree.add(new_obj);
                            }
                        } else {
                            gameSoonFree.add(new_obj);
                        }

                    }
                }
            }

            for (JSONObject jsonObject : gameFree) {
                JSONArray keyImages = (JSONArray) jsonObject.get("keyImages");
                String title = jsonObject.get("title").toString();
                String thumbnail = "";

                for (int y = 0; y < keyImages.size(); y++) {
                    JSONObject element = (JSONObject) keyImages.get(y);
                    String type = element.get("type").toString();

                    if (type.equalsIgnoreCase("Thumbnail")) {
                        thumbnail = element.get("url").toString();
                    }
                }

                String finalThumbnail = thumbnail;
                context.getChannel().createEmbed(embedCreateSpec -> embedCreateSpec
                        .setColor(Color.of(0, 120, 242))
                        .setAuthor(title+" est actuellement gratuit", null, null)
                        .setImage(finalThumbnail)
                        .setFooter("Akasuki", null)
                        .setTimestamp(Instant.now())
                ).block();
            }

            for (JSONObject jsonObject : gameSoonFree) {
                JSONArray keyImages = (JSONArray) jsonObject.get("keyImages");
                String title = jsonObject.get("title").toString();
                String thumbnail = "";

                for (int y = 0; y < keyImages.size(); y++) {
                    JSONObject element = (JSONObject) keyImages.get(y);
                    String type = element.get("type").toString();

                    if (type.equalsIgnoreCase("Thumbnail")) {
                        thumbnail = element.get("url").toString();
                    }
                }

                String finalThumbnail = thumbnail;
                context.getChannel().createEmbed(embedCreateSpec -> embedCreateSpec
                        .setColor(Color.BLACK)
                        .setAuthor(title + " sera bientôt gratuit", null, null)
                        .setImage(finalThumbnail)
                        .setFooter("Akasuki", null)
                        .setTimestamp(Instant.now())
                ).block();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
