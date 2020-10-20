package fr.dawoox.akasuki.commands.images;

import fr.dawoox.akasuki.utils.Command;
import fr.dawoox.akasuki.utils.ImageUtils;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.awt.*;
import java.util.Map;

/**
 * Show a image of a wanted templated with the sender profil image into /!\ IN BETA /!\
 * @author Dawoox
 * @version 0.1.1
 */
public class Wanted {

    public static void reg(Map<String, Command> commands) {
        commands.put("wanted", event -> {
            ImagePlus font = IJ.openImage("src\\main\\java\\fr\\dawoox\\akasuki\\commands\\images\\wanted.png");
            ImagePlus tempavatar = IJ.openImage(event.getMessage().getAuthor().get().getAvatarUrl());
            ImageProcessor ip = font.getProcessor();

            ImagePlus avatar = tempavatar.resize(200, 200, "none");

            try {
                for (int i=0;i<avatar.getWidth();i++){
                    for (int i2=0;i2<avatar.getHeight();i2++){
                        int[] test = avatar.getPixel(i, i2);
                        Color color = new Color(test[0], test[1], test[2]);
                        ip.setColor(color);
                        ip.setLineWidth(1);
                        if (!color.equals(Color.BLACK)){
                            ip.drawPixel(i + 145, i2 + 270);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            event.getMessage().getChannel().block().createMessage(MessageCreateSpec ->
                    MessageCreateSpec.addFile("wanted.png", ImageUtils.getDiscordCompatibleFile(font))).block();
        });
    }
}
