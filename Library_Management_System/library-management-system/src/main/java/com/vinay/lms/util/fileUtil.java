package com.vinay.lms.util;

import com.vinay.lms.model.Book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class fileUtil {
    public static void saveBook(List<Book> books){
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"))
            ) {
            for(Book book : books){
                String data = book.getBookId() + "," +
                        book.getTitle() + "," +
                        book.getAuthor() + "," +
                        book.getCategory() + "," +
                        book.getPrice() + "," +
                        book.getQuantity();
                writer.write(data);
                writer.newLine();
                writer.close();
            }

        }   catch (IOException e){
            System.out.println("Error while saving books.");

        }

    }
}
