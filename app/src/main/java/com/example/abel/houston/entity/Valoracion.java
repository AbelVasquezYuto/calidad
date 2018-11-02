package com.example.abel.houston.entity;

/**
 * Created by ABEL on 1/11/2018.
 */

public class Valoracion {

    private String id,idR,user,valor;

    public Valoracion(){

    }

    @Override
    public String toString() {
        return "Valoracion{" +
                "id='" + id + '\'' +
                ", idR='" + idR + '\'' +
                ", user='" + user + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdR() {
        return idR;
    }

    public void setIdR(String idR) {
        this.idR = idR;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
