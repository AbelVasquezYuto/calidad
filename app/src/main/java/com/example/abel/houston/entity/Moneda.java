package com.example.abel.houston.entity;

/**
 * Created by ABEL on 16/11/2018.
 */

public class Moneda {

    private String id,user,monedas;

    public Moneda(){

    }

    @Override
    public String toString() {
        return "Moneda{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", monedas='" + monedas + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMonedas() {
        return monedas;
    }

    public void setMonedas(String monedas) {
        this.monedas = monedas;
    }
}
