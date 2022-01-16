package com.example.projet.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "stade_db";
    public static final String TABLE_STADE = "stade";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_DESCRIPTION = "description";
public static final String COL_CONTINENT="continent";
    public String CREATE_STADE_TABLE ="create table "+TABLE_STADE+"("
            +COL_ID+" integer primary key autoincrement, "
            +COL_NAME+" text, "
            +COL_DESCRIPTION+" text, "+COL_CONTINENT+" text)";

    SQLiteDatabase db;

    public DatabaseHelper(Activity context) {

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STADE_TABLE);
        Log.d("DB","DB created ! ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop TABLE IF EXISTS "+ TABLE_STADE);
        onCreate(db);
    }

    public SQLiteDatabase open(){
        db= this.getWritableDatabase();
        return db;
    }

        public void addAnnonce(Equipe e){
        open();
        ContentValues values=new ContentValues();
        values.put(COL_NAME,e.getNom());
        values.put(COL_DESCRIPTION,e.getDescription());
        values.put(COL_CONTINENT,e.getContinent());
        db.insert(TABLE_STADE,null,values);
    }

    public void updateAnnonce(Equipe e){
        open();
        ContentValues values = new ContentValues();
        values.put(COL_NAME,e.getNom());
        values.put(COL_DESCRIPTION,e.getDescription());
        values.put(COL_CONTINENT,e.getContinent());
        db.update(TABLE_STADE,values,COL_ID+"=?",new String[]{String.valueOf(e.getId())});
    }

    public void removeAnnonce(int id){
        open();
        db.delete(TABLE_STADE,COL_ID+"=?",new String[]{String.valueOf(id)});
    }

    public ArrayList<Equipe> getAllAnnonce(){
        db=this.getReadableDatabase();
        ArrayList<Equipe>List=new ArrayList<Equipe>();
        Cursor c =db.query(TABLE_STADE,null,null,null,null,null,null);
        c.moveToFirst();
        do{
            Equipe s=new Equipe(c.getInt(0),c.getString(1),c.getString(2),c.getString(3));
            List.add(s);
        }while (c.moveToNext());
        return List;
    }
    public int getAnnonceCount(){
        db=this.getReadableDatabase();
        int cpt=0;
        Cursor c=db.query(TABLE_STADE,null,null,null,null,null,null,null);
        cpt=c.getCount();
        return cpt;
    }


}
