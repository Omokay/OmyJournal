package com.example.david.omy_journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dbTable";
    public static final String TABLE_NAME = "journalDb";
    public static final int VERSION = 1;


    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String BODY = "body";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbTable = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                TITLE + " TEXT," +
                BODY + " TEXT)";
        db.execSQL(dbTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dbTable = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dbTable);
    }

    public boolean addEntry(String title, String body) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, title);
        cv.put(BODY, body);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllEntries() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


}
