package com.vinay.sms;

import com.vinay.sms.model.Student;

import com.vinay.sms.service.studentService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        studentService service = new studentService();

        while(true){
            System.out.println("1.Add Student");
            System.out.println("2.Display Student");
            System.out.println("3.Search Student");
            System.out.println("4.Update Student");
            System.out.println("5.Delete Student");
            System.out.println("6.Exit");
            System.out.println("Enter Your Choice");
            int choice = scan.nextInt();

            switch(choice){
                case 1:
                    addStudent(scan,service);
                    break;
                case 2 :
                    displayStudent(service);
                    break;
                case 3:
                    searchStudent(scan,service);
                    break;
                case 4:
                    updateStudent(scan,service);
                     break;
                case 5:
                     deleteStudent(scan,service);
                    break;
                case 6:
                    System.out.println("Thank You For Using Student Management System");
                    scan.close();
                    return;
                default:
                    System.out.println("Invalid Choice ! Please Try Again");
            }
        }
    }

    private static void addStudent(Scanner scanner ,studentService service){
        System.out.println("Enter Student Id");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Student Name");
        String studentName = scanner.nextLine();

        System.out.println("Enter Student Age");
        int studentAge = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Student Course");
        String studentCourse = scanner.nextLine();

        Student student = new Student(studentId,studentName,studentAge,studentCourse);
        service.addStudent(student);
    }

    private static void displayStudent(studentService service){
        service.displayStudents();
    }

    private static void searchStudent(Scanner scanner,studentService service){
        System.out.println("Enter Student Id");
        int id = scanner.nextInt();
        scanner.nextLine();

         Student found =  service.searchStudentById(id);
         if(found != null){
             System.out.println("Student Found");
             System.out.println(found);
         }else{
             System.out.println("Student Not Found");
         }
    }

    private static void updateStudent(Scanner scanner,studentService service){
        System.out.println("Enter Student Id");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Student Name");
        String studentName = scanner.nextLine();

        System.out.println("Enter Student Age");
        int studentAge = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Student Course");
        String studentCourse = scanner.nextLine();

        boolean updateStudent = service.UpdateStudent(id,studentName,studentAge,studentCourse);

        if(updateStudent){
            System.out.println("Student Updated");
        }else{
            System.out.println("Student Not Updated");
        }
    }

    private static void deleteStudent(Scanner scanner,studentService service){
        System.out.println("Enter Student Id For Deletion   ");
        int DeleteId = scanner.nextInt();
        boolean Deleted = service.DeleteStudent(DeleteId);
        if(Deleted){
            System.out.println("Student Deleted");
        }else {
            System.out.println("Student Not Found");
        }
    }
}