package org.evision.saravanan.studentapp;

/**
 * Created by saravanan on 18/08/18.
 * Student Model Class with required set and get methods
 */

public class Student {
    // fields
    private int studentID;
    private String studentName;
    private String department;
    // constructors
    public Student() {}
    public Student(int id, String studentname,String department) {
        this.studentID = id;
        this.studentName = studentname;
        this.department =department;
    }
    // properties
    public void setID(int id) {
        this.studentID = id;
    }
    public int getID() {
        return this.studentID;
    }
    public void setStudentName(String studentname) {
        this.studentName = studentname;
    }
    public String getStudentName() {
        return this.studentName;
    }

    public void setDepartment(String department) {this.department=department;}
    public String getDepartment(){return  this.department;}
}