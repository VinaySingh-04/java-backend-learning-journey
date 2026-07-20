package com.vinay.lms.main;

import com.vinay.lms.model.Book;

public class Main {
    public static void main(String[] args) {
        Book book = new Book(101,
                "Java Programming",
                "James Gosling",
                "Programming",
                599.99,
                10);

        System.out.println(book);
    }


}
