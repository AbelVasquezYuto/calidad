package com.example.abel.houston.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ABEL on 29/10/2018.
 */

public abstract class DatabaseManagerR {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public DatabaseManagerR(Context context){
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void cerrar(){
        db.close();
    }

    abstract public void eliminar(String id);
    abstract public void eliminarTodo();
    abstract public Cursor cargarCursor();
    abstract boolean comprobarRegistro(String id);

    public DatabaseHelper getHelper() {
        return helper;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setHelper(DatabaseHelper helper) {
        this.helper = helper;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }




}
