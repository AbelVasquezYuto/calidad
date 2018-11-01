package com.example.abel.houston;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abel.houston.database.DatabaseManagerRespuesta;
import com.example.abel.houston.entity.Respuesta;

public class VistaRespuesta extends AppCompatActivity {

    private String identRespuesta;
    private TextView tvVistaValoracion;
    private TextView tvVistaDescripcion;
    private ImageView imVistaImagen;
    private Respuesta itemRespuesta;
    private DatabaseManagerRespuesta databaseManagerRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_respuesta);

        tvVistaValoracion = (TextView) findViewById(R.id.tv_vista_puntuacion_respuesta);
        tvVistaDescripcion = (TextView) findViewById(R.id.tv_vista_descripcion_respuesta);
        imVistaImagen = (ImageView) findViewById(R.id.iv_vista_respuesta_imagen);

        databaseManagerRespuesta = new DatabaseManagerRespuesta(getApplicationContext());

        Bundle b = getIntent().getExtras();

        identRespuesta = b.getString("IDRESPUESTA");

        itemRespuesta = databaseManagerRespuesta.getRespuesta(identRespuesta);

        tvVistaValoracion.setText(itemRespuesta.getValoracion());
        tvVistaDescripcion.setText(itemRespuesta.getDescripcion());

        Bitmap bitmapsinfoto = BitmapFactory.decodeResource(getResources(),R.drawable.camara);
        RoundedBitmapDrawable roundedBitmapDrawablesinfoto = RoundedBitmapDrawableFactory.create(getResources(), bitmapsinfoto);
        roundedBitmapDrawablesinfoto.setCircular(true);

        imVistaImagen.setImageDrawable(roundedBitmapDrawablesinfoto);

        if(itemRespuesta.getBytes()!=null){
            byte[] foodImage = itemRespuesta.getBytes();
            Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);

            imVistaImagen.setImageBitmap(bitmap);

            /*
            Bitmap bitmap2 = ((BitmapDrawable)imagePregunta.getDrawable()).getBitmap();
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap2);
            roundedBitmapDrawable.setCircular(true);

            imagePregunta.setImageDrawable(roundedBitmapDrawable);*/
        }

    }
}
