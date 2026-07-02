package com.vinay.sms.service;

import com.vinay.sms.model.Student;

import java.util.ArrayList;

 public class studentService {

    private ArrayList<Student> students;

    public studentService() {
        students = new ArrayList<>();
    }

    // Add Student
    public void addStudent(Student student) {

        students.add(student);

        System.out.println("Student added successfully.");
    }

    // Display Students
    public void displayStudents() {

        if (students.isEmpty()) {

            System.out.println("No students found.");
            return;
        }

        System.out.println("\n========== STUDENT LIST ==========");

        for (Student student : students) {
            System.out.println(student);
        }

    }

}