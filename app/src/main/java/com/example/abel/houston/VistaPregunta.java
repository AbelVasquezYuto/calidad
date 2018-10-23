package com.example.abel.houston;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abel.houston.database.DatabaseManagerPregunta;
import com.example.abel.houston.database.DatabaseManagerUser;
import com.example.abel.houston.entity.Pregunta;
import com.example.abel.houston.entity.User;

public class VistaPregunta extends AppCompatActivity {

    private static final String TAG = "VistaPregunta";
    private DatabaseManagerPregunta databaseManagerPregunta;
    private Pregunta itemPregunta;
    private String identPregunta;
    private ImageView imagePregunta;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_pregunta);

        imagePregunta = (ImageView) findViewById(R.id.iv_vista_pregunta_imagen);
        textView = (TextView) findViewById(R.id.tv_vista_descripcion_pregunta);

        Bundle b = getIntent().getExtras();

        identPregunta = b.getString("IDPREGUNTA");

        databaseManagerPregunta = new DatabaseManagerPregunta(getApplicationContext());

        itemPregunta = databaseManagerPregunta.getPregunta(identPregunta); // encuentra la pregunta registrado en la bbdd

        textView.setText(itemPregunta.getDescripcion());

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


    }
}
