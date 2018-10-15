package com.example.shmuel.myapplication;

public class Task {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String name;
    private String text;

    public Task(){}

    public Task(String name, String text, int id )
    {
        super();
        this.name = name;
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return " Task From: "   + name + " description:  " + text ;
    }
}
