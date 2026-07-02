package com.vinay.sms.model;

public class Student {

    private int id;
    private String name;
    private int age;
    private String course;

    // Default Constructor
    public Student() {
        this(0, "Unknown", 0, "Not Assigned");
    }

    // Constructor with Name , With One Parameter
    public Student(String name) {
        this(0, name, 0, "Not Assigned");
    }

    // Constructor with ID and Name ,with Two parameters
    public Student(int id, String name) {
        this(id, name, 0, "Not Assigned");
    }

    // Main Constructor
    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    // Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {

        if(age > 0){
            this.age = age;
        }else{
            System.out.println("Invalid Age");
        }

    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {

        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", course='" + course + '\'' +
                '}';
    }

}