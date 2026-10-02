package com.vinay.lms.testing;

import com.vinay.lms.model.Book;
import com.vinay.lms.repository.BookRepository;

public class BookRepositoryTest {
    public static void main(String[] args) {
        Book book = new Book(
                101,
                "Java Basics",
                "James",
                "Programming",
                500.00,
                5
        );

        BookRepository bookRepository = new BookRepository();
        bookRepository.addBook(book);
    }
}
