package com.example.abel.houston;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.abel.houston.entity.Canje;

import java.util.ArrayList;

public class ActivityCanje extends AppCompatActivity {

    ArrayList<Canje> listCanje;
    RecyclerView recyclerCanje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canje);

        listCanje = new ArrayList<>();
        recyclerCanje = (RecyclerView) findViewById(R.id.recyclerid);
        recyclerCanje.setLayoutManager(new LinearLayoutManager(this));

        llenarCanje();

        ActivityCanjeAdapter adapter = new ActivityCanjeAdapter(listCanje);
        recyclerCanje.setAdapter(adapter);
    }

    private void llenarCanje(){
        listCanje.add(new Canje("Solucionario 2019-I", "ABD: Ciencias de la Salud, Ciencias Básica & Ciencias Económicas y de la Gestión", R.drawable.uno));
        listCanje.add(new Canje("Solucionario 2019-I", "CE: Ingenierías & Humanidades y Ciencias Jurídicas", R.drawable.uno));

        listCanje.add(new Canje("Solucionario 2018-II", "ABD: Ciencias de la Salud, Ciencias Básica y Ciencias Económicas de la Gestión", R.drawable.dos));
        listCanje.add(new Canje("Solucionario 2018-II", "CE: Ingenierías & Humanidades y Ciencias Jurídicas", R.drawable.dos));

        listCanje.add(new Canje("Solucionario 2018-I", "ABD: Ciencias de la Salud, Ciencias Básica y Ciencias Económicas de la Gestión", R.drawable.tres));
        listCanje.add(new Canje("Solucionario 2018-I", "CE: Ingenierías & Humanidades y Ciencias Jurídicas", R.drawable.tres));

        listCanje.add(new Canje("Solucionario 2017-II", "ABD: Ciencias de la Salud, Ciencias Básica y Ciencias Económicas de la Gestión", R.drawable.cuatro));
        listCanje.add(new Canje("Solucionario 2017-II", "CE: Ingenierías & Humanidades y Ciencias Jurídicas", R.drawable.cuatro));

        listCanje.add(new Canje("Solucionario 2017-I", "ABD: Ciencias de la Salud, Ciencias Básica y Ciencias Económicas de la Gestión", R.drawable.cinco));
        listCanje.add(new Canje("Solucionario 2017-I", "CE: Ingenierías & Humanidades y Ciencias Jurídicas", R.drawable.cinco));

        listCanje.add(new Canje("Solucionario 2016-II", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial", R.drawable.seis));
        listCanje.add(new Canje("Solucionario 2016-II", "BCE: Humanidades, Ciencias Sociales & Ingenierías", R.drawable.seis));

        listCanje.add(new Canje("Solucionario 2016-I", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial", R.drawable.siete));
        listCanje.add(new Canje("Solucionario 2016-I", "BCE: Humanidades, Ciencias Sociales & Ingenierías", R.drawable.siete));

        listCanje.add(new Canje("Solucionario 2015-II", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial", R.drawable.ocho));
        listCanje.add(new Canje("Solucionario 2015-II", "BCE: Humanidades, Ciencias Sociales & Ingenierías", R.drawable.ocho));

        listCanje.add(new Canje("Solucionario 2015-I", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial", R.drawable.diez));
        listCanje.add(new Canje("Solucionario 2015-I", "BCE: Humanidades, Ciencias Sociales & Ingenierías", R.drawable.nueve));

        listCanje.add(new Canje("Solucionario 2014-II", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial", R.drawable.doce));
        listCanje.add(new Canje("Solucionario 2014-II", "BCE: Humanidades, Ciencias Sociales & Ingenierías", R.drawable.once));

        listCanje.add(new Canje("Solucionario 2014-I", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial", R.drawable.catorce));
        listCanje.add(new Canje("Solucionario 2014-I", "BCE: Humanidades, Ciencias Sociales & Ingenierías", R.drawable.trece));

        listCanje.add(new Canje("Solucionario 2013-II", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial", R.drawable.dieciseis));
        listCanje.add(new Canje("Solucionario 2013-II", "BCE: Humanidades, Ciencias Sociales & Ingenierías", R.drawable.quince));

        listCanje.add(new Canje("Solucionario 2013-I", "ADF/BCE: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial / Humanidades, Ciencias Sociales & Ingenierías", R.drawable.diecisiete));

        listCanje.add(new Canje("Solucionario 2012-II", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial", R.drawable.diecinueve));
        listCanje.add(new Canje("Solucionario 2012-II", "BCE: Humanidades, Ciencias Sociales & Ingenierías", R.drawable.dieciocho));

        listCanje.add(new Canje("Solucionario 2012-I", "ADF/BCE: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial / Humanidades, Ciencias Sociales & Ingenierías", R.drawable.veinte));

        listCanje.add(new Canje("Solucionario 2011-II", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial", R.drawable.veintidos));
        listCanje.add(new Canje("Solucionario 2011-II", "BCE: Humanidades, Ciencias Sociales & Ingenierías", R.drawable.veintiuno));
    }
}
