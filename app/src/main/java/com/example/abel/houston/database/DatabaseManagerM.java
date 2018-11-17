package com.example.abel.houston.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ABEL on 16/11/2018.
 */

public abstract class DatabaseManagerM {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public DatabaseManagerM(Context context){
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void cerrar(){
        db.close();
    }

    abstract public void eliminar(String id);
    abstract public void eliminarTodo();
    abstract public Cursor cargarCursor();
    abstract boolean comprobarExisteUsuario(String user);

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
