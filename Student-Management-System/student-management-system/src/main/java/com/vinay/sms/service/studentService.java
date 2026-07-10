package com.vinay.sms.service;

import com.vinay.sms.model.Student;
import com.vinay.sms.util.fileUtil;

import java.util.ArrayList;
import java.util.List;

public class studentService {

    private ArrayList<Student> students;

    public studentService() {

        students = new ArrayList<>();
        fileUtil.loadStudents(students);
    }

    // Add Student
    public void addStudent(Student student) {

        students.add(student);
        fileUtil.saveStudents(students);

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
            fileUtil.saveStudents(students);
            return true;
        }
        return false;
     }

     //Delete Student
     public boolean DeleteStudent(int id){
        Student student = searchStudentById(id);
        if(student != null){
            students.remove(student);
            fileUtil.saveStudents(students);
            return true;
         }else{
            return false;
         }
     }

     public boolean isStudentExist(int id){
         return searchStudentById(id) != null;
     }

     public List<Student> searchStudentByName(String name){
        List<Student> matchedStudent = new ArrayList<>();
        for (Student student :students){
            if(student.getName().toLowerCase().contains(name.toLowerCase())){
               matchedStudent.add(student);
          }
        }
        return matchedStudent;

      }

        public int getTotalStudent(){
        return students.size();
      }

        public  double getAverage(){
        if(students.isEmpty()){
            return 0;
        }

        int sum = 0;
        for(Student student : students){
            sum = sum + student.getAge();
        }

        return (double)sum/students.size();
      }

      public void showCourseStatistics(){
        int javaCounter = 0;
        int pythonCounter = 0;
        int cppCounter = 0;

        for(Student student : students){
            String course = student.getCourse();
            if ("Java".equalsIgnoreCase(course)) {
                javaCounter++;
            }
            else if ("Python".equalsIgnoreCase(course)) {
                pythonCounter++;
            }
            else if ("C++".equalsIgnoreCase(course)) {
                cppCounter++;
            }
            System.out.println("\nCourse Distribution");
            System.out.println("-------------------");

            System.out.printf("%-10s : %d%n", "Java", javaCounter);
            System.out.printf("%-10s : %d%n", "Python", pythonCounter);
            System.out.printf("%-10s : %d%n", "C++", cppCounter);
        }
      }
}