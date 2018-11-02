package com.example.abel.houston.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.abel.houston.entity.Pregunta;
import com.example.abel.houston.entity.Respuesta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABEL on 29/10/2018.
 */

public class DatabaseManagerRespuesta extends DatabaseManagerR{

    private static final String NOMBRE_TABLA = "demo3";
    private static final String CN_ID = "_id";
    private static final String CN_ID_P = "idP";
    private static final String CN_DESCRIPCION = "descripcion";
    private static final String CN_IMAGE = "imagen";
    private static final String CN_USER = "usuario";
    private static final String CN_VALORACION = "valoracion";

    public static final String CREATE_TABLE = "create table " + NOMBRE_TABLA + " ("
            + CN_ID + " integer PRIMARY KEY AUTOINCREMENT, "
            + CN_ID_P + " text NULL, "
            + CN_DESCRIPCION + " text NULL, "
            + CN_IMAGE + " BLOB NULL, "
            + CN_USER + " text NULL, "
            + CN_VALORACION + " text NULL "
            + ");";

    public DatabaseManagerRespuesta(Context context){
        super(context);
    }

    @Override
    public void cerrar() {
        super.getDb().close();
    }

    private ContentValues generarContentValues(String id, String idP, String descripcion, byte[] imagen, String usuario,String valoracion){
        ContentValues valores = new ContentValues();
        valores.put(CN_ID, id);
        valores.put(CN_ID_P, idP);
        valores.put(CN_DESCRIPCION, descripcion);
        valores.put(CN_IMAGE, imagen);
        valores.put(CN_USER, usuario);
        valores.put(CN_VALORACION, valoracion);

        return valores;
    }

    public void insertar_parametros(String id, String idP, String descripcion, byte[] imagen, String usuario, String valoracion) {
        Log.d("respuesta_insertar", super.getDb().insert(NOMBRE_TABLA,null,generarContentValues(id, idP, descripcion, imagen, usuario,valoracion))+"");
    }

    public void actualizar_parametros(String id, String idP, String descripcion, byte[] imagen, String usuario, String valoracion) {

        ContentValues valores = new ContentValues();
        valores.put(CN_ID, id);
        valores.put(CN_ID_P, idP);
        valores.put(CN_DESCRIPCION, descripcion);
        valores.put(CN_IMAGE, imagen);
        valores.put(CN_USER, usuario);
        valores.put(CN_VALORACION, valoracion);

        String [] args = new String[]{id};

        Log.d("actualizar", super.getDb().update(NOMBRE_TABLA, valores,"_ID=?", args)+"");
    }



    @Override
    public void eliminar(String id) {

    }

    @Override
    public void eliminarTodo() {

    }

    @Override
    public Cursor cargarCursor() {
        String [] columnas = new String[]{CN_ID, CN_ID_P, CN_DESCRIPCION, CN_IMAGE, CN_USER, CN_VALORACION};

        return super.getDb().query(NOMBRE_TABLA, columnas, null, null, null, null, null);
    }

    @Override
    boolean comprobarRegistro(String id) {
        return false;
    }

    public List<Respuesta> getRespuestaAlaPregunta(String idP){

        List<Respuesta> list = new ArrayList<>();
        Cursor c = cargarCursor();

        while (c.moveToNext()){
            Respuesta respuesta = new Respuesta();

            if(idP.equalsIgnoreCase(c.getString(1))){
                respuesta.setId(c.getString(0));
                respuesta.setIdPregunta(c.getString(1));
                respuesta.setDescripcion(c.getString(2));
                respuesta.setBytes(c.getBlob(3));
                respuesta.setUsuario(c.getString(4));
                respuesta.setValoracion(c.getString(5));
                //usuario.setActive(false);

                list.add(respuesta);
            }
        }

        return list;
    }

    public Respuesta getRespuesta(String ident){

        Cursor c1 = super.getDb().rawQuery("SELECT _id, idP, descripcion, imagen, usuario, valoracion FROM demo3 WHERE _id" + "='" + ident+ "'", null);

        Respuesta respuesta = new Respuesta();

        c1.moveToNext();

        respuesta.setId(c1.getString(0));
        respuesta.setIdPregunta(c1.getString(1));
        respuesta.setDescripcion(c1.getString(2));
        respuesta.setBytes(c1.getBlob(3));
        respuesta.setUsuario(c1.getString(4));
        respuesta.setValoracion(c1.getString(5));

        return respuesta;
    }


}
