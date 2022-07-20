package com.hotel.villa.images;


import java.io.File;

public class CreateDirectory {

    public static void createDirectoriesForImages() {

        File files = new File("C:\\Spring\\Project\\Images");
        if (!files.exists()) {
            if (files.mkdirs()) {
                System.out.println("Multiple directories are created!");
            } else {
                System.out.println("Failed to create multiple directories!");
            }
        }
    }
}
