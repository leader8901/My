package ru.myandroidhelper.myaccountant.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

import ru.myandroidhelper.myaccountant.constans.Constans;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase database;


    public MyDatabaseHelper(Context context) {
        super(context, Constans.DATABASE_NAME, null, Constans.DATABASE_VERSION);
        this.context = context;

    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        //Таблицв доходов
        String query1 = "CREATE TABLE IF NOT EXISTS " + Constans.TABLE_NAME_INCOME +
                " (" + Constans.COLUMN_ID_INCOME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constans.COLUMN_NAME_INCOME + " TEXT, " +
                Constans.COLUMN_INCOME + " INTEGER, " +
                Constans.COLUMN_CATEGORY_INCOME + " TEXT, " +
                Constans.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";
        db.execSQL(query1);
        //Таблица расходов
        String query2 = "CREATE TABLE IF NOT EXISTS " + Constans.TABLE_NAME_EXP +
                " (" + Constans.COLUMN_ID_EXP + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constans.COLUMN_NAME_EXP + " TEXT, " +
                Constans.COLUMN_EXPENSES + " INTEGER, " +
                Constans.COLUMN_CATEGORY_EXP + " TEXT, " +
                Constans.COLUMN_TIMESTAMP2 + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";
        db.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Constans.TABLE_NAME_INCOME);
        db.execSQL("DROP TABLE IF EXISTS " + Constans.TABLE_NAME_EXP);
        onCreate(db);

    }

    //Медот для добавление в таблицу Доход
    public void addIncome(String nameIn, int income, String categoryIn, String getDateTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constans.COLUMN_NAME_INCOME, nameIn);
        cv.put(Constans.COLUMN_INCOME, income);
        cv.put(Constans.COLUMN_CATEGORY_INCOME, categoryIn);
        cv.put(Constans.COLUMN_TIMESTAMP, getDateTime);
        long result = db.insert(Constans.TABLE_NAME_INCOME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Запись добавлено!", Toast.LENGTH_SHORT).show();


        }
        db.close();
    }

    public void addExpenses(String nameExp, int expenses, String  categoryExp, String getDateTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constans.COLUMN_NAME_EXP, nameExp);
        cv.put(Constans.COLUMN_EXPENSES, expenses);
        cv.put(Constans.COLUMN_TIMESTAMP, getDateTime);
        cv.put(Constans.COLUMN_CATEGORY_EXP, categoryExp);
        long result = db.insert(Constans.TABLE_NAME_EXP, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Запись добавлено!", Toast.LENGTH_SHORT).show();


        }
        db.close();
    }

    //Метод для чтение данный из таблицы Расходов
    public Cursor readIncomeData() {
        String query = "SELECT * FROM " + Constans.TABLE_NAME_INCOME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null, null);
        }

        return cursor;


    }

    //Метод для чтение данный из таблицы Доходов
    public Cursor readExpData() {
        String query = "SELECT * FROM " + Constans.TABLE_NAME_EXP;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null, null);
        }
        return cursor;
    }

    //Медот для внесение изменений в таблицу доходов
    public void updateIncomeData(String row_id, String nameIn, String income,String categoryIn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constans.COLUMN_NAME_INCOME, nameIn);
        cv.put(Constans.COLUMN_INCOME, income);
        cv.put(Constans.COLUMN_CATEGORY_INCOME, categoryIn);

        long result = db.update(Constans.TABLE_NAME_INCOME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Удалось", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    //Медот для внесение изменений в таблицу расходов
    public void updateExpData(String row_id, String name2, String expenses ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constans.COLUMN_NAME_INCOME, name2);
        cv.put(Constans.COLUMN_EXPENSES, expenses);
        //cv.put(Constans.COLUMN_CATEGORY_EXP, categoryExp);
        long result = db.update(Constans.TABLE_NAME_EXP, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Удалось изменить!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    public Cursor SumIncome(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT SUM(" +
                (Constans.COLUMN_INCOME) + ") FROM " + Constans.TABLE_NAME_INCOME, null);
        return cur;
    }
    public Cursor SumExp(){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT SUM(" +
                    (Constans.COLUMN_EXPENSES) + ") FROM " + Constans.TABLE_NAME_EXP, null);
            return cur;

    }

       /*public void deleteOneRowIncome(String _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constans.TABLE_NAME_INCOME, Constans.COLUMN_ID_INCOME + "=?", new String[]{String.valueOf(_id)});
        db.close();

    }*/

    // Медот для удаление одной строки из таблицы доходов
    public void deleteOneRowIncome(String _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        long result = db.delete(Constans.TABLE_NAME_INCOME, "_id=?", new String[]{_id});
        db.setTransactionSuccessful();
        db.endTransaction();
        if (result == -1) {
            Toast.makeText(context, "Ошибка.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Удалено.", Toast.LENGTH_SHORT).show();

        }
        db.close();
    }

    // Медот для удаление одной строки из таблицы Расходов
    public void deleteOneRowExp(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(Constans.TABLE_NAME_EXP, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Ошибка.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Удалено.", Toast.LENGTH_SHORT).show();

        }
        db.close();
    }

    public void deleteAllDataIncome() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + Constans.TABLE_NAME_INCOME);
    }

    public void deleteAllDataExp() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + Constans.TABLE_NAME_EXP);
    }


    public ArrayList getAllIncome() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
        // Cursor res =  db.rawQuery( "select * from "+CONTACTS_TABLE_NAME, null );
        // Cursor res =  db.rawQuery( "select (id ||' : '||name  || ' : ' || salary || ' : '|| datetime) AS fullname from "+CONTACTS_TABLE_NAME, null );
        Cursor res = db.rawQuery("select (SUM(income)) AS icnome from " + Constans.TABLE_NAME_INCOME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex("income")));
            res.moveToNext();
        }
        return array_list;
    }

}



       /* SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.execSQL("SELECT SUM(" + Constans.COLUMN_EXPENSES + ") as Total FROM " + Constans.TABLE_NAME_EXP, null);
        if (cursor.moveToFirst()) {
            int total = cursor.getInt(cursor.getColumnIndex("expenses"));
        }
        return cursor;
    }
}*/
