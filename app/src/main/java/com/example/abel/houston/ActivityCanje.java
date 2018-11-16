package com.example.abel.houston;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.abel.houston.entity.Canje;

import java.util.ArrayList;

public class ActivityCanje extends AppCompatActivity {

    ArrayList<Canje> listCanje;
    RecyclerView recyclerCanje;
    DownloadManager dm;
    long queveid;
    String nombreURLs="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canje);

        listCanje = new ArrayList<>();
        recyclerCanje = (RecyclerView) findViewById(R.id.recyclerid);
        recyclerCanje.setLayoutManager(new LinearLayoutManager(this));

        llenarCanje();

        ActivityCanjeAdapter adapter = new ActivityCanjeAdapter(listCanje);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = "";
                nombre = listCanje.get(recyclerCanje.getChildAdapterPosition(view)).getUrlCanje();
                descargarSolucionario(nombre);

            }
        });

        recyclerCanje.setAdapter(adapter);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)){
                    DownloadManager.Query req_query = new DownloadManager.Query();
                    req_query.setFilterById(queveid);
                }
            }
        };

        registerReceiver(receiver,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    private void llenarCanje(){
        listCanje.add(new Canje("Solucionario 2019-I", "ABD: Ciencias de la Salud, Ciencias Básica & Ciencias Económicas y de la Gestión", "http://cloud.vallejo.com.pe/Sol_SM_2019-I%20(Res)Saw6sJDKHeIq.pdf",R.drawable.uno));
        listCanje.add(new Canje("Solucionario 2019-I", "CE: Ingenierías & Humanidades y Ciencias Jurídicas","http://cloud.vallejo.com.pe/Sol%20SM%202019-I%20(Res)NuzGKOJTX2wu.pdf", R.drawable.uno));

        listCanje.add(new Canje("Solucionario 2018-II", "ABD: Ciencias de la Salud, Ciencias Básica y Ciencias Económicas de la Gestión", "http://cloud.vallejo.com.pe/SabadoWF1SRYdt34FQsP.pdf",R.drawable.dos));
        listCanje.add(new Canje("Solucionario 2018-II", "CE: Ingenierías & Humanidades y Ciencias Jurídicas", "http://cloud.vallejo.com.pe/UNMSMxE6YEeC8PmJ.pdf",R.drawable.dos));

        listCanje.add(new Canje("Solucionario 2018-I", "ABD: Ciencias de la Salud, Ciencias Básica y Ciencias Económicas de la Gestión", "http://cloud.vallejo.com.pe/Sabado%202018-IL3wMIlXEaURv.pdf",R.drawable.tres));
        listCanje.add(new Canje("Solucionario 2018-I", "CE: Ingenierías & Humanidades y Ciencias Jurídicas", "http://cloud.vallejo.com.pe/Domingo%202018-Irmmfqne9GLu.pdf",R.drawable.tres));


        listCanje.add(new Canje("Solucionario 2017-II", "ABD: Ciencias de la Salud, Ciencias Básica y Ciencias Económicas de la Gestión", "http://cloud.vallejo.com.pe/SOLUCIONARIO%20SABADO%20SM%202017-IIkdIzTU5URBBa.pdf",R.drawable.cuatro));
        listCanje.add(new Canje("Solucionario 2017-II", "CE: Ingenierías & Humanidades y Ciencias Jurídicas","http://cloud.vallejo.com.pe/SOLUCIONARIO%20DOMINGO%20SM%202017-IIjvj6jHNd8bZJ.pdf", R.drawable.cuatro));

        listCanje.add(new Canje("Solucionario 2017-I", "ABD: Ciencias de la Salud, Ciencias Básica y Ciencias Económicas de la Gestión", "http://cloud.vallejo.com.pe/sabado-web-1MYj4C0fA8oBz.pdf",R.drawable.cinco));
        listCanje.add(new Canje("Solucionario 2017-I", "CE: Ingenierías & Humanidades y Ciencias Jurídicas","http://cloud.vallejo.com.pe/DOMINGO-web-1sXRA0rAXReyK.pdf", R.drawable.cinco));

        listCanje.add(new Canje("Solucionario 2016-II", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial", "http://cloud.vallejo.com.pe/SABADO-web-1j2TaydHBrVN.pdf",R.drawable.seis));
        listCanje.add(new Canje("Solucionario 2016-II", "BCE: Humanidades, Ciencias Sociales & Ingenierías","http://cloud.vallejo.com.pe/1-110-webnmwWxkQc4i2c.pdf", R.drawable.seis));

        listCanje.add(new Canje("Solucionario 2016-I", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial","http://cloud.vallejo.com.pe/SABADO%20COLOR%20PDF-webxpwiZzS4pAHD.pdf", R.drawable.siete));
        listCanje.add(new Canje("Solucionario 2016-I", "BCE: Humanidades, Ciencias Sociales & Ingenierías","http://cloud.vallejo.com.pe/solucionario-webhMM4hctGSP8W.pdf", R.drawable.siete));

        listCanje.add(new Canje("Solucionario 2015-II", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial", "http://cloud.vallejo.com.pe/unmsm%20sabadoqqFLsrjXbGKF.pdf",R.drawable.ocho));
        listCanje.add(new Canje("Solucionario 2015-II", "BCE: Humanidades, Ciencias Sociales & Ingenierías", "http://cloud.vallejo.com.pe/unmsm%20domingo4pDj9bCR9XgX.pdf",R.drawable.ocho));

        listCanje.add(new Canje("Solucionario 2015-I", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial", "http://cloud.vallejo.com.pe/SabadofIPbEbWdajs.pdf",R.drawable.diez));
        listCanje.add(new Canje("Solucionario 2015-I", "BCE: Humanidades, Ciencias Sociales & Ingenierías", "http://cloud.vallejo.com.pe/DomingoQWKFu0fOHeei.pdf",R.drawable.nueve));

        listCanje.add(new Canje("Solucionario 2014-II", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial","http://cloud.vallejo.com.pe/Sol%20UNMSM%202014-II_ADEKRdGsF56kkL.pdf", R.drawable.doce));
        listCanje.add(new Canje("Solucionario 2014-II", "BCE: Humanidades, Ciencias Sociales & Ingenierías", "http://cloud.vallejo.com.pe/Sol%20UNMSM%202014-II_BCF8Khde8Xp7pHC.pdf",R.drawable.once));

        listCanje.add(new Canje("Solucionario 2014-I", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial","http://cloud.vallejo.com.pe/Sol%20UNMSM%202014-I_ADEJJUxhxP1DKIe.pdf", R.drawable.catorce));
        listCanje.add(new Canje("Solucionario 2014-I", "BCE: Humanidades, Ciencias Sociales & Ingenierías", "http://cloud.vallejo.com.pe/Sol%20UNMSM%202014-I_BCFxphh2arkAxG.pdf",R.drawable.trece));

        listCanje.add(new Canje("Solucionario 2013-II", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial", "http://cloud.vallejo.com.pe/SabadoDSHAV6wkwVI8.pdf",R.drawable.dieciseis));
        listCanje.add(new Canje("Solucionario 2013-II", "BCE: Humanidades, Ciencias Sociales & Ingenierías", "http://cloud.vallejo.com.pe/DomingoIAxdSw0jsQe0.pdf",R.drawable.quince));

        listCanje.add(new Canje("Solucionario 2013-I", "ADF/BCE: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial / Humanidades, Ciencias Sociales & Ingenierías", "http://cloud.vallejo.com.pe/DOMINGO0LbUIW1aPAoo.pdf",R.drawable.diecisiete));

        listCanje.add(new Canje("Solucionario 2012-II", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial", "http://cloud.vallejo.com.pe/SabadoTgs5xGvL7pQc.pdf",R.drawable.diecinueve));
        listCanje.add(new Canje("Solucionario 2012-II", "BCE: Humanidades, Ciencias Sociales & Ingenierías", "http://cloud.vallejo.com.pe/DomingoNvKe5DdzHUm8.pdf",R.drawable.dieciocho));

        listCanje.add(new Canje("Solucionario 2012-I", "ADF/BCE: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial / Humanidades, Ciencias Sociales & Ingenierías", "http://cloud.vallejo.com.pe/DOMINGOGvCgRMGLHDq8.pdf",R.drawable.veinte));

        listCanje.add(new Canje("Solucionario 2011-II", "ADF: Ciencias de la Salud, Ciencias Básicas & Economía Empresarial","http://cloud.vallejo.com.pe/SabadoZyjsIzZdPUQe.pdf", R.drawable.veintidos));
        listCanje.add(new Canje("Solucionario 2011-II", "BCE: Humanidades, Ciencias Sociales & Ingenierías","http://cloud.vallejo.com.pe/DomingoUnFkqgCdWJy.pdf", R.drawable.veintiuno));

    }

    public void descargarSolucionario(String nombreURL){

        nombreURLs = nombreURL;
        AlertDialog.Builder alerDialogBuilder = new AlertDialog.Builder(this);
        alerDialogBuilder.setMessage("Desea descargar el solucionario")
                .setCancelable(false)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(nombreURLs));
                        queveid = dm.enqueue(request);
                        Toast.makeText(ActivityCanje.this, "Se ha descargado el solucionario", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        AlertDialog alertDialog = alerDialogBuilder.create();
        alertDialog.show();

    }



}
