package com.example.abel.houston.entity;

import java.util.Arrays;

/**
 * Created by ABEL on 19/10/2018.
 */

public class Pregunta {

    private String id,tipo,descripcion,usuario;
    private byte[] bytes;

    public Pregunta(){

    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", usuario='" + usuario + '\'' +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
}
