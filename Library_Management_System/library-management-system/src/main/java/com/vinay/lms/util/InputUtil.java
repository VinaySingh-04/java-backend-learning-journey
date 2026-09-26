package com.vinay.lms.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {

    public static int readInt(Scanner scanner , String message){

        while(true){
            System.out.println(message);

            try{
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            }catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    public static double readDouble(Scanner scanner , String message){

        while(true){
            System.out.println(message);
            try{
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            }catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    public static String readNonEmptyString(Scanner scanner,String message){

        while(true) {
            System.out.println(message);
            String value = scanner.nextLine().trim();
                if (!value.isEmpty()) {
                    return value;
                }
                System.out.println("Input cannot be empty.");

        }
    }
}
