package com.cifpcesarmanrique.HolaMundo.controllers.Models;

public class persona {

    private int id;

    private String name;

    private int age;

    private String email;

    public persona() {}

    public persona(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }


}
