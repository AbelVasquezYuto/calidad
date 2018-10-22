package com.example.abel.houston.entity;

import java.util.Arrays;

/**
 * Created by ABEL on 13/10/2018.
 */

public class User {

    private byte[] bytes;
    private String id, correo, password, nombre;
    private boolean active;

    public User() {
    }

    public User(boolean active) {
        this.active = active;
    }

    public User(String id, String correo, String password, String nombre) {
        this.id = id;
        this.correo = correo;
        this.password = password;
        this.nombre = nombre;
    }

    public User(String id, String correo, String password,byte[] bytes, String nombre) {
        this.bytes = bytes;
        this.id = id;
        this.correo = correo;
        this.password = password;
        this.nombre = nombre;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", bytes=" + Arrays.toString(bytes) + '\'' +
                ", active=" + active +
                '}';
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
