package fr.dawoox.akasuki.commands.images;

import fr.dawoox.akasuki.utils.Command;
import fr.dawoox.akasuki.utils.ImageUtils;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.awt.*;
import java.util.Map;

/**
 * Show a image of a stonks templated with the sender profil image into /!\ IN BETA /!\
 * @author Dawoox
 * @version 1.3.0
 */
public class Stonks {

    public static void reg(Map<String, Command> commands) {
        commands.put("stonks", event -> {
            ImagePlus font = IJ.openImage("src\\main\\java\\fr\\dawoox\\akasuki\\commands\\images\\stonks.png");
            ImagePlus avatar = IJ.openImage(event.getMessage().getAuthor().get().getAvatarUrl());
            ImageProcessor ip = font.getProcessor();

            try {
                for (int i=0;i<avatar.getWidth();i++){
                    for (int i2=0;i2<avatar.getHeight();i2++){
                        int[] test = avatar.getPixel(i, i2);
                        Color color = new Color(test[0], test[1], test[2]);
                        ip.setColor(color);
                        ip.setLineWidth(1);
                        if (!color.equals(Color.BLACK)){
                            ip.drawPixel(i + 125, i2 + 50);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            event.getMessage().getChannel().block().createMessage(MessageCreateSpec ->
                    MessageCreateSpec.addFile("stonks.png", ImageUtils.getDiscordCompatibleFile(font))).block();
        });
    }
}
