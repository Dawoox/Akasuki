package fr.dawoox.akasuki.utils;

import ij.ImagePlus;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utils Class to manage ImagePlus Objects
 * @author Dawoox
 * @version 1.0.1
 */
public class ImageUtils {

    /**
     * Return a image into a valid format for Discord Files
     * @param imagePlus
     * The Image to convert from a ImagePlus format into a InputStream
     * @return InputStream
     * Return a InputStream which can be send on Discord
     * @since 1.0.0
     */
    public static InputStream getDiscordCompatibleFile(ImagePlus imagePlus){
        Image image = imagePlus.getImage();
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, null, null);

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", outStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(outStream.toByteArray());
    }
}
