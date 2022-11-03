package com.example.androistudio_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {

    public Helper(Context context) {
        super(context , "ManagerComputer.db",null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table category(categoryId text primary key ,nameCategory text)");
        sqLiteDatabase.execSQL("create table computer(idComputer text primary key , nameComputer text ,price text ," +
                " categoryId text constraint categoryId references category(categoryId))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public Boolean insertValueCategory(String categoryId , String nameCategory){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("categoryId" , categoryId);
        contentValues.put("nameCategory" , nameCategory);
        long result = db.insert("category" ,null ,contentValues);
        if(result == - 1){
            return false ;
        }else{
            return true ;
        }
    }

    public Boolean insertValuecomputer(String idComputer , String nameComputer ,String price , String categoryId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idComputer" , idComputer);
        contentValues.put("nameComputer" , nameComputer);
        contentValues.put("price" , price);
        contentValues.put("categoryId" , categoryId);
        long result = db.insert("computer" ,null ,contentValues);
        if(result == - 1){
            return false ;
        }else{
            return true ;
        }
    }

    public Boolean updateValuecomputer(String idComputer , String nameComputer ,String price , String categoryId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameComputer" , nameComputer);
        contentValues.put("price" , price);
        contentValues.put("categoryId" , categoryId);
        Cursor cursor = db.rawQuery("select * from computer where idComputer=?",new String[]{idComputer});

        if(cursor.getCount() > 0){
            long result = db.update("computer" ,contentValues ,"idComputer=?" , new String[]{idComputer});
            if(result == - 1){
                return false ;
            }else{
                return true ;
            }}else {
            return false ;
        }
    }

    public Boolean deleteValuecomputer(String idComputer ){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from computer where idComputer=?",new String[]{idComputer});

        if(cursor.getCount() > 0){
            long result = db.delete("computer"  ,"idComputer=?" , new String[]{idComputer});
            if(result == - 1){
                return false ;
            }else{
                return true ;
            }}else {
            return false ;
        }
    }

    public Cursor getvalueComputer(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from computer ",null);
        return cursor ;
    }
}
