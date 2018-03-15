package com.example.asp.sqlite;

/**
 * Created by asp on 3/15/2018.
 */

public class Data {

    private String name,age;
    private int id;

    public Data() {
    }

    public Data( int id,String name, String age) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public Data(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
