package com.vinay.lms.services;


import com.vinay.lms.model.Book;
import com.vinay.lms.model.IssueBook;
import com.vinay.lms.model.Member;
import com.vinay.lms.util.FileUtil;
import com.vinay.lms.util.InputUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class LibraryServices {

    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<IssueBook> issueBooks = new ArrayList<>();

    private static final double FINE_PER_DAY = 10;


    public LibraryServices(){
        loadBooks();
        loadMembers();
        loadIssuedBooks();
      }

    public void addBook(Scanner scanner) {

        System.out.println("\n========== Add New Book ==========");

           int bookId = InputUtil.readInt(scanner,"Enter Book Id");

            boolean exists = false;
            for (Book book : books) {
                if (book.getBookId() == bookId) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("Book ID already exists.");
                return;
            }

            if (bookId <= 0) {
                System.out.println("Book ID should be greater than 0.");
                return;
            }


           String title = InputUtil.readNonEmptyString(scanner,"Enter Title: ");
           String author = InputUtil.readNonEmptyString(scanner,"Enter Author: ");
           String category = InputUtil.readNonEmptyString(scanner,"Enter Category: ");


            double price = InputUtil.readDouble(scanner,"Enter Book Price");

            if (price <= 0) {
                System.out.println("Price should be greater than 0.");
                return;
            }

            int quantity = InputUtil.readInt(scanner,"Enter Quantity: ");

            if (quantity <= 0) {
                System.out.println("Quantity should be greater than 0.");
                return;
            }


            Book book = new Book(bookId, title, author, category, price, quantity);
            books.add(book);

            System.out.println(book);

            saveBooks();

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

    public void searchBookById(Scanner scanner) {
        if(books.isEmpty()){
            System.out.println("\nNo Books Available");
            return;
        }

        int searchId = InputUtil.readInt(scanner,"Enter Book Id To Search: ");

        boolean found = false;
        for(Book book : books){
            if(book.getBookId() == searchId){
                System.out.println("\nBook Found!");
                System.out.println(book);

                found = true;
                break;
            }
        }

        if(!found){
            System.out.println("\nBook Not Found.");
        }
    }

    public void searchBook(Scanner scanner){
        System.out.println("\n===== Search Book By Title =====");

        String word = InputUtil.readNonEmptyString(scanner, "Enter book title, author, or category:  ").toLowerCase();
        if (word.isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return;
        }

        boolean   foundBook = false;

        for (Book book : books){
            if(book.getTitle().toLowerCase(Locale.ROOT).contains(word)
                    || book.getAuthor().toLowerCase(Locale.ROOT).contains(word)
                    || book.getCategory().toLowerCase(Locale.ROOT).contains(word)) {

                System.out.println("\nBook Found!");
                System.out.println(book);

                foundBook = true;

            }
        }

        if(!foundBook){
            System.out.println("\nBook Not Found.");
        }
    }


    public void updateBook(Scanner scanner) {

        System.out.println("\n========== Update Book ==========");

        int bookId = InputUtil.readInt(scanner,"Enter Book Id");

        boolean found = false;

        for (Book book : books) {

            if (book.getBookId() == bookId) {

                found = true;

                String title = InputUtil.readNonEmptyString(scanner, "Enter New Title: ");

                String author = InputUtil.readNonEmptyString(scanner,"Enter New Author: ");

                String category = InputUtil.readNonEmptyString(scanner,"Enter New Category: ");

                double price = InputUtil.readDouble(scanner,"Enter New Price:");


                if (price <= 0) {
                    System.out.println("Price must be greater than 0.");
                    return;
                }

                int quantity = InputUtil.readInt(scanner,"Enter New Quantity: ");

                if (quantity < 0) {
                    System.out.println("Quantity cannot be negative.");
                    return;
                }

                book.setTitle(title);
                book.setAuthor(author);
                book.setCategory(category);
                book.setPrice(price);
                book.setQuantity(quantity);

               saveBooks();

                System.out.println("\nBook updated successfully!");
                System.out.println(book);

                break;
            }
        }

        if (!found) {
            System.out.println("\nBook Not Found.");
        }
    }


    public void deleteBook(Scanner scanner) {

        if(books.isEmpty()){
            System.out.println("\nNo Books Available");
            return;
        }

        int deletedId = InputUtil.readInt(scanner,"Enter Book Id to Delete :");

        Book bookToDelete = null;

        for (Book book : books){
            if(book.getBookId() == deletedId){
                bookToDelete = book;
                break;
            }
        }

        if (bookToDelete == null) {
            System.out.println("Book Not Found!");
            return;
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

        books.remove(bookToDelete);

        saveBooks();

        System.out.println("\nBook Deleted Successfully");
      }


      private void loadBooks(){
        List<String> lines = FileUtil.readLines("Books.txt");

        for(String line : lines){
            try{
                String[] data = line.split(",");

                int bookId = Integer.parseInt(data[0]);
                String title = data[1];
                String author = data[2];
                String category = data[3];
                double price = Double.parseDouble(data[4]);
                int quantity = Integer.parseInt(data[5]);

                Book book = new Book(
                        bookId,
                        title,
                        author,
                        category,
                        price,
                        quantity
                );

                books.add(book);
            }catch(NumberFormatException | ArrayIndexOutOfBoundsException e){
                System.out.println("Skipping invalid book record: " + line);
            }
        }
      }

      private void saveBooks(){
        List<String> lines = new ArrayList<>();

        for(Book book : books){
            String line =   book.getBookId() + "," +
                    book.getTitle() + "," +
                    book.getAuthor() + "," +
                    book.getCategory() + "," +
                    book.getPrice() + "," +
                    book.getQuantity();

            lines.add(line);
        }
        FileUtil.writeLines("books.txt",lines);
      }

      //Memebers

    public void registerMember(Scanner scanner){
        System.out.println("\n===== Register Member =====");

        int memberId = InputUtil.readInt(scanner,"Enter Member ID: ");

        if(memberId <= 0){
            System.out.println("Member ID should be greater than 0.");
            return;
        }

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

        String name = InputUtil.readNonEmptyString(scanner, "Enter Name: ");

        String phone = InputUtil.readNonEmptyString(scanner, "Enter Phone Number: ");

        String email = InputUtil.readNonEmptyString(scanner, "Enter Email: ");

        String address = InputUtil.readNonEmptyString(scanner,"Enter Address: ");

        Member member = new Member(memberId,name,phone,email,address);
        members.add(member);
        saveMembers();
        System.out.println("Member Registered Successfully!");

    }

    public void viewAllMembers(){
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

        int memberId = InputUtil.readInt(scanner, "Enter Member ID: ");

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

        String name = InputUtil.readNonEmptyString(scanner, "Enter Member Name: ").toLowerCase();

        if (name.isEmpty()) {
            System.out.println("Member name cannot be empty.");
            return;
        }

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

        int memberId = InputUtil.readInt(scanner,"Enter Member ID: ");

        for (Member member : members) {

            if (member.getMemberId() == memberId) {

                String name = InputUtil.readNonEmptyString(scanner, "Enter New Name: ");

                String phone = InputUtil.readNonEmptyString(scanner, "Enter New Phone Number: ");

                String email = InputUtil.readNonEmptyString(scanner, "Enter New Email: ");

                String address = InputUtil.readNonEmptyString(scanner,"Enter New Address: ");

                member.setName(name);
                member.setPhone(phone);
                member.setEmail(email);
                member.setAddress(address);

                saveMembers();

                System.out.println("Member Updated Successfully!");
                return;
            }
        }

        System.out.println("Member Not Found.");
      }

    public void deleteMember(Scanner scanner) {

        if(members.isEmpty()){
            System.out.println("No members registered.");
            return;
        }

        System.out.println("\n===== Delete Member =====");

        int memberId = InputUtil.readInt(scanner,"Enter Member ID: ");

        Member memberToDelete = null;

        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                memberToDelete = member;
                break;
            }
        }

        if (memberToDelete == null) {
            System.out.println("Member Not Found.");
            return;
        }

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
                saveMembers();

                System.out.println("Member Deleted Successfully!");

                return;
            }
        }

        System.out.println("Member Not Found.");
    }


    private void loadMembers(){
        List<String> lines = FileUtil.readLines("members.txt");

        for(String line : lines){
            try{
                String[] data = line.split(",");

                int memberId = Integer.parseInt(data[0]);
                String name = data[1];
                String phone = data[2];
                String email = data[3];
                String address = data[4];

                Member member = new Member(
                        memberId,
                        name,
                        phone,
                        email,
                        address
                );

                members.add(member);
            }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                System.out.println("Skipping invalid member record: " + line);
            }
        }
    }

    private void saveMembers(){
        List<String> lines = new ArrayList<>();

        for(Member member : members){
            String line =  member.getMemberId() + "," +
                    member.getName() + "," +
                    member.getPhone() + "," +
                    member.getEmail() + "," +
                    member.getAddress();

            lines.add(line);
        }
        FileUtil.writeLines("members.txt", lines);
    }



    //issueBook




    public void issueBook(Scanner scanner) {
        System.out.println("\n===== Issue Book =====");


        int memberId = InputUtil.readInt(scanner, "Enter Member ID: ");

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

        int bookId = InputUtil.readInt(scanner, "Enter Book ID: ");

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
        saveIssuedBooks();

        foundBook.setQuantity(foundBook.getQuantity()-1);
        saveBooks();

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

        int issueId = InputUtil.readInt(scanner, "Enter Issue ID: ");

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

        Book foundBook = findBookByID(
                foundIssue.getBookId()
        );

        if (foundBook == null) {
            System.out.println("Book Record Not Found.");
            return;
        }


        foundBook.setQuantity(foundBook.getQuantity() + 1);
        foundIssue.setReturnDate(returnDate);
        foundIssue.setFine(fine);
        foundIssue.setReturned(true);
        saveBooks();
        saveIssuedBooks();
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
        long lateDays = ChronoUnit.DAYS.between(dueDate,returnDate);

        return lateDays * FINE_PER_DAY;
    }

    public void searchIssuedBookById(Scanner scanner) {

        System.out.println("\n===== Search Issued Book =====");


        int issueId = InputUtil.readInt(scanner,"Enter Issue ID: ");

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

    public void showTotalFine() {

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

    private void loadIssuedBooks(){
        List<String> lines = FileUtil.readLines("issueBooks.txt");

        for(String line : lines){

            try{
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

                issueBooks.add(issueBook);
            }catch(NumberFormatException | ArrayIndexOutOfBoundsException | DateTimeParseException e) {

                System.out.println("Skipping invalid issued book record: " + line);
            }
        }
    }

    private void saveIssuedBooks(){
        List<String> lines = new ArrayList<>();

        for (IssueBook issueBook : issueBooks) {

            String returnDate = issueBook.getReturnDate() == null
                    ? "null"
                    : issueBook.getReturnDate().toString();

            String line =
                    issueBook.getIssueId() + "," +
                            issueBook.getMemberId() + "," +
                            issueBook.getBookId() + "," +
                            issueBook.getIssueDate() + "," +
                            issueBook.getDueDate() + "," +
                            returnDate + "," +
                            issueBook.isReturned() + "," +
                            issueBook.getFine();

            lines.add(line);
        }

        FileUtil.writeLines("issuedBooks.txt", lines);
    }
}
