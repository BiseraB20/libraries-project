package com.dians.lab.dianslab.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class Library {
    @Id
    private String id;
    private boolean isReadingRoom;
    private Integer counter;
    private String name;
    private String lat;
    private String lon;
    private String email;

    public Library(String id, boolean isReadingRoom, Integer counter, String name, String lat, String lon, String email) {
        this.id = id;
        this.isReadingRoom = isReadingRoom;
        this.counter = counter;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.email = email;
    }


    public Library() {

    }
}
