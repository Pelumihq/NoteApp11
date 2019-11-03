package com.example.noteapp1;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MyFirstTest {

    @Test
    public void createMultipleNotes(){
        Note note=new Note("description","title1");
        Note newNote=new Note("description2","title2");
        List<Note> noteList=new ArrayList<>();

        noteList.add(note);
        noteList.add(newNote);

        Note note1=noteList.get(0);
        Note note2=noteList.get(1);
        note1.getTitle();


        Assert.assertEquals(note1.getTitle(),"title1");
        Assert.assertEquals(note1.getDescription(),"description");

        Assert.assertEquals(note2.getDescription(),"description2");
    }
}
