package com.example.noteapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.noteapp1.MainActivity.noteList;
import static com.example.noteapp1.NotesContractClass.*;

public class NoteInfoActivity extends AppCompatActivity {
    EditText enterTitle;
    EditText enterText;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_info);
        enterText =findViewById(R.id.enterTextId);
        enterTitle =findViewById(R.id.enterTitleId);

        position = getIntent().getIntExtra("position",-1);

        if(position ==-1){
            Log.d("nimiDebug","The value of position is "+ position);
            return;
        }
        Note note=noteList.get(position);

        String title=note.getTitle();
        String description=note.getDescription();

        enterText.setText(description);
        enterTitle.setText(title);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.menu_save){
          saveNote();
           finish();
        }else if(id==R.id.delete_button){
            deleteNote();
        }else if(id==R.id.menu_share){
            shareNote(enterTitle,enterText);
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareNote(EditText title, EditText description ) {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_SUBJECT,title.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT,description.getText().toString());
        if(intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        }
    }

    private void deleteNote() {
        NoteOpenHelper noteOpenHelper=new NoteOpenHelper(this);
        SQLiteDatabase db=noteOpenHelper.getWritableDatabase();
        int id=noteList.get(position).getId();
        String where=_ID +" = ?" ;
        String[] whereArgs={String.valueOf(id)};
        db.delete(NOTETABLE,where,whereArgs);
        finish();
    }

    public void saveNote(){
        if(position==-1){
            createNewNote();
        }else{
            updateNote();
        }
//        String text= enterText.getText().toString();
//        String title= enterTitle.getText().toString();
//        Note note=new Note(text,title);
//        noteList.add(note);

    }

    public void createNewNote(){
        NoteOpenHelper noteOpenHelper=new NoteOpenHelper(this);

        SQLiteDatabase db=noteOpenHelper.getWritableDatabase();

        String text= enterText.getText().toString();
        String title= enterTitle.getText().toString();

        ContentValues contentValues=new ContentValues();
        contentValues.put(TITLECOLUMN,title);
        contentValues.put(DESCRIPTIONCOLUMN,text);

        db.insert(NOTETABLE,null,contentValues);

    }

    public void updateNote(){
        NoteOpenHelper noteOpenHelper=new NoteOpenHelper(this);
        SQLiteDatabase db=noteOpenHelper.getWritableDatabase();

        String text= enterText.getText().toString();
        String title= enterTitle.getText().toString();
        int id=noteList.get(position).getId();

        ContentValues contentValues=new ContentValues();
        contentValues.put(TITLECOLUMN,title);
        contentValues.put(DESCRIPTIONCOLUMN,text);

        String where=_ID +" = ?" ;
        String[] whereArgs={String.valueOf(id)};
        int val=     db.update(NOTETABLE,contentValues,where,whereArgs);
        Log.d("nimiDebug",val+" ");

    }
    public void onDesttoy(){
        Toast.makeText(this, "New Branch", Toast.LENGTH_SHORT).show();
    }
}
//DatabaseContract Class
    // table
  // columns
  // strings that we need to create the table

//Database Class