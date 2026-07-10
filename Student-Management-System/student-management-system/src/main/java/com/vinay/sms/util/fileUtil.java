package com.vinay.sms.util;

import com.vinay.sms.model.Student;

import java.io.*;
import java.util.List;

public class fileUtil {

    public static void createFile(){
        File file = new File("students.txt");

        try{
            if(file.createNewFile()){
                System.out.println("File created successfully");
            } else  {
                System.out.println("File already exists");
            }
        }catch (IOException e){
            System.out.println("Error while creating file");
            e.printStackTrace();
        }
    }

    public static void saveStudents(List<Student> students) {
        try {
            FileWriter writer = new FileWriter("students.txt");
            for(Student student : students){
                String data =
                        student.getId() + "," +
                                student.getName() + "," +
                                student.getAge() + "," +
                                student.getCourse();
                 writer.write(data);
                 writer.write(System.lineSeparator());
            }
            writer.close();
            System.out.println("Successfully wrote to the file");
        }catch (IOException e){
            System.out.println("Error while writing to the file");
            e.printStackTrace();
        }

    }

    public static void loadStudents(List<Student> students){
        try{
        BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
        String line  ;
        while((line = reader.readLine()) != null){
            String[] data = line.split(",");

            int id = Integer.parseInt(data[0]);
            String name = data[1];
            int age = Integer.parseInt(data[2]);
            String course = data[3];

            Student student =
                    new Student(id, name, age, course);

          students.add(student);
        }
        reader.close();
        }catch(IOException e){
            System.out.println("Error loading students.");
            e.printStackTrace();
        }
    }
}
