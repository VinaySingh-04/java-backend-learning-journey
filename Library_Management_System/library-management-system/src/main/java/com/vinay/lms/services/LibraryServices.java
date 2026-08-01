package com.vinay.lms.services;

import com.vinay.lms.model.Book;
import com.vinay.lms.model.Member;
import com.vinay.lms.util.fileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryServices {

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();


      public LibraryServices(){
          fileUtil.loadFile(books);
      }

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

        fileUtil.saveBook(books);

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

    public void searchBook(Scanner scanner) {
        if(books.isEmpty()){
            System.out.println("\nNo Books Available");
            return;
        }

        System.out.println("\nEnter Book Id to search :");
        int searchId = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for(Book book : books){
            if(book.getBookId() == searchId){
                System.out.println("\nBook Found!");
                System.out.println(book);

                found = true;
                break;
            }
        }
    }

    public void updateBook(Scanner scanner) {
        if(books.isEmpty()){
            System.out.println("\nNo Books Available");
            return;
        }

        System.out.println("\nEnter Book Id to update :");
        int updateId = scanner.nextInt();
        scanner.nextLine();

        for(Book book : books){
            if(book.getBookId() == updateId){
                System.out.println("Enter new Title: ");
                book.setTitle(scanner.nextLine());

                System.out.println("Enter new Author");
                book.setAuthor(scanner.nextLine());

                System.out.println("Enter new Category");
                book.setCategory(scanner.nextLine());

                System.out.println("Enter new Price");
                book.setPrice(scanner.nextDouble());
                scanner.nextLine();

                System.out.println("Enter new Quantity");
                book.setQuantity(scanner.nextInt());
                scanner.nextLine();

                System.out.println("\n✅ Book Updated Successfully!");

                 fileUtil.saveBook(books);

                System.out.println(book);
                return;
            }
        }
        System.out.println("\n Book Not Found.");
    }

    public void deleteBook(Scanner scanner) {

        if(books.isEmpty()){
            System.out.println("\nNo Books Available");
            return;
        }

        System.out.println("\nEnter Book Id to Delete :");
        int deletedId = scanner.nextInt();
        scanner.nextLine();

        Book booktoDelete = null;

        for (Book book : books){
            if(book.getBookId() == deletedId){
                booktoDelete = book;
                break;
            }
        }

        if(booktoDelete != null){
            books.remove(booktoDelete);

            fileUtil.saveBook(books);
            System.out.println("\nBook Deleted Successfully");
        }else{
            System.out.println("Book Not Found!");
        }
      }

    public void registerMember(Scanner scanner){
        System.out.println("\n===== Register Member =====");

        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        Member member = new Member(memberId,name,phone,email,address);
        members.add(member);
        System.out.println("Member Registered Successfully!");

    }

    public void viewAllMember(){
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        System.out.println("\n===== All Members =====");

        for (Member member : members) {

            System.out.println(member);
            System.out.println("----------------------");

        }
      }

    public void searchMemberById(Scanner scanner){
        System.out.println("\n===== Search Member =====");

        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        for (Member member : members) {

            if (member.getMemberId() == memberId) {

                System.out.println(member);
                return;
            }
        }

        System.out.println("Member not found.");
    }

    public void updateMember(Scanner scanner){
          System.out.println("\n===== Update Member =====");

        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        for (Member member : members) {

            if (member.getMemberId() == memberId) {

                System.out.print("Enter New Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter New Phone: ");
                String phone = scanner.nextLine();

                System.out.print("Enter New Email: ");
                String email = scanner.nextLine();

                System.out.print("Enter New Address: ");
                String address = scanner.nextLine();

                member.setName(name);
                member.setPhone(phone);
                member.setEmail(email);
                member.setAddress(address);

                System.out.println("Member Updated Successfully!");
                return;
            }
        }

        System.out.println("Member Not Found.");
      }

    public void deleteMember(Scanner scanner) {

        System.out.println("\n===== Delete Member =====");

        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < members.size(); i++) {

            if (members.get(i).getMemberId() == memberId) {

                members.remove(i);

                System.out.println("Member Deleted Successfully!");

                return;
            }
        }

        System.out.println("Member Not Found.");
    }

}
