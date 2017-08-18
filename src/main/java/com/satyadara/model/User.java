package com.satyadara.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private String age;


    public User(){}
    public User(long id, String name, String age)   {
        this.setId(id);
        this.setName(name);
        this.setAge(age);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
