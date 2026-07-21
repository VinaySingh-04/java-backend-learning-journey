package com.vinay.lms.main;

import com.vinay.lms.model.Book;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);

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
                   System.out.println("Add Book selected.");
                   break;

               case 2:
                   System.out.println("View All Books selected.");
                   break;

               case 3:
                   System.out.println("Search Book selected.");
                   break;

               case 4:
                   System.out.println("Update Book selected.");
                   break;

               case 5:
                   System.out.println("Delete Book selected.");
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
