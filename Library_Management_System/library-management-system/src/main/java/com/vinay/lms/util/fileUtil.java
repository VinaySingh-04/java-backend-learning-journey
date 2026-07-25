package com.vinay.lms.util;

import com.vinay.lms.model.Book;

import java.io.*;
import java.util.List;

public class fileUtil {
    public static void saveBook(List<Book> books) {
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"))
        ) {
            for (Book book : books) {
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

        } catch (IOException e) {
            System.out.println("Error while saving books.");

        }

    }

    public static void loadFile(List<Book> books) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader("books.txt"))
        ) {
            String line = reader.readLine();

            while (line != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String title = data[1];
                String author = data[2];
                String category = data[3];
                double price = Double.parseDouble(data[4]);
                int quantity = Integer.parseInt(data[5]);

                Book book = new Book(
                        id,
                        title,
                        author,
                        category,
                        price,
                        quantity
                );

                books.add(book);

                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error while Loading books.");
        }
    }
}
