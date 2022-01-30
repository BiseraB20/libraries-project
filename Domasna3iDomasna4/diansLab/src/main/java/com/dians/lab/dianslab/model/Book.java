package com.dians.lab.dianslab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer counter;

    private String authorName;

    private Integer counter_read;

    @ManyToMany
    private List<Library> libraries;

    public Book( String name, String description, Integer counter, String authorName, List<Library> libraries, Integer counter_read) {
        this.name = name;
        this.description = description;
        this.counter = counter;
        this.authorName = authorName;
        this.libraries = libraries;
        this.counter_read = counter_read;
    }

    public Book() {

    }
}
