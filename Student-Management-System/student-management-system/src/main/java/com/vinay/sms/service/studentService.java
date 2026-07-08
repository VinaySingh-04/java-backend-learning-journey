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

    //    Search Student by id
    public Student searchStudentById(int id ){
        for(Student student : students){
            if(student.getId() == id){
                return student;
            }
        }
        return null;
    }



    //update Student
     public boolean UpdateStudent(int id ,String name ,int age ,String Course){

        Student student = searchStudentById(id);
        if(student != null){
            student.setName(name);
            student.setAge(age);
            student.setCourse(Course);
            return true;
        }
        return false;
     }

     //Delete Student
     public boolean DeleteStudent(int id){
        Student student = searchStudentById(id);
        if(student != null){
            students.remove(student);
            return true;
         }else{
            return false;
         }
     }

     public boolean isStudentExist(int id){
         return searchStudentById(id) != null;
     }

}