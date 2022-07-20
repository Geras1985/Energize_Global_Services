package com.hotel.villa.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JImageJunk {

    public static String imageIoWrite(String s, String param) {
        BufferedImage bImage;
        try {
            CreateDirectory.createDirectoriesForImages();
            File initialImage = new File("D://DAVID KURGHINYAN/picture/" + param + s);
            bImage = ImageIO.read(initialImage);

            ImageIO.write(bImage, "jpg", new File("C://Spring/Project/Images/" + s));

            System.out.println("Images were written successfully.");
            return "D://DAVID KURGHINYAN/picture/" + s;

        } catch (IOException e) {
            System.out.println("Exception occurred :" + e.getMessage());
        }
        return null;
    }
}
