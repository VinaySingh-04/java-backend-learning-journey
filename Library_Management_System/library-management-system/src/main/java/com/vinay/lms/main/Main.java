package com.vinay.lms.main;

import com.vinay.lms.model.Book;
import com.vinay.lms.services.LibraryServices;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       LibraryServices service = new LibraryServices();


       while(true){

           System.out.println("\n========== Library Management System ==========");
           System.out.println("0. DashBoard");
           System.out.println("1. Add Book");
           System.out.println("2. View All Books");
           System.out.println("3. Search Book By Id");
           System.out.println("4. Search Book By Title");
           System.out.println("5. Update Book");
           System.out.println("6. Delete Book");
           System.out.println();
           System.out.println("========== Member Management ==========");
           System.out.println("7. Register New Member");
           System.out.println("8. View All Members");
           System.out.println("9. Search Member By Id");
           System.out.println("10. Search Member By Name");
           System.out.println("11. Update Member");
           System.out.println("12. Delete Member");
           System.out.println();
           System.out.println("\n========== Issue Book Management ==========");
           System.out.println("13. Issue Book");
           System.out.println("14. View Issued Books");
           System.out.println("15. Search Issued Book");
           System.out.println("16. Return Book");
           System.out.println("17. Show Overdue Book");
           System.out.println("18. Show Remaining Days");
           System.out.println("19. Show Fine Reports");
           System.out.println("20. Exit");
           System.out.print("Enter your choice: ");

           int choice = scanner.nextInt();

           switch(choice){
               case 0:
                   service.showDashBoard();
                   break;

               case 1:
                   service.addBook(scanner);
                   break;

               case 2:
                  service.viewBooks();
                   break;

               case 3:
                   service.searchBook(scanner);
                   break;

               case 4:
                   service.searchBookByTitle(scanner);
                   break;

               case 5:
                   service.updateBook(scanner);
                   break;

               case 6:
                   service.deleteBook(scanner);
                   break;

               case 7:
                  service.registerMember(scanner);
                  break;

               case 8:
                   service.viewAllMember();
                   break;

               case 9:
                   service.searchMemberById(scanner);
                   break;

               case 10:
                   service.searchMemberByName(scanner);
                   break;

               case 11:
                   service.updateMember(scanner);
                   break;

               case 12:
                   service.deleteMember(scanner);
                   break;

               case 13:
                   service.issueBook(scanner);
                   break;

               case 14:
                   service.viewIssuedBook();
                   break;

               case 15:
                   service.searchIssuedBookById(scanner);
                   break;

               case 16:
                   service.returnBook(scanner);
                   break;

               case 17:
                   service.showOverdueBooks();
                   break;

               case 18:
                   service.showRemainingDays();
                   break;

               case 19:
                   service.showFineReports();
                   break;


               case 20:
                   System.out.println("Thank you for using Library Management System!");
                   scanner.close();
                   return;

               default:
                   System.out.println("Invalid choice. Please try again.");
           }
       }
    }


}
