package com.example.noteapp1;

import java.util.ArrayList;
import java.util.List;

public class Note {
private String description;
private String title;
private int id;
public Note(String description, String title, int id){
    this.description = description;
    this.title=title;
    this.id=id;
}

    public String getDescription() {

    return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


