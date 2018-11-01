package com.example.abel.houston;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abel.houston.database.DatabaseManagerPregunta;
import com.example.abel.houston.database.DatabaseManagerRespuesta;
import com.example.abel.houston.database.DatabaseManagerUser;
import com.example.abel.houston.entity.Pregunta;
import com.example.abel.houston.entity.Respuesta;
import com.example.abel.houston.entity.User;

import java.util.List;

public class VistaPregunta extends AppCompatActivity implements RespuestasAdapter.OnRespuestaItemClickListener{

    private static final String TAG = "VistaPregunta";
    private DatabaseManagerPregunta databaseManagerPregunta;
    private DatabaseManagerRespuesta databaseManagerRespuesta;
    private Pregunta itemPregunta;
    private String identPregunta;
    private String idUsuarioPregunta;
    private ImageView imagePregunta;
    private Button btnIrAgregarRespuesta;
    private TextView textView;
    private RecyclerView recyclerViewR;
    private RespuestasAdapter respuestasAdapter;
    List<Respuesta> respuestas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_pregunta);

        imagePregunta = (ImageView) findViewById(R.id.iv_vista_pregunta_imagen);
        textView = (TextView) findViewById(R.id.tv_vista_descripcion_pregunta);
        btnIrAgregarRespuesta = (Button) findViewById(R.id.btn_vista_agregar_respuesta_pregunta);
        recyclerViewR = (RecyclerView) findViewById(R.id.rv_respuestas);

        Bundle b = getIntent().getExtras();

        identPregunta = b.getString("IDPREGUNTA");

        databaseManagerPregunta = new DatabaseManagerPregunta(getApplicationContext());

        itemPregunta = databaseManagerPregunta.getPregunta(identPregunta); // encuentra la pregunta registrado en la bbdd

        textView.setText(itemPregunta.getDescripcion());

        idUsuarioPregunta = b.getString("nomUsuarioXD");

        Bitmap bitmapsinfoto = BitmapFactory.decodeResource(getResources(),R.drawable.camara);
        RoundedBitmapDrawable roundedBitmapDrawablesinfoto = RoundedBitmapDrawableFactory.create(getResources(), bitmapsinfoto);
        roundedBitmapDrawablesinfoto.setCircular(true);

        imagePregunta.setImageDrawable(roundedBitmapDrawablesinfoto);

        if(itemPregunta.getBytes()!=null){
            byte[] foodImage = itemPregunta.getBytes();
            Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);

            imagePregunta.setImageBitmap(bitmap);

            /*
            Bitmap bitmap2 = ((BitmapDrawable)imagePregunta.getDrawable()).getBitmap();
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap2);
            roundedBitmapDrawable.setCircular(true);

            imagePregunta.setImageDrawable(roundedBitmapDrawable);*/
        }

        btnIrAgregarRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityAgregarRespuestas.class);
                intent.putExtra("IdPREGUNTA",identPregunta);
                intent.putExtra("IdUSUARIO",idUsuarioPregunta);
                startActivity(intent);
            }
        });


        databaseManagerRespuesta = new DatabaseManagerRespuesta(getApplicationContext());

        respuestasAdapter = new RespuestasAdapter(this);
        respuestasAdapter.addListRespuesta(respuestas);


        recyclerViewR.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewR.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewR.setAdapter(respuestasAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        respuestas = databaseManagerRespuesta.getRespuestaAlaPregunta(identPregunta);
        respuestasAdapter.addListRespuesta(respuestas);
    }

    @Override
    public void onItemClickRespuesta(Respuesta respuesta) {
        String id = respuesta.getId();
        Intent intent = new Intent(getApplicationContext(),VistaRespuesta.class);
        intent.putExtra("IDRESPUESTA",id);
        startActivity(intent);
    }
}
