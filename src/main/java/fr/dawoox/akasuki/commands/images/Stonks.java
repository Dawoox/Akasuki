package fr.dawoox.akasuki.commands.images;

import com.sun.tools.javac.util.List;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.core.command.CommandCategory;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.Context;
import fr.dawoox.akasuki.utils.ImageUtils;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.awt.*;

/**
 * Show a image of a stonks templated with the sender profil image into /!\ IN BETA /!\
 * @author Dawoox
 * @version 1.3.0
 */
public class Stonks extends BaseCmd {

    public Stonks() {
        super(CommandCategory.IMAGE, CommandPermission.USER, List.of("stonks"));
    }

    @Override
    public void execute(Context context) {
        ImagePlus font = IJ.openImage("src\\main\\java\\fr\\dawoox\\akasuki\\commands\\images\\stonks.png");
        ImagePlus avatar = IJ.openImage(context.getAuthor().getAvatarUrl());
        ImageProcessor ip = font.getProcessor();

        try {
            for (int i=0;i<avatar.getWidth();i++){
                for (int i2=0;i2<avatar.getHeight();i2++){
                    int[] temp = avatar.getPixel(i, i2);
                    Color color = new Color(temp[0], temp[1], temp[2]);
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

        context.getChannel().createMessage(MessageCreateSpec ->
                MessageCreateSpec.addFile("stonks.png", ImageUtils.getDiscordCompatibleFile(font))).block();
    }
}
