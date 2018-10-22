package com.example.abel.houston;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.abel.houston.database.DatabaseManagerPregunta;
import com.example.abel.houston.database.DatabaseManagerUser;
import com.example.abel.houston.entity.Pregunta;
import com.example.abel.houston.entity.User;

import java.util.List;

public class ActivityPreguntas extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DatabaseManagerUser databaseManagerUser;
    private DatabaseManagerPregunta databaseManagerPregunta;
    private RecyclerView recyclerView;
    private PreguntasAdapter preguntasAdapter;
    private List<Pregunta> preguntas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        recyclerView = (RecyclerView) findViewById(R.id.rv_preguntas);

        databaseManagerUser = new DatabaseManagerUser(getApplicationContext());

        List<User> userList = databaseManagerUser.getUsuariosList();
        for (User p : userList) {
            Log.i(TAG, p.toString());
        }

        databaseManagerPregunta = new DatabaseManagerPregunta(getApplicationContext());
        /*
        List<Pregunta> preguntaList = databaseManagerPregunta.getPreguntasList();
        for (Pregunta e : preguntaList) {
            Log.i(TAG, e.toString());
        }*/

        preguntasAdapter = new PreguntasAdapter();
        preguntasAdapter.addList(preguntas);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(preguntasAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        preguntas = databaseManagerPregunta.getPreguntasList();
        preguntasAdapter.addList(preguntas);

    }
}
