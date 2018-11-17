package com.example.abel.houston.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.abel.houston.entity.Moneda;

/**
 * Created by ABEL on 16/11/2018.
 */

public class DatabaseManagerMonedas extends DatabaseManagerM{

    private static final String NOMBRE_TABLA = "demo5";
    private static final String CN_ID = "_id";
    private static final String CN_USER = "userM";
    private static final String CN_VALOR = "valorM";

    public static final String CREATE_TABLE = "create table " + NOMBRE_TABLA + " ("
            + CN_ID + " integer PRIMARY KEY AUTOINCREMENT, "
            + CN_USER + " text NULL, "
            + CN_VALOR + " text NULL "
            + ");";

    @Override
    public void cerrar() {
        super.getDb().close();
    }

    public DatabaseManagerMonedas(Context ctx) {
        super(ctx);
    }

    private ContentValues generarContentValues(String id, String userM, String valorM){
        ContentValues valores = new ContentValues();
        valores.put(CN_ID, id);
        valores.put(CN_USER, userM);
        valores.put(CN_VALOR, valorM);

        return valores;
    }

    public void insertar_parametros(String id, String userM, String valorM) {
        Log.d("monedas insertar", super.getDb().insert(NOMBRE_TABLA,null,generarContentValues(id,userM, valorM))+"");
    }

    public void actualizar_parametros(String id, String userM, String valorM) {

        ContentValues valores = new ContentValues();
        valores.put(CN_ID, id);
        valores.put(CN_USER, userM);
        valores.put(CN_VALOR, valorM);

        String [] args = new String[]{id};

        Log.d("actualizar monedas", super.getDb().update(NOMBRE_TABLA, valores,"_ID=?", args)+"");
    }


    @Override
    public void eliminar(String id) {

    }

    @Override
    public void eliminarTodo() {

    }

    @Override
    public Cursor cargarCursor() {
        String [] columnas = new String[]{CN_ID, CN_USER, CN_VALOR};

        return super.getDb().query(NOMBRE_TABLA, columnas, null, null, null, null, null);

    }

    @Override
    public boolean comprobarExisteUsuario(String user) {
        boolean esta;
        Cursor resultSet = super.getDb().rawQuery("SELECT userM FROM demo5" + " WHERE userM='"+user+"'", null);

        if(resultSet.getCount()<=0){
            esta = false;
        }else{
            esta = true;
        }
        return esta;
    }

    public Moneda getMoneda(String userM){

        Cursor c1 = super.getDb().rawQuery("SELECT _id, userM, valorM FROM demo5 WHERE userM='"+userM+"'", null);

        Moneda moneda = new Moneda();

        c1.moveToNext();

        moneda.setId(c1.getString(0));
        moneda.setUser(c1.getString(1));
        moneda.setMonedas(c1.getString(2));

        return moneda;
    }


}
