package com.example.demo3;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.io.Serializable;


public class Song implements Serializable {
    private String title;
    private String artist;
    private String genre;
    private String length;
    private String fileName;
    public StringProperty getTitle() {
        StringProperty propertyName = new SimpleStringProperty(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ObservableValue<String> getArtist() {
    }

    public ObservableValue<String> getGenre() {
    }

    public StringProperty getDuration() {
        StringProperty propertyLength = new SimpleStringProperty(length);
        return propertyLength;

    }
}
