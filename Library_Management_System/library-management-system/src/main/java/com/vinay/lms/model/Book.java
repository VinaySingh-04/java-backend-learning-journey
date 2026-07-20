package com.vinay.lms.model;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private double price ;
    private int quantity;

    public Book(){}

    public Book(int bookId,String title,String author,String category,double price,int quantity){
        this.bookId=bookId;
        this.title=title;
        this.author=author;
        this.category=category;
        this.price=price;
        this.quantity=quantity;

    }
}
