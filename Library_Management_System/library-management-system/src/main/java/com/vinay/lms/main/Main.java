package com.vinay.lms.main;

import com.vinay.lms.model.Book;
import com.vinay.lms.services.LibraryServices;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       LibraryServices service = new LibraryServices();

//        System.out.println("Enter Book ID");
//       int BookId = scanner.nextInt();
//       scanner.nextLine();
//
//        System.out.println("Enter Book Title");
//        String BookTitle = scanner.nextLine();





       while(true){

           System.out.println("\n========== Library Management System ==========");
           System.out.println("1. Add Book");
           System.out.println("2. View All Books");
           System.out.println("3. Search Book");
           System.out.println("4. Update Book");
           System.out.println("5. Delete Book");
           System.out.println("6. Exit");
           System.out.print("Enter your choice: ");

           int choice = scanner.nextInt();

           switch(choice){
               case 1:
                   service.addBook(scanner);
                   break;

               case 2:
                  service.viewBooks();
                   break;

               case 3:
                   service.searchBook();
                   break;

               case 4:
                   service.updateBook();
                   break;

               case 5:
                   service.deleteBook();
                   break;

               case 6:
                   System.out.println("Thank you for using Library Management System!");
                   scanner.close();
                   return;

               default:
                   System.out.println("Invalid choice. Please try again.");
           }
       }
    }


}
