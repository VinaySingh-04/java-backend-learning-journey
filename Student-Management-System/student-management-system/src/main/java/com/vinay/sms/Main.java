package com.vinay.sms;

import com.vinay.sms.model.Student;

import com.vinay.sms.service.studentService;

public class Main {

    public static void main(String[] args) {

        studentService service = new studentService();

        service.addStudent(
                new Student(101, "Vinay Singh", 22, "Java Backend")
        );

        service.addStudent(
                new Student(102, "Rahul Sharma", 20, "BCA")
        );

        service.addStudent(
                new Student(103, "Amit Kumar", 21, "MCA")
        );

        service.displayStudents();

    }
}