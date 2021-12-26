package com.dians.lab.dianslab.model;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee_user")
public class User {

    @Id
    String username;

    String name;

    String surname;

    String password;

    String id_library;

    public User(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public User(){

    }
}