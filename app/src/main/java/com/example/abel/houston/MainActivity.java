package com.example.abel.houston;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abel.houston.database.DatabaseManagerPregunta;
import com.example.abel.houston.database.DatabaseManagerUser;
import com.example.abel.houston.entity.Pregunta;
import com.example.abel.houston.entity.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DatabaseManagerUser databaseManagerUser;
    private DatabaseManagerPregunta databaseManagerPregunta;
    private User itemUsuario;
    private String ident;
    private TextView textUser;
    private TextView textCorreo;
    private ImageView imagenUser;
    private Button buttonCanje;
    private Button buttonPreguntas;
    private Button buttonGuia;
    private Button buttonMisPreguntas;
    private Button irAgregarPreguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textUser = (TextView) findViewById(R.id.tv_nombre_usuario_main);
        textCorreo = (TextView) findViewById(R.id.tv_correo_main);
        imagenUser = (ImageView) findViewById(R.id.imageView_main);

        Bundle b = getIntent().getExtras();

        ident = b.getString("IDENT");

        databaseManagerUser= new DatabaseManagerUser(getApplicationContext());

        itemUsuario = databaseManagerUser.getUsuario(ident); // encuentra al usuario registrado en la bbdd

        textUser.setText(itemUsuario.getNombre());
        textCorreo.setText(itemUsuario.getCorreo());

        Bitmap bitmapsinfoto = BitmapFactory.decodeResource(getResources(),R.drawable.imagen);
        RoundedBitmapDrawable roundedBitmapDrawablesinfoto = RoundedBitmapDrawableFactory.create(getResources(), bitmapsinfoto);
        roundedBitmapDrawablesinfoto.setCircular(true);

        imagenUser.setImageDrawable(roundedBitmapDrawablesinfoto);

        if(itemUsuario.getBytes()!=null){
            byte[] foodImage = itemUsuario.getBytes();
            Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);

            imagenUser.setImageBitmap(bitmap);

            Bitmap bitmap2 = ((BitmapDrawable)imagenUser.getDrawable()).getBitmap();
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap2);
            roundedBitmapDrawable.setCircular(true);

            imagenUser.setImageDrawable(roundedBitmapDrawable);
        }

        buttonPreguntas = (Button) findViewById(R.id.m_btn_preguntas);
        buttonCanje = (Button) findViewById(R.id.m_btn_canje);
        buttonMisPreguntas = (Button) findViewById(R.id.m_btn_mis_preguntas);
        buttonGuia = (Button) findViewById(R.id.m_btn_guia);
        irAgregarPreguntas = (Button) findViewById(R.id.btn_ir_a_agregar);

        buttonPreguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String elCorreo = (String) textCorreo.getText();

                Intent intent = new Intent(getApplicationContext(),ActivityPreguntas.class);
                intent.putExtra("ValorDelCorreo",elCorreo);
                startActivity(intent);
            }
        });

        buttonCanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityCanje.class);
                startActivity(intent);
            }
        });

        buttonMisPreguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String elUsuario = (String) textUser.getText();
                Intent intent = new Intent(getApplicationContext(),ActivityMisPreguntas.class);
                intent.putExtra("ValorDelUsuario",elUsuario);
                startActivity(intent);
            }
        });


        buttonGuia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityGuia.class);
                startActivity(intent);
            }
        });

        irAgregarPreguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String elUsuario = (String) textUser.getText();
                Intent intent = new Intent(getApplicationContext(),ActivityAgregarPreguntas.class);
                intent.putExtra("ValorDelUsuario",elUsuario);
                startActivity(intent);
            }
        });

        databaseManagerPregunta = new DatabaseManagerPregunta(getApplicationContext());

        List<User> userList =databaseManagerUser.getUsuariosList();
        for (User p: userList){
            Log.i(TAG,p.toString());
        }

        List<Pregunta> preguntaList = databaseManagerPregunta.getPreguntasList();
        for (Pregunta e: preguntaList){
            Log.i(TAG,e.toString());
        }

    }
}
