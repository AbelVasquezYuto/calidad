package com.example.abel.houston.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * Created by ABEL on 1/11/2018.
 */

public class DatabaseManagerValoracion extends DatabaseManagerV {

    private static final String NOMBRE_TABLA = "demo4";
    private static final String CN_ID = "_id";
    private static final String CN_IDR = "idR";
    private static final String CN_USER = "userV";
    private static final String CN_VALOR = "valorV";

    public static final String CREATE_TABLE = "create table " + NOMBRE_TABLA + " ("
            + CN_ID + " integer PRIMARY KEY AUTOINCREMENT, "
            + CN_IDR + " text NULL, "
            + CN_USER + " text NULL, "
            + CN_VALOR + " text NULL "
            + ");";

    public DatabaseManagerValoracion(Context ctx) {
        super(ctx);
    }

    @Override
    public void cerrar() {
        super.getDb().close();
    }

    private ContentValues generarContentValues(String id, String idR, String userV, String valorV){
        ContentValues valores = new ContentValues();
        valores.put(CN_ID, id);
        valores.put(CN_IDR, idR);
        valores.put(CN_USER, userV);
        valores.put(CN_VALOR, valorV);

        return valores;
    }

    public void insertar_parametros(String id, String idR, String userV, String valorV) {
        Log.d("puntuacion_insertar", super.getDb().insert(NOMBRE_TABLA,null,generarContentValues(id, idR, userV, valorV))+"");
    }

    @Override
    public void eliminar(String id) {

    }

    @Override
    public void eliminarTodo() {

    }

    @Override
    public Cursor cargarCursor() {
        String [] columnas = new String[]{CN_ID, CN_IDR, CN_USER, CN_VALOR};

        return super.getDb().query(NOMBRE_TABLA, columnas, null, null, null, null, null);

    }

    @Override
    public boolean comprobarValoracion(String idR, String user) {
        boolean esta;
        Cursor resultSet = super.getDb().rawQuery("SELECT idR, userV FROM demo4" + " WHERE idR='"+idR+"' AND userV='"+user+"'", null);

        if(resultSet.getCount()<=0){
            esta = false;
        }else{
            esta = true;
        }
        return esta;
    }
}
