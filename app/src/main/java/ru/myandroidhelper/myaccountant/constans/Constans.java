package ru.myandroidhelper.myaccountant.constans;

public class Constans {


    // Название БД и версия
    public static final String DATABASE_NAME = "MyReport.db";
    public static final int DATABASE_VERSION = 15;


    //Таблица Доходов
    public static final String TABLE_NAME_INCOME = "ReportIn";
    public static final String COLUMN_ID_INCOME = "_id";
    public static final String COLUMN_NAME_INCOME = "nameIn";
    public static final String COLUMN_INCOME = "income";
    public static final String COLUMN_CATEGORY_INCOME = "categoryIn";
    public static final String COLUMN_TIMESTAMP2 = "date";


    //Таблица Расходов
    public static final String TABLE_NAME_EXP = "ReportExp";
    public static final String COLUMN_ID_EXP = "_id";
    public static final String COLUMN_NAME_EXP = "nameExp";
    public static final String COLUMN_EXPENSES = "expenses";
    public static final String COLUMN_CATEGORY_EXP = "categoryExp";
    public static final String COLUMN_TIMESTAMP = "date";
    public static final String COLUMN_SUM_EXPENSES = "sum";


    //Название БД заметок
    public static final String DATABASE_NAME_NOTE = "NOTE.db";
    public static final int DATABASE_VERSION_NOTE = 1;

    //ТАблица Заметок
    public static final String TABLE_NAME_NOTE = "noteDB";
    public static final String COLUMN_ID_NOTE = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_SUBTITLE = "subtitle";


    public static final String COLUMN_NOTE = "Заметки";




}
