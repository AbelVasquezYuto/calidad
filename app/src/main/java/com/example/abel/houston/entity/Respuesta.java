package com.example.abel.houston.entity;

import java.util.Arrays;

/**
 * Created by ABEL on 29/10/2018.
 */

public class Respuesta {

    private String id,idPregunta,descripcion,usuario;
    private byte[] bytes;
    private String valoracion;

    public Respuesta(){

    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "id='" + id + '\'' +
                ", idPregunta='" + idPregunta + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", usuario='" + usuario + '\'' +
                ", bytes=" + Arrays.toString(bytes) +
                ", valoracion=" + valoracion +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(String idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }
}
