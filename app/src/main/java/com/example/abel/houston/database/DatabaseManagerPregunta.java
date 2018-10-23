package com.example.abel.houston.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.abel.houston.entity.Pregunta;
import com.example.abel.houston.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABEL on 19/10/2018.
 */

public class DatabaseManagerPregunta extends DatabaseManagerP{

    private static final String NOMBRE_TABLA = "demo2";
    private static final String CN_ID = "_id";
    private static final String CN_TIPO = "tipo";
    private static final String CN_DESCRIPCION = "descripcion";
    private static final String CN_IMAGE = "imagen";
    private static final String CN_USER = "usuario";

    public static final String CREATE_TABLE = "create table " + NOMBRE_TABLA + " ("
            + CN_ID + " integer PRIMARY KEY AUTOINCREMENT, "
            + CN_TIPO + " text NULL, "
            + CN_DESCRIPCION + " text NULL, "
            + CN_IMAGE + " BLOB NULL, "
            + CN_USER + " text NULL "
            + ");";

    public DatabaseManagerPregunta(Context context){
        super(context);
    }

    @Override
    public void cerrar() {
        super.getDb().close();
    }

    private ContentValues generarContentValues(String id, String tipo, String descripcion, byte[] imagen, String usuario){
        ContentValues valores = new ContentValues();
        valores.put(CN_ID, id);
        valores.put(CN_TIPO, tipo);
        valores.put(CN_DESCRIPCION, descripcion);
        valores.put(CN_IMAGE, imagen);
        valores.put(CN_USER, usuario);

        return valores;
    }

    public void insertar_parametros(String id, String tipo, String descripcion, byte[] imagen, String usuario) {
        Log.d("pregunta_insertar", super.getDb().insert(NOMBRE_TABLA,null,generarContentValues(id, tipo, descripcion, imagen, usuario))+"");
    }

    @Override
    public void eliminar(String id) {

    }

    @Override
    public void eliminarTodo() {

    }

    @Override
    public Cursor cargarCursor() {
        String [] columnas = new String[]{CN_ID, CN_TIPO, CN_DESCRIPCION, CN_IMAGE, CN_USER};

        return super.getDb().query(NOMBRE_TABLA, columnas, null, null, null, null, null);
    }

    @Override
    boolean comprobarRegistro(String id) {
        return false;
    }

    public List<Pregunta> getPreguntasList(){

        List<Pregunta> list = new ArrayList<>();
        Cursor c = cargarCursor();

        while (c.moveToNext()){
            Pregunta pregunta = new Pregunta();

            pregunta.setId(c.getString(0));
            pregunta.setTipo(c.getString(1));
            pregunta.setDescripcion(c.getString(2));
            pregunta.setBytes(c.getBlob(3));
            pregunta.setUsuario(c.getString(4));
            //usuario.setActive(false);

            list.add(pregunta);
        }

        return list;
    }

    public List<Pregunta> getPreguntaDelUsuario(String usuario){

        List<Pregunta> list = new ArrayList<>();
        Cursor c = cargarCursor();

        while (c.moveToNext()){
            Pregunta pregunta = new Pregunta();

            if(usuario.equalsIgnoreCase(c.getString(4))){
                pregunta.setId(c.getString(0));
                pregunta.setTipo(c.getString(1));
                pregunta.setDescripcion(c.getString(2));
                pregunta.setBytes(c.getBlob(3));
                pregunta.setUsuario(c.getString(4));
                //usuario.setActive(false);

                list.add(pregunta);
            }
        }

        return list;
    }



    public Pregunta getPregunta(String ident){

        Cursor c1 = super.getDb().rawQuery("SELECT _id, tipo, descripcion, imagen, usuario FROM demo2 WHERE _id" + "='" + ident+ "'", null);

        Pregunta pregunta = new Pregunta();

        c1.moveToNext();

        pregunta.setId(c1.getString(0));
        pregunta.setTipo(c1.getString(1));
        pregunta.setDescripcion(c1.getString(2));
        pregunta.setBytes(c1.getBlob(3));
        pregunta.setUsuario(c1.getString(4));

        return pregunta;
    }
}
