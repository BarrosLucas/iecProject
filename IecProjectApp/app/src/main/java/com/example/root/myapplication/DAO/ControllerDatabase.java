package com.example.root.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.root.myapplication.Model.Login;

public class ControllerDatabase {
    private SQLiteDatabase db;
    private Database database;

    public ControllerDatabase(Context context){
        database = new Database(context);
    }

    public boolean insertLogin(String username, String password){
        ContentValues values;
        long result;

        db = database.getWritableDatabase();
        values = new ContentValues();
        values.put(Database.USER, username);
        values.put(Database.PASSWORD, password);

        result = db.insert(Database.TABLE, null, values);
        db.close();

        if (result ==-1)
            return false;
        else
            return true;

    }

    public Login loadData() {
        Cursor cursor;
        String[] fields = {Database.ID, Database.USER, Database.PASSWORD};
        db = database.getReadableDatabase();
        cursor = db.query(Database.TABLE, fields, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        else{
            return null;
        }
        db.close();

        Login login = new Login();
        login.setIdLogin(cursor.getInt(cursor.getColumnIndex(Database.ID)));
        login.setUsername(cursor.getString(cursor.getColumnIndex(Database.USER)));
        login.setPassword(cursor.getString(cursor.getColumnIndex(Database.PASSWORD)));


        return login;
    }

    public void delete(){
        Login login = loadData();
        String where = Database.ID + "=" + login.getIdLogin();
        db = database.getReadableDatabase();
        db.delete(database.TABLE,where,null);
        db.close();

    }

}
