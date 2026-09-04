package com.vinay.lms.services;


import com.vinay.lms.model.Book;
import com.vinay.lms.model.IssueBook;
import com.vinay.lms.model.Member;
import com.vinay.lms.util.fileUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryServices {

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<IssueBook> issueBooks = new ArrayList<>();


    public LibraryServices(){
        fileUtil.loadFile(books);
        fileUtil.loadMember(members);
        fileUtil.loadIssuedBooks(issueBooks);
      }

    public void addBook(Scanner scanner) {

        System.out.println("\n========== Add New Book ==========");



        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();

        boolean exists  = false;
        for (Book book : books){
            if(book.getBookId() == bookId){
                exists = true;
                break;
            }
        }

        if(exists){
            System.out.println("Book ID is already exists.");
            return;
        }

        if(bookId <= 0){
            System.out.println("Book ID should be greater than 0.");
            return;
        }
        scanner.nextLine();

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
        scanner.nextLine();
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

    public void searchBookByTitle(Scanner scanner){
        System.out.println("\n===== Search Book By Title =====");

        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine().toLowerCase();

        boolean   FoundBook = false;

        for (Book book : books){
            if(book.getTitle().toLowerCase().contains(title)){

                System.out.println("\nBook Found!");
                System.out.println(book);

                FoundBook = true;
                break;

            }
        }

        if(!FoundBook){
            System.out.println("\nBook Not Found.");
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

        boolean isIssued = false;

        for(IssueBook issueBook : issueBooks){
            if(issueBook.getBookId() == deletedId && !issueBook.isReturned()){
                isIssued = true;
                break;
            }
        }
        if(isIssued){
            System.out.println("Book is currently issued. Cannot delete.");
            return;
        }

        if(booktoDelete != null){

            books.remove(booktoDelete);

            fileUtil.saveBook(books);
            System.out.println("\nBook Deleted Successfully");
        }else{
            System.out.println("Book Not Found!");
        }
      }


      //Memebers

    public void registerMember(Scanner scanner){
        System.out.println("\n===== Register Member =====");

        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();

        boolean exists = false;

        for(Member member : members){
            if(member.getMemberId() == memberId){
                exists = true;
                break;
            }
        }

        if(exists){
            System.out.println("Member ID already exists.");
            return;
        }

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
        fileUtil.saveMember(members);
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


    public void searchMemberByName(Scanner scanner){
        System.out.println("\n===== Search Member By Name =====");
        scanner.nextInt();
        System.out.print("Enter Member Name: ");
        String name = scanner.nextLine().toLowerCase();

        boolean found = false;

        for (Member member : members) {

            if (member.getName().toLowerCase().contains(name)) {

                System.out.println("\nMember Found!");
                System.out.println(member);
                System.out.println("-------------------------");

                found = true;
            }
        }

        if (!found) {

            System.out.println("\nMember Not Found.");

        }

    }

    public void updateMember(Scanner scanner){
          System.out.println("\n===== Update Member =====");

        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        for (Member member : members) {

            if (member.getMemberId() == memberId) {

                System.out.println("Enter new Id");
                int id = scanner.nextInt();
                scanner.nextLine();

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

                fileUtil.saveMember(members);

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

        boolean hasBorrowedBook  = false;
        for(IssueBook issueBook : issueBooks){
            if(issueBook.getMemberId() == memberId && !issueBook.isReturned()){
                hasBorrowedBook  = true;
                break;
            }
        }
        if(hasBorrowedBook){
            System.out.println("Member has borrowed books. Cannot delete.");
            return;
        }

        for (int i = 0; i < members.size(); i++) {

            if (members.get(i).getMemberId() == memberId) {

                members.remove(i);
                fileUtil.saveMember(members);

                System.out.println("Member Deleted Successfully!");

                return;
            }
        }

        System.out.println("Member Not Found.");
    }


    //issueBook

    public void issueBook(Scanner scanner) {
        System.out.println("\n===== Issue Book =====");

        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        Member foundMember = null;

        for (Member member : members) {

            if (member.getMemberId() == memberId) {

                foundMember = member;
                break;
            }
        }

        if (foundMember == null) {

            System.out.println("Member not found.");
            return;
        }

        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        Book foundBook = null;

        for (Book book : books) {

            if (book.getBookId() == bookId) {

                foundBook = book;
                break;
            }
        }

        if (foundBook == null) {

            System.out.println("Book not found.");
            return;
        }

        if (foundBook.getQuantity() <= 0) {

            System.out.println("Book is out of stock.");
            return;
        }

        System.out.println("\nMember Found : " + foundMember.getName());
        System.out.println("Book Found   : " + foundBook.getTitle());

        System.out.println("\nValidation Successful");
        System.out.println("Ready to Issue Book...");

        int issueId = issueBooks.size() + 1;
        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(7);

        boolean returned = false;

        IssueBook issueBook = new IssueBook( issueId,
                foundMember.getMemberId(),
                foundBook.getBookId(),
                issueDate,
                dueDate,
                returned
        );

        issueBooks.add(issueBook);
        fileUtil.saveIssuedBooks(issueBooks);

        foundBook.setQuantity(foundBook.getQuantity()-1);
        fileUtil.saveBook(books);

        System.out.println("\nBook Issued Successfully!");
        System.out.println(issueBook);
    }

    public void viewIssuedBook() {
        if (issueBooks.isEmpty()) {

            System.out.println("\nNo books have been issued.");
            return;

        }

        System.out.println("\n========== Issued Books ==========");

        for (IssueBook issueBook : issueBooks) {

            Book book = findBookByID(issueBook.getBookId());

            Member member = findMemberByID(issueBook.getMemberId());

            System.out.println("----------------------------------------");
            System.out.println("Issue ID      : " + issueBook.getIssueId());

            if (member != null) {
                System.out.println("Member Name   : " + member.getName());
                System.out.println("Member ID     : " + member.getMemberId());
            } else {
                System.out.println("Member        : Not Found");
            }

            if (book != null) {
                System.out.println("Book Title    : " + book.getTitle());
                System.out.println("Book ID       : " + book.getBookId());
            } else {
                System.out.println("Book          : Not Found");
            }

            System.out.println("Issue Date    : " + issueBook.getIssueDate());
            System.out.println("Due Date      : " + issueBook.getDueDate());

            System.out.println("Status        : " + (issueBook.isReturned() ? "Returned" : "Issued"));

            System.out.println("----------------------------------------");
        }
    }

    public void returnBook(Scanner scanner){
        System.out.println("\n===== Return Book ======");
        System.out.println("Enter Issue Id: ");
        int issueId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Searching Issue Id : " + issueId);

        IssueBook foundIssue = null;

        for(IssueBook issueBook : issueBooks){
            if(issueBook.getIssueId() == issueId){
                foundIssue = issueBook;
                break;
            }
        }

        if(foundIssue == null){
            System.out.println("Issue record Not Found.");
            return;
        }
        System.out.println("\nIssue Record Found. ");
        System.out.println(foundIssue);

        if(foundIssue.isReturned()){
            System.out.println("\nThis book has already been returned.");
            return;
        }

        LocalDate returnDate = LocalDate.now();

        double fine = calculateFine(foundIssue.getDueDate(), returnDate);
        long lateDays = 0;
        if(returnDate.isAfter(foundIssue.getDueDate())){
            lateDays = ChronoUnit.DAYS.between(foundIssue.getDueDate(), returnDate);
        }

        Book foundBook = null;
        for(Book book : books){
            if(book.getBookId() == foundIssue.getBookId()){
                foundBook = book;
                break;
            }
        }

        if(foundBook == null){
            System.out.println("Book Record Not Found.");
            return;
        }

        foundBook.setQuantity(foundBook.getQuantity() + 1);
        foundIssue.setReturnDate(returnDate);
        foundIssue.setFine(fine);
        foundIssue.setReturned(true);
        fileUtil.saveBook(books);
        fileUtil.saveIssuedBooks(issueBooks);
        System.out.println("\n===== Return Summary =====");

        System.out.println("Book Title : " + foundBook.getTitle());
        System.out.println("Due Date   : " + foundIssue.getDueDate());
        System.out.println("Return Date: " + returnDate);
        System.out.println("Late Days  : " + lateDays);
        System.out.println("Fine       : ₹" + fine);

        System.out.println("Available Quantity : " + foundBook.getQuantity());

        System.out.println("\nBook Returned Successfully!");

    }

    private Book findBookByID(int bookId){

            for(Book book : books){
                if(book.getBookId() == bookId){
                    return  book;
                }
            }
            return null;
    }

    private Member findMemberByID(int memberId){
        for(Member member : members){
            if(member.getMemberId() == memberId){
                return  member;
            }
        }
        return null;
    }

    public void showDashBoard(){

        int totalCopies = 0;
        int returnedBooks = 0;
        int currentlyIssued = 0;
        int overdueBooks = 0;
        double totalFine = 0;


        for (Book book : books) {

            totalCopies += book.getQuantity();
        }


        LocalDate today = LocalDate.now();

        for (IssueBook issueBook : issueBooks) {

            if (issueBook.isReturned()) {

                returnedBooks++;

                totalFine += issueBook.getFine();

            } else {

                currentlyIssued++;

                if (today.isAfter(issueBook.getDueDate())) {

                    overdueBooks++;
                }
            }
        }

        System.out.println("\n========================================");
        System.out.println("           LIBRARY DASHBOARD");
        System.out.println("========================================");

        System.out.println("Total Book Records : " + books.size());
        System.out.println("Available Copies   : " + totalCopies);

        System.out.println("Total Members      : " + members.size());

        System.out.println("Currently Issued   : " + currentlyIssued);
        System.out.println("Returned Books     : " + returnedBooks);
        System.out.println("Overdue Books      : " + overdueBooks);

        System.out.println("Total Fine         : ₹" + totalFine);

        System.out.println("========================================");
    }

    private double calculateFine(LocalDate dueDate,LocalDate returnDate){
        if(!returnDate.isAfter(dueDate)){
            return 0;
        }
        long lateDays = ChronoUnit.DAYS.between(returnDate,dueDate);
        double finePerDays = 10 ;
        return lateDays * finePerDays;
    }

    public void searchIssuedBookById(Scanner scanner) {

        System.out.println("\n===== Search Issued Book =====");

        System.out.print("Enter Issue ID: ");
        int issueId = scanner.nextInt();
        scanner.nextLine();

        IssueBook foundIssue = null;

        for (IssueBook issueBook : issueBooks) {

            if (issueBook.getIssueId() == issueId) {

                foundIssue = issueBook;
                break;
            }
        }

        if (foundIssue == null) {

            System.out.println("Issue record not found.");
            return;
        }

        Member foundMember =
                findMemberByID(foundIssue.getMemberId());

        Book foundBook =
                findBookByID(foundIssue.getBookId());

        System.out.println("\n========== ISSUE DETAILS ==========");

        System.out.println("Issue ID      : " +
                foundIssue.getIssueId());

        if (foundMember != null) {

            System.out.println("Member Name   : " +
                    foundMember.getName());

            System.out.println("Member ID     : " +
                    foundMember.getMemberId());

        } else {

            System.out.println("Member        : Not Found");
        }

        if (foundBook != null) {

            System.out.println("Book Title    : " +
                    foundBook.getTitle());

            System.out.println("Book ID       : " +
                    foundBook.getBookId());

        } else {

            System.out.println("Book          : Not Found");
        }

        System.out.println("Issue Date    : " +
                foundIssue.getIssueDate());

        System.out.println("Due Date      : " +
                foundIssue.getDueDate());

        if (foundIssue.isReturned()) {

            System.out.println("Return Date   : " +
                    foundIssue.getReturnDate());

            System.out.println("Status        : Returned");

        } else {

            System.out.println("Return Date   : Not Returned Yet");

            System.out.println("Status        : Issued");
        }

        System.out.println("Fine          : ₹" +
                foundIssue.getFine());

        System.out.println("===================================");
    }

    public void showOverdueBooks() {

        if (issueBooks.isEmpty()) {
            System.out.println("\nNo issue books found.");
            return;
        }

        LocalDate currentDate = LocalDate.now();

        boolean foundOverdue = false;

        System.out.println("\n========== Overdue Books ==========");

        for (IssueBook issueBook : issueBooks) {

            if (!issueBook.isReturned()
                    && currentDate.isAfter(issueBook.getDueDate())) {

                foundOverdue = true;

                Book foundBook = findBookByID(issueBook.getBookId());
                Member foundMember = findMemberByID(issueBook.getMemberId());

                long lateDays = ChronoUnit.DAYS.between(
                        issueBook.getDueDate(),
                        currentDate
                );

                System.out.println("----------------------------------------");

                System.out.println("Issue ID      : " + issueBook.getIssueId());

                if (foundMember != null) {
                    System.out.println("Member Name   : " + foundMember.getName());
                    System.out.println("Member ID     : " + foundMember.getMemberId());
                } else {
                    System.out.println("Member        : Not Found");
                }

                if (foundBook != null) {
                    System.out.println("Book Title    : " + foundBook.getTitle());
                    System.out.println("Book ID       : " + foundBook.getBookId());
                } else {
                    System.out.println("Book          : Not Found");
                }

                System.out.println("Issue Date    : " + issueBook.getIssueDate());
                System.out.println("Due Date      : " + issueBook.getDueDate());
                System.out.println("Late Days     : " + lateDays);

                System.out.println("----------------------------------------");
            }
        }

        if (!foundOverdue) {
            System.out.println("No overdue books found.");
        }
    }

    public void  showRemainingDays(){

        if (issueBooks.isEmpty()) {
            System.out.println("\nNo issue books found.");
            return;
        }

        LocalDate today = LocalDate.now();

        System.out.println("\n========== Book Due Status ==========");

        boolean foundActiveBook = false;

        for (IssueBook issueBook : issueBooks) {

            // Ignore returned books
            if (!issueBook.isReturned()) {

                foundActiveBook = true;

                Book foundBook = findBookByID(issueBook.getBookId());
                Member foundMember = findMemberByID(issueBook.getMemberId());

                System.out.println("----------------------------------------");

                System.out.println("Issue ID      : " + issueBook.getIssueId());

                if (foundMember != null) {
                    System.out.println("Member Name   : " + foundMember.getName());
                    System.out.println("Member ID     : " + foundMember.getMemberId());
                } else {
                    System.out.println("Member        : Not Found");
                }

                if (foundBook != null) {
                    System.out.println("Book Title    : " + foundBook.getTitle());
                    System.out.println("Book ID       : " + foundBook.getBookId());
                } else {
                    System.out.println("Book          : Not Found");
                }

                System.out.println("Issue Date    : " + issueBook.getIssueDate());
                System.out.println("Due Date      : " + issueBook.getDueDate());

                if (today.isBefore(issueBook.getDueDate())) {

                    long remainingDays =
                            ChronoUnit.DAYS.between(
                                    today,
                                    issueBook.getDueDate()
                            );

                    System.out.println("Status        : Active");
                    System.out.println("Remaining Days: " + remainingDays);

                } else if (today.isEqual(issueBook.getDueDate())) {

                    System.out.println("Status        : Due Today");
                    System.out.println("Remaining Days: 0");

                } else {

                    long lateDays =
                            ChronoUnit.DAYS.between(
                                    issueBook.getDueDate(),
                                    today
                            );

                    System.out.println("Status        : OVERDUE");
                    System.out.println("Late Days     : " + lateDays);
                }

                System.out.println("----------------------------------------");
            }
        }

        if (!foundActiveBook) {
            System.out.println("No currently issued books found.");
        }
    }

    public void showFineReports() {
        if (issueBooks.isEmpty()) {
            System.out.println("\nNo issue books found.");
            return;
        }

        boolean foundFine = false;

        System.out.println("\n========== Fine Report ==========");

        for (IssueBook issueBook : issueBooks) {

            if (issueBook.isReturned() && issueBook.getFine() > 0) {

                foundFine = true;

                Book foundBook = findBookByID(issueBook.getBookId());
                Member foundMember = findMemberByID(issueBook.getMemberId());

                System.out.println("----------------------------------------");

                System.out.println("Issue ID      : " + issueBook.getIssueId());

                if (foundMember != null) {
                    System.out.println("Member Name   : " + foundMember.getName());
                    System.out.println("Member ID     : " + foundMember.getMemberId());
                } else {
                    System.out.println("Member        : Not Found");
                }

                if (foundBook != null) {
                    System.out.println("Book Title    : " + foundBook.getTitle());
                    System.out.println("Book ID       : " + foundBook.getBookId());
                } else {
                    System.out.println("Book          : Not Found");
                }

                System.out.println("Issue Date    : " + issueBook.getIssueDate());
                System.out.println("Due Date      : " + issueBook.getDueDate());
                System.out.println("Return Date   : " + issueBook.getReturnDate());
                System.out.println("Fine          : ₹" + issueBook.getFine());

                System.out.println("----------------------------------------");
            }
        }

        if (!foundFine) {
            System.out.println("No fine records found.");
        }
    }

    public void showTotalfine() {

        if (issueBooks.isEmpty()) {
            System.out.println("There is no issue books.");
            return;
        }

        double totalFine = 0;

        for (IssueBook issueBook : issueBooks) {

            if (issueBook.isReturned() && issueBook.getFine() > 0) {
                totalFine = totalFine + issueBook.getFine();
            }
        }

        System.out.println("Total Fine : ₹" + totalFine);
    }
}
