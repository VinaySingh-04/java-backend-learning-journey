package com.vinay.lms.services;

import com.vinay.lms.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryServices {

    private ArrayList<Book> books = new ArrayList<>();


    public void addBook(Scanner scanner) {

        System.out.println("\n========== Add New Book ==========");

        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        if(bookId <= 0){
            System.out.println("Book ID should be greater than 0.");
            return;
        }
        scanner.nextLine(); // consume newline

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        if(title.trim().isEmpty()){
            System.out.println("Title should not be empty.");
            return;
        }

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        if(author.trim().isEmpty()){
            System.out.println("Author should not be empty.");
            return;
        }

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        if(category.trim().isEmpty()){
            System.out.println("Category should not be empty.");
            return;
        }

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        if(price <= 0){
            System.out.println("Price should be greater than 0.");
            return;
        }

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        if(quantity < 0){
            System.out.println("Quantity should be greater than 0.");
            return;
        }

        Book book = new Book(bookId,title,author,category,price,quantity);
        books.add(book);

        System.out.println("\n No books available in the library.");

        System.out.println(book);

        System.out.println("\nTotal Books : " + books.size());
    }

    public void viewBooks() {
        if(books.isEmpty()){
            System.out.println("\n Books List is Empty!");
            return;
        }
        System.out.println("\n========== Library Books ==========");
        for(Book book : books){
            System.out.println(book);
            System.out.println("--------------------------------");
        }
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
