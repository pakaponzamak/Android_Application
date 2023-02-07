package com.example.myloginapp;

public class StudentData {
    private String name;
    private String date;
    private String time;

    //Constructor
    public StudentData(String name,String date,String time){
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getName(){
        return name;
    }
    public void getName(String name){
        this.name = name;
    }

    public String getDate(){
        return date;
    }
    public void getDate(String date){
        this.date = date;
    }

    public String getTime(){
        return time;
    }
    public void getTime(String time){
        this.time = time;
    }
}

