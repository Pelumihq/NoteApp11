package com.example.noteapp1;

import android.provider.BaseColumns;

public class NotesContractClass implements BaseColumns {

   public static final String NOTETABLE="note_table";
   public static final String TITLECOLUMN="title_column";
   public static final String DESCRIPTIONCOLUMN="desc_column";

   // create table table_name (firstcolumn, second column );
   public static final String SQL_CREATE_TABLE= "CREATE TABLE " +NOTETABLE+ " ( "+
           _ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            TITLECOLUMN +" TEXT NOT NULL " +", "+
           DESCRIPTIONCOLUMN+ " TEXT NOT NULL " +" );";

}
//DatabaseContract Class
// table
// columns
// strings that we need to create the table

//Database