package com.example.purshaselist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "purshase.db";
    public static final String DATABASE_TABLE = "purshase";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCT = "PRODUCT";
    public static final String COLUMN_AMOUNT= "AMOUNT";
    public static final String COLUMN_TYPE = "TYPE";
    public static final String COLUMN_COST = "COST";
    public static final String COLUMN_CATEGORY = "CATEGORY";
    public static final String COLUMN_DATE = "DATA";
    public static final int DATABASE_VERSION = 1;


    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        LocalDate today = LocalDate.now();
        String createQuery;
        createQuery = "CREATE TABLE " + DATABASE_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCT + " text not null, " +
                COLUMN_AMOUNT + " TEXT not null, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_COST + " TEXT not null, " +
                COLUMN_CATEGORY + " TEXT not null default \"продукт\"," +
                COLUMN_DATE + "TEXT DEFAULT " +"(" + today +")" + ")";
        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }
}
