package com.example.abel.houston.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ABEL on 13/10/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NOMBRE = "houston.sqlite";
    private static int DB_SCHEME_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context,DB_NOMBRE,null,DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseManagerUser.CREATE_TABLE);
        sqLiteDatabase.execSQL(DatabaseManagerPregunta.CREATE_TABLE);
        sqLiteDatabase.execSQL(DatabaseManagerRespuesta.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+DB_NOMBRE);
        onCreate(sqLiteDatabase);
    }
}