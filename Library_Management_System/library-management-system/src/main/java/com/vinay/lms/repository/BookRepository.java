package com.vinay.lms.repository;

import com.vinay.lms.model.Book;
import com.vinay.lms.util.DBConnections;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try(Connection connection = DBConnections.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){

            while(resultSet.next()){
                Book book = new Book(
                        resultSet.getInt("book_id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("category"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                );

                books.add(book);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error while fetching books: " + e.getMessage()
            );
        }

        return books;
    }
    
    public Book findBookById(int bookId){
        String sql = "SELECT * FROM books WHERE book_id = ?";

        try(Connection connection  = DBConnections.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,bookId);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                return new Book(
                        resultSet.getInt("book_id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("category"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                );
            }
        }
        catch(SQLException e){
            System.out.println("Error while fetching book: " + e.getMessage());
        }
        return null;
    }

    public void updateBook(Book book) {

        String sql =
                "UPDATE books SET " +
                        "title = ?, " +
                        "author = ?, " +
                        "category = ?, " +
                        "price = ?, " +
                        "quantity = ? " +
                        "WHERE book_id = ?";

        try (Connection connection = DBConnections.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getCategory());
            statement.setDouble(4, book.getPrice());
            statement.setInt(5, book.getQuantity());
            statement.setInt(6, book.getBookId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book updated successfully.");
            } else {
                System.out.println("Book not found.");
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error while updating book: " + e.getMessage()
            );
        }
    }
    public void deleteBook(int bookId){
        String sql = "DELETE FROM books WHERE book_id = ?";
        try(Connection connection = DBConnections.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
           ){
            statement.setInt(1,bookId);
            int rowAffected = statement.executeUpdate();
            if(rowAffected > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Book not found.");
            }
        }catch (SQLException e) {
            System.out.println( "Error while deleting book: " + e.getMessage() );
        }
    }
}
