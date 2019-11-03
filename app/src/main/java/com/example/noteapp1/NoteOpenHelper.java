package com.example.noteapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.icu.text.Normalizer.NO;
import static com.example.noteapp1.NotesContractClass.*;

public class NoteOpenHelper extends SQLiteOpenHelper {



        public static final String DATABASE_NAME="NOTEKEEPER.db";
        public static final int DATABASE_VERSION=1;

    public NoteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(SQL_CREATE_TABLE);

       Log.d("nimiDebug","Database Created");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


//    ContentValues contentValues=new ContentValues();
//        contentValues.put(TITLECOLUMN,"TITLE10");
//                contentValues.put(DESCRIPTIONCOLUMN,"DESC");
//
//                db.insert(NOTETABLE,null,contentValues);
//                String[] columns={TITLECOLUMN,DESCRIPTIONCOLUMN};
//                Cursor cursor =db.query(NOTETABLE,columns,null,null,null,null,null);
//
//                while(cursor.moveToNext()){
//                String firstTitle=cursor.getString(cursor.getColumnIndex(TITLECOLUMN));
//                Log.d("nimiDebug",firstTitle);
//                }