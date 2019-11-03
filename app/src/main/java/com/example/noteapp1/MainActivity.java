package com.example.noteapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.noteapp1.NotesContractClass.*;

//  MainActoivity main=new MainActivity
//main
public class MainActivity extends AppCompatActivity {
    RecyclerView noteRecyclerView;
    ImageView newNoteButton;
   static List<Note> noteList=new ArrayList<>();
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteRecyclerView=findViewById(R.id.noteRecyclerView);
        newNoteButton=findViewById(R.id.newNoteButton);

      NoteOpenHelper noteOpenHelper=new NoteOpenHelper(this);

        db = noteOpenHelper.getReadableDatabase();




        newNoteButton.setOnClickListener(
                new ImageView.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                       Intent intent=new Intent(MainActivity.this,   NoteInfoActivity.class);
                       startActivity(intent);
                        }
                }
        );



    }

    @Override
    protected void onStart() {
        super.onStart();
        noteList.clear();
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));

     readNoteItems();
    }

    public void readNoteItems(){
        String[] columnNames={TITLECOLUMN, DESCRIPTIONCOLUMN, _ID};

        Cursor cursor=db.query(NOTETABLE,columnNames,null,null,null,null,null);

        int titlePosition=cursor.getColumnIndex(TITLECOLUMN);
        int descriptionPosition=cursor.getColumnIndex(DESCRIPTIONCOLUMN);
        int IdPosition=cursor.getColumnIndex(_ID);

        while(cursor.moveToNext()){
            String title=cursor.getString(titlePosition);
            String description=cursor.getString(descriptionPosition);
            int id=cursor.getInt(IdPosition);
            Note note=new Note(description,title,id);
            noteList.add(note);
            Log.d("nimiDebug",id+" ");
        }
        noteRecyclerView.setHasFixedSize(true);

        NoteAdapter noteAdapter=new NoteAdapter(this,noteList);

        noteRecyclerView.setAdapter(noteAdapter);

    }
}
