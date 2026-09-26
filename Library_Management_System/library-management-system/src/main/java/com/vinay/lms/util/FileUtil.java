package com.vinay.lms.util;
import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static void writeLines(String fileName , List<String> lines){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for(String line : lines){
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while writing file: " + e.getMessage());
        }
    }

    public static List<String> readLines(String fileName){
        List<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line ;
            while((line = reader.readLine()) != null){
                lines.add(line);
            }
        }catch(IOException e){
            System.out.println("Error while reading file: " + e.getMessage());
        }
        return lines;
    }
}
