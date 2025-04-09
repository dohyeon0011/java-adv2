package io.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * File은 Java 1.0 Ver
 */
public class OldFileMain {

    public static void main(String[] args) throws IOException {
        File file = new File("temp/example.txt");
        File directory = new File("temp/exampleDir");

        System.out.println("File exists: " + file.exists());

        boolean created = file.createNewFile();
        System.out.println("File created = " + created);

        boolean dirCreated = directory.mkdir();
        System.out.println("DirCreated created = " + dirCreated);

//        boolean deleted = file.delete();
//        System.out.println("File deleted = " + deleted);

        System.out.println("Is file: " + file.isFile());
        System.out.println("Is directory: " + directory.isDirectory());
        System.out.println("File Name: " + file.getName());
        System.out.println("File size: " + file.length() + " bytes");

        File newFile = new File("temp/newExample.txt");
        boolean renamed = file.renameTo(newFile);
        System.out.println("File renamed = " + renamed);

        long lastModified = newFile.lastModified();
        System.out.println("File lastModified = " + new Date(lastModified));
    }
}
