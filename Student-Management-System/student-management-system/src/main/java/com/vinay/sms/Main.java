package com.vinay.sms;

import com.vinay.sms.model.Student;
import com.vinay.sms.service.studentService;
import com.vinay.sms.util.fileUtil;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        fileUtil.createFile();

        fileUtil.writeSampleData();

        Scanner scan = new Scanner(System.in);

        studentService service = new studentService();

        while(true){
            System.out.println("1.Add Student");
            System.out.println("2.Display Student");
            System.out.println("3.Search Student By Id");
            System.out.println("4.Search Student By Name");
            System.out.println("5.Update Student");
            System.out.println("6.Delete Student");
            System.out.println("7.Show All Students");
            System.out.println("8.Exit");
            System.out.println("Enter Your Choice");
            int choice = scan.nextInt();
            scan.nextLine();

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
                    searchStudentByName(scan,service);
                    break;
                case 5:
                    updateStudent(scan,service);
                     break;
                case 6:
                     deleteStudent(scan,service);
                    break;
                case 7:
                    ShowDisplay(service);
                    break;
                case 8:
                    System.out.println("Thank You For Using Student Management System");
                    scan.close();
                    return;
                default:
                    System.out.println("Invalid Choice ! Please Try Again");
            }
        }
    }

    private static void addStudent(Scanner scanner ,studentService service){
        int studentId;
        while(true){
            studentId = readValidId(scanner);
            if(!service.isStudentExist(studentId)){
                break;
            }
            System.out.println("Student id already exist , Enter a different id");
        }
        scanner.nextLine();

        String studentName = readValidName(scanner);

        int studentAge = readValidAge(scanner);
        scanner.nextLine();


        String studentCourse = readValidCourse(scanner);

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
        System.out.println("\n========== UPDATE STUDENT ==========");

        int id = readValidId(scanner);

        Student foundStudent = service.searchStudentById(id);

        if (foundStudent == null) {
            System.out.println(" Student not found.");
            return;
        }

        System.out.println("\nStudent Found:");
        System.out.println(foundStudent);

        while(true){
            System.out.println("\n===== UPDATE MENU =====");

            System.out.println("1. Update Name");
            System.out.println("2. Update Age");
            System.out.println("3. Update Course");
            System.out.println("4. Update All Details");
            System.out.println("5. Cancel");

            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    String newName = readValidName(scanner);
                    foundStudent.setName(newName);
                    System.out.println("name updated successfully");
                    break;
                case 2:
                    int newAge = readValidAge(scanner);
                    foundStudent.setAge(newAge);
                    System.out.println("age updated successfully");
                    break;
                case 3:
                    String newCourse = readValidCourse(scanner);
                    foundStudent.setCourse(newCourse);
                    System.out.println("course updated successfully");
                    break;
                case 4:
                    String name = readValidName(scanner);
                    int age = readValidAge(scanner);
                    scanner.nextLine();
                    String course = readValidCourse(scanner);

                    foundStudent.setName(name);
                    foundStudent.setAge(age);
                    foundStudent.setCourse(course);

                    System.out.println(" student details updated successfully.");
                    break;
                    case 5:
                    System.out.println("Update cancelled.");
                    return;

                default:
                    System.out.println("Invalid choice.");

            }
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

    private static int readValidId(Scanner scanner){
        int id ;

        while(true) {
            try {
                System.out.println("Enter Student Id");
                id = scanner.nextInt();

                if (id > 0) {
                    return id;
                }
                System.out.println("Id must be greater than zero");
            } catch(InputMismatchException e) {
               printInvalidNumberMessage();
                scanner.nextLine();
            }
        }
    }

    private static int readValidAge(Scanner scanner){
        int age;
        while(true){
            try {
                System.out.println("Enter Student Age");
                age = scanner.nextInt();

                if (age >= 1 && age <= 120) {
                    return age;
                }

                System.out.println("Age must be between 1 and 120");
            }
            catch(InputMismatchException e) {
               printInvalidNumberMessage();
                scanner.nextLine();
            }
        }
    }

    private static String readValidName(Scanner scanner){
        String name;
        while(true){
            System.out.println("Enter Student Name");
            name = scanner.nextLine();

            if (!name.isBlank()){
                return name.trim();
            }
            System.out.println("Name can't be blank");
        }
    }

    private static String readValidCourse(Scanner scanner){
        String course;
        while(true){
            System.out.println("Enter Student Course");
            course = scanner.nextLine();
            if(!course.isBlank()){
                return course.trim();
            }
            System.out.println("Course can't be blank");
        }
    }

    private static void printInvalidNumberMessage(){
    System.out.println("Invalid input. Please enter numbers only.");
    }

    private static void searchStudentByName(Scanner scanner, studentService service){
//        scanner.nextLine();
        System.out.println("Enter Student Name : ");
        String name = scanner.nextLine();

        List<Student> matchedStudent = service.searchStudentByName(name);
        if(matchedStudent.isEmpty()){
            System.out.println("Student Not Found");
            return;
        }
        System.out.println("\n===== Matching Students =====");
        for(Student student : matchedStudent){
            System.out.println(student);
        }
    }

    private static void ShowDisplay(studentService service){

        System.out.println("\n========== DASHBOARD ==========");

        int totalStudents = service.getTotalStudent();

        double average = service.getAverage();

        System.out.println("Total Students : " + totalStudents);

        System.out.printf("Average : %.2f%n",average);

        System.out.println("===============================");
    }
}