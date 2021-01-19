package ru.myandroidhelper.myaccountant.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ru.myandroidhelper.myaccountant.db.MyDatabaseHelper;

public class DbManager {

    private Context context;
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase db;


    public DbManager(Context context){
        this.context = context;
        myDatabaseHelper = new MyDatabaseHelper(context);
    }

    public void OpenDb(){
        db = myDatabaseHelper.getWritableDatabase();
    }
    public void insertToDb(){
       // ContentValues cv = new ContentValues(String title, String disc) {
      //  }

    }
}
