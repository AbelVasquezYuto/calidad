package com.example.abel.houston;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.abel.houston.database.DatabaseManagerPregunta;
import com.example.abel.houston.entity.Pregunta;

import java.util.List;

public class ActivityMisPreguntas extends AppCompatActivity implements PreguntasAdapter.OnClienteItemClickListener{

    private static final String TAG = "ActivityMisPreguntas";
    private DatabaseManagerPregunta databaseManagerPregunta;
    private RecyclerView recyclerView;
    private PreguntasAdapter preguntasAdapter;
    private String valorDelUsuario;
    private List<Pregunta> preguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_preguntas);

        recyclerView = (RecyclerView) findViewById(R.id.rv_mis_preguntas);

        Bundle b = getIntent().getExtras();

        valorDelUsuario = b.getString("ValorDelUsuario");

        databaseManagerPregunta = new DatabaseManagerPregunta(getApplicationContext());

        /*
        preguntas = databaseManagerPregunta.getPreguntaDelUsuario(valorDelUsuario);
        for (Pregunta e : preguntas) {
            Log.i(TAG, e.toString());
        }*/

        preguntasAdapter = new PreguntasAdapter(this);
        preguntasAdapter.addList(preguntas);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(preguntasAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        preguntas = databaseManagerPregunta.getPreguntaDelUsuario(valorDelUsuario);
        preguntasAdapter.addList(preguntas);

    }

    @Override
    public void onItemClick(Pregunta pregunta) {

    }
}
