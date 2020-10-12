package fr.dawoox.akasuki.utils;

import ij.ImagePlus;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {

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
