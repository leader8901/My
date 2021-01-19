package ru.myandroidhelper.myaccountant.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.myandroidhelper.myaccountant.constans.Constans;

import static ru.myandroidhelper.myaccountant.constans.Constans.DATABASE_NAME_NOTE;
import static ru.myandroidhelper.myaccountant.constans.Constans.DATABASE_VERSION_NOTE;
import static ru.myandroidhelper.myaccountant.constans.Constans.TABLE_NAME_NOTE;

public class NoteDB extends SQLiteOpenHelper {


    private Context context;



    public NoteDB(Context context){
        super(context, DATABASE_NAME_NOTE, null, DATABASE_VERSION_NOTE);
        this.context = context;

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //Таблицв доходов
        String query1 = "CREATE TABLE " + TABLE_NAME_NOTE +
                " (" + Constans.COLUMN_ID_NOTE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constans.COLUMN_TITLE + " TEXT, " +
                Constans.COLUMN_SUBTITLE + " TEXT );";
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_NOTE);
        onCreate(db);

    }
}
