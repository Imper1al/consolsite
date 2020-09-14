package com.company;

public class Student extends Person {
    int course = 6;
    public Student(String name, int height, int weight, int course){
        super(name, height, weight);
        this.course = course;
    }
    void tell(){
        System.out.println("Name " + super.name + "\nHeight "+ super.height + "\nWeight " + super.weight + "\nCourse " + course);
    }
}
