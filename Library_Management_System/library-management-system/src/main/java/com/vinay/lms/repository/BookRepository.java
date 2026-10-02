package com.vinay.lms.repository;

import com.vinay.lms.model.Book;
import com.vinay.lms.util.DBConnections;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookRepository {
    public void addBook(Book book) {

        String sql = "INSERT INTO books " +
                     "(book_id, title, author, category, price, quantity) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnections.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, book.getBookId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getCategory());
            statement.setDouble(5, book.getPrice());
            statement.setInt(6, book.getQuantity());

            statement.executeUpdate();

            System.out.println("Book added successfully.");

        } catch (SQLException e) {
            System.out.println(
                    "Error while adding book: " + e.getMessage()
            );
        }
    }
}
