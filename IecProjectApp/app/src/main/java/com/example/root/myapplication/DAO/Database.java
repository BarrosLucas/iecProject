package com.example.root.myapplication.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE = "banco.db";
    public static final String TABLE = "login";
    public static final String ID = "_id";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    private static final int VERSION = 1;


    public Database(Context context) {
        super(context, DATABASE,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE+"("+
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                USER + " TEXT NOT NULL, "+
                PASSWORD + "TEXT NOT NULL)";

        db.execSQL(sql);
        Log.i("VEIO?","SIM");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
