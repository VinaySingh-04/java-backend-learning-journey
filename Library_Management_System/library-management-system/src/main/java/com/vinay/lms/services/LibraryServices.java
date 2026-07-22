package com.vinay.lms.services;

import com.vinay.lms.model.Book;

import java.util.Scanner;

public class LibraryServices {

    public void addBook(Scanner scanner) {

        System.out.println("\n========== Add New Book ==========");

        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        Book book = new Book(bookId,title,author,category,price,quantity);

        System.out.println(book);
    }

    public void viewBooks() {
        System.out.println("View Books feature will be implemented here.");
    }

    public void searchBook() {
        System.out.println("Search Book feature will be implemented here.");
    }

    public void updateBook() {
        System.out.println("Update Book feature will be implemented here.");
    }

    public void deleteBook() {
        System.out.println("Delete Book feature will be implemented here.");
    }

}
