package com.vinay.lms.util;

import com.vinay.lms.model.Book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class fileUtil {
    public static void saveBook(List<Book> books){
        try (
                BufferedWriter write = new BufferedWriter(new FileWriter("books.txt"))
            ) {

        }   catch (IOException e){

        }

    }
}
