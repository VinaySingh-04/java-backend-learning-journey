package com.vinay.lms.util;

import com.vinay.lms.model.Book;
import com.vinay.lms.model.IssueBook;
import com.vinay.lms.model.Member;

import java.io.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

                try {

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

                } catch (NumberFormatException e) {

                    System.out.println("Invalid number format in books.txt: " + line);

                } catch (ArrayIndexOutOfBoundsException e) {

                    System.out.println("Invalid book data in books.txt: " + line);
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error while loading books.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in books.txt.");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid book data in books.txt.");
        }
    }

    //for members
    public static void saveMember(List<Member> members){

        try(
            BufferedWriter writer  = new BufferedWriter(new FileWriter("members.txt")))

        {
            for(Member member : members){
                writer.write(  member.getMemberId() + "," +
                        member.getName() + "," +
                        member.getPhone() + "," +
                        member.getEmail() + "," +
                        member.getAddress()
                );
                writer.newLine();
            }
            System.out.println("Member saved successfully.");

        }catch(IOException e){
            System.out.println("Error :- "+ e.getMessage() );
        }
    }

    public static void loadMember(List<Member> members){
        try (BufferedReader reader = new BufferedReader(new FileReader("members.txt"))) {

            String line;

            while((line = reader.readLine()) != null){

                try {

                    String[] data = line.split(",");

                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String phone = data[2];
                    String email = data[3];
                    String address = data[4];

                    Member member = new Member(
                            id,
                            name,
                            phone,
                            email,
                            address
                    );

                    members.add(member);

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid number format in members.txt: " + line
                    );

                } catch (ArrayIndexOutOfBoundsException e) {

                    System.out.println(
                            "Invalid member data in members.txt: " + line
                    );
                }
            }

        } catch (IOException e) {

            System.out.println("Error while loading Members.");
        }
    }


    public static void saveIssuedBooks(List<IssueBook> issuedBooks){

        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter("IssuedBooks.txt")))
        {
            for(IssueBook issueBook : issuedBooks){
                writer.write(issueBook.getIssueId() + "," +
                        issueBook.getMemberId() + "," +
                        issueBook.getBookId() + "," +
                        issueBook.getIssueDate() + "," +
                        issueBook.getDueDate() + "," +
                        issueBook.getReturnDate() + "," +
                        issueBook.isReturned() + "," +
                        issueBook.getFine()
                );
                writer.newLine();
            }

            System.out.println("Issued Book saved successfully");

        }catch(IOException e){
            System.out.println("Error :- "+e.getMessage());
        }
    }

    public static  void loadIssuedBooks(List<IssueBook> issuedBooks){

        try(
            BufferedReader reader  = new BufferedReader(new FileReader("IssuedBooks.txt")))
        {
            String line ;
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                int issueId = Integer.parseInt(data[0]);
                int memberId = Integer.parseInt(data[1]);
                int bookId = Integer.parseInt(data[2]);

                LocalDate issueDate = LocalDate.parse(data[3]);
                LocalDate dueDate = LocalDate.parse(data[4]);


                LocalDate returnDate = null;

                if (!data[5].equals("null")) {
                    returnDate = LocalDate.parse(data[5]);
                }


                boolean returned = Boolean.parseBoolean(data[6]);

                double fine = Double.parseDouble(data[7]);

                IssueBook issueBook = new IssueBook(
                        issueId,
                        memberId,
                        bookId,
                        issueDate,
                        dueDate,
                        returnDate,
                        returned,
                        fine
                );

                issuedBooks.add(issueBook);
            }
            System.out.println("Issued Books loaded successfully");
        } catch (IOException e) {
            System.out.println("Error while loading issued books.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in IssuedBooks.txt.");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid issued book data in IssuedBooks.txt.");

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format in IssuedBooks.txt.");

        }
    }
}
