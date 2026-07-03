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
                    System.out.println("Enter Student ID");
                    int id = scan.nextInt();

                    scan.nextLine();

                    System.out.println("Enter Student Name");
                    String name = scan.nextLine();

                    System.out.println("Enter Student Age");
                    int age = scan.nextInt();

                    scan.nextLine();

                    System.out.println("Enter Student Course");
                    String course = scan.nextLine();

                    Student student = new Student(id,name,age,course);
                    service.addStudent(student);

                    break;
                case 2 :
                    service.displayStudents();
                    break;
                case 3:
                    System.out.println("Enter Student ID");
                    int studentId = scan.nextInt();

                    Student foundStudent = service.searchStudentById(studentId);

                    if(foundStudent != null){
                        System.out.println("\n===== Student Found =====");
                        System.out.println(foundStudent);
                    }else{
                        System.out.println("Student Not Found");
                    }
                    break;
                case 4:
                    System.out.println("Enter Student Id to Update");
                    int UpdateId = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Enter New Name");
                    String UpdatesName = scan.nextLine();

                    System.out.println("Enter New Age");
                    int UpdatedAge = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Enter New Course");
                    String UpdatesCourse = scan.nextLine();

                    boolean updated  = service.UpdateStudent(UpdateId,UpdatesName,UpdatedAge,UpdatesCourse);

                    if(updated){
                        System.out.println("Student Updated");
                    }else {
                        System.out.println("Student Not Updated");
                    }

                     break;
                case 5:
                    System.out.println("Enter Student Id For Deletion   ");
                    int DeleteId = scan.nextInt();
                    boolean Deleted = service.DeleteStudent(DeleteId);
                    if(Deleted){
                        System.out.println("Student Deleted");
                    }else {
                        System.out.println("Student Not Found");
                    }
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
}