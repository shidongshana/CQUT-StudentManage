package com.example.entity;

public class Student {
    public  int tid;

    public String id;


    public String name;


   public String college;


    public String major;


   public int grade;


   public String clazz;


   public int age;
    public Student(){
        this.tid=1;
        this.id="12223010101";
        this.name="张三";
        this.college="人工智能学院";
        this.major="大数据科学与技术";
        this.grade=2022;
        this.clazz="1班";
        this.age=18;
    }
    public Student(String arg0,String arg1,String arg2,String arg3,int arg4,String arg5,int arg6){
        this.id=arg0;
        this.name=arg1;
        this.college=arg2;
        this.major=arg3;
        this.grade=arg4;
        this.clazz=arg5;
        this.age=arg6;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getTid() {
        return tid;
    }
    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getGrade() {
        return grade;
    }

    public String getClazz() {
        return clazz;
    }

    public String getCollege() {
        return college;
    }

    public String getMajor() {
        return major;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setName(String name) {
        this.name = name;
    }
}
