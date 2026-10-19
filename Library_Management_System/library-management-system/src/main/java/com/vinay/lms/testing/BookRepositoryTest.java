package com.vinay.lms.testing;

import com.vinay.lms.model.Book;
import com.vinay.lms.repository.BookRepository;

import java.util.List;

public class BookRepositoryTest {
    public static void main(String[] args) {
          BookRepository bookRepository = new BookRepository();

        // 1. INSERT
        System.out.println("\n--- TEST 1: INSERT BOOK ---");

        Book newBook = new Book(
                103,
                "Spring Boot Basics",
                "John",
                "Programming",
                600.00,
                5
        );

        bookRepository.addBook(newBook);


        // 2. SELECT ALL
        System.out.println("\n--- TEST 2: GET ALL BOOKS ---");

        List<Book> books = bookRepository.getAllBooks();

        for (Book book : books) {
            System.out.println(
                    book.getBookId() + " | " +
                    book.getTitle() + " | " +
                    book.getAuthor() + " | " +
                    book.getPrice() + " | " +
                    book.getQuantity()
            );
        }


        // 3. FIND BY ID
        System.out.println("\n--- TEST 3: FIND BOOK BY ID ---");

        Book foundBook = bookRepository.findBookById(103);

        if (foundBook != null) {
            System.out.println("Found: " + foundBook.getTitle());
        } else {
            System.out.println("Book not found.");
        }


        // 4. UPDATE
        System.out.println("\n--- TEST 4: UPDATE BOOK ---");

        Book updatedBook = new Book(
                103,
                "Spring Boot Advanced",
                "John",
                "Programming",
                800.00,
                10
        );

        bookRepository.updateBook(updatedBook);


        // 5. VERIFY UPDATE
        System.out.println("\n--- TEST 5: VERIFY UPDATE ---");

        Book updatedResult = bookRepository.findBookById(103);

        if (updatedResult != null) {
            System.out.println(
                    "Updated title: " + updatedResult.getTitle()
            );
            System.out.println(
                    "Updated price: " + updatedResult.getPrice()
            );
            System.out.println(
                    "Updated quantity: " + updatedResult.getQuantity()
            );
        }


        // 6. DELETE
        System.out.println("\n--- TEST 6: DELETE BOOK ---");

        bookRepository.deleteBook(103);


        // 7. VERIFY DELETE
        System.out.println("\n--- TEST 7: VERIFY DELETE ---");

        Book deletedBook = bookRepository.findBookById(103);

        if (deletedBook == null) {
            System.out.println("PASS: Book deleted successfully.");
        } else {
            System.out.println("FAIL: Book still exists.");
        }
    }
}