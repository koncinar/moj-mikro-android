package com.example;

import java.util.Date;

public class Message {
    private String author;
    private Date date;
    private int longitude;
    private int latitude;
    private String message;

    public Message(String author, Date date, int latitude, int longitude, String message) {
        this.author = author;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public String getMessage() {
        return message;
    }
}