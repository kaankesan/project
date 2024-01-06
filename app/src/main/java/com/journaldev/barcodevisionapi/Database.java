package com.journaldev.barcodevisionapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String databaseName = "sqlite.db";

    public Database(@Nullable Context context) {
        super(context,"sqlite.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table kullanıcılar(isim TEXT primary key, password TEXT,doğrusayısı TEXT,yanlışsayısı TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists kullanıcılar");
    }
    public boolean veriEkle(String name, String password){
        SQLiteDatabase Database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("isim",name);
        contentValues.put("password",password);
        long result = Database.insert("kullanıcılar",null,contentValues);


        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkName(String name){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from kullanıcılar where isim = ?",new String[]{name});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkPassword(String name,String password){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from kullanıcılar where password = ? and password = ?",new String[]{name,password});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}
