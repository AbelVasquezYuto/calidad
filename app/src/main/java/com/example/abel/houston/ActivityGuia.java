package com.example.abel.houston;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.abel.houston.database.DatabaseManagerPregunta;
import com.example.abel.houston.database.DatabaseManagerUser;
import com.example.abel.houston.entity.Pregunta;
import com.example.abel.houston.entity.User;

public class ActivityGuia extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DatabaseManagerPregunta databaseManagerPregunta;
    private Pregunta itemPregunta;
    private String ident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia);

        databaseManagerPregunta = new DatabaseManagerPregunta(getApplicationContext());

        itemPregunta = databaseManagerPregunta.getPregunta("1"); // encuentra al usuario registrado en la bbdd
        Log.i(TAG,itemPregunta.toString());

    }
}
