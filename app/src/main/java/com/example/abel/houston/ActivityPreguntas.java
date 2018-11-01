package com.example.abel.houston;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abel.houston.database.DatabaseManagerPregunta;
import com.example.abel.houston.database.DatabaseManagerUser;
import com.example.abel.houston.entity.Pregunta;
import com.example.abel.houston.entity.User;

import java.util.List;

public class ActivityPreguntas extends AppCompatActivity implements PreguntasAdapter.OnClienteItemClickListener{

    private static final String TAG = "ActivityPreguntas";
    private DatabaseManagerUser databaseManagerUser;
    private DatabaseManagerPregunta databaseManagerPregunta;
    private Spinner spinnerTipoPregunta;
    private String valorTipoPregunta,valorUsuario;
    private Button btnBuscarPregunta;
    private RecyclerView recyclerView;
    private PreguntasAdapter preguntasAdapter;
    private List<Pregunta> preguntas;
    private List<Pregunta> preguntasTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        Bundle b = getIntent().getExtras();

        valorUsuario = b.getString("ValorDelUsuario");

        recyclerView = (RecyclerView) findViewById(R.id.rv_preguntas);

        databaseManagerUser = new DatabaseManagerUser(getApplicationContext());

        btnBuscarPregunta = (Button) findViewById(R.id.btn_buscar_preguntas);
        spinnerTipoPregunta = (Spinner) findViewById(R.id.sp_tipos_preguntas);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.combo_tipos,android.R.layout.simple_spinner_item);

        spinnerTipoPregunta.setAdapter(adapter);

        List<User> userList = databaseManagerUser.getUsuariosList();
        for (User p : userList) {
            Log.i(TAG, p.toString());
        }

        databaseManagerPregunta = new DatabaseManagerPregunta(getApplicationContext());

        preguntasAdapter = new PreguntasAdapter(this);
        preguntasAdapter.addList(preguntas);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(preguntasAdapter);

        btnBuscarPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarPregunta();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        preguntas = databaseManagerPregunta.getPreguntasList();
        preguntasAdapter.addList(preguntas);
    }

    @Override
    public void onItemClick(Pregunta pregunta) {
        String id = pregunta.getId();
        Intent intent = new Intent(getApplicationContext(),VistaPregunta.class);
        intent.putExtra("IDPREGUNTA",id);
        intent.putExtra("nomUsuarioXD",valorUsuario);
        startActivity(intent);
    }

    public void buscarPregunta(){
        valorTipoPregunta = spinnerTipoPregunta.getSelectedItem().toString();

        preguntasTipo = databaseManagerPregunta.getPreguntaPorTipo(valorTipoPregunta);

        if(preguntasTipo.isEmpty()){
            Toast.makeText(this, "No se encontraron preguntas de este tipo", Toast.LENGTH_SHORT).show();
        }
        else{

            final int size = preguntas.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    preguntas.remove(0);
                }
            }

            preguntasAdapter.addList(preguntasTipo);
            Toast.makeText(this, "Filtrado exitoso", Toast.LENGTH_SHORT).show();
        }



    }
}
