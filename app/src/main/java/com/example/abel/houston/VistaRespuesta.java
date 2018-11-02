package com.example.abel.houston;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abel.houston.database.DatabaseManagerRespuesta;
import com.example.abel.houston.database.DatabaseManagerValoracion;
import com.example.abel.houston.entity.Respuesta;

public class VistaRespuesta extends AppCompatActivity {

    private static final String TAG = "VistaRespuesta";
    private String identRespuesta;
    private String valorRespuesta;
    private String elUsuario,elIDR;
    private TextView tvVistaValoracion;
    private TextView tvVistaDescripcion;
    private ImageView imVistaImagen;
    private Respuesta itemRespuesta;
    private DatabaseManagerRespuesta databaseManagerRespuesta;
    private CheckBox checkBox1,checkBox2,checkBox3;
    private Button buttonPuntuacion;

    private DatabaseManagerValoracion databaseManagerValoracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_respuesta);

        tvVistaValoracion = (TextView) findViewById(R.id.tv_vista_puntuacion_respuesta);
        tvVistaDescripcion = (TextView) findViewById(R.id.tv_vista_descripcion_respuesta);
        imVistaImagen = (ImageView) findViewById(R.id.iv_vista_respuesta_imagen);

        checkBox1 = (CheckBox) findViewById(R.id.chb1);
        checkBox2 = (CheckBox) findViewById(R.id.chb2);
        checkBox3 = (CheckBox) findViewById(R.id.chb3);
        buttonPuntuacion = (Button) findViewById(R.id.btn_vista_agregar_puntuacion);

        buttonPuntuacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calificarPuntuacion();
            }
        });

        databaseManagerRespuesta = new DatabaseManagerRespuesta(getApplicationContext());

        Bundle b = getIntent().getExtras();

        identRespuesta = b.getString("IDRESPUESTA");
        elUsuario = b.getString("IdUSUARIO");

        Log.i(TAG,elUsuario);

        itemRespuesta = databaseManagerRespuesta.getRespuesta(identRespuesta);

        tvVistaValoracion.setText(itemRespuesta.getValoracion());
        tvVistaDescripcion.setText(itemRespuesta.getDescripcion());

        elIDR = itemRespuesta.getId();




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

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox1.setChecked(true);
            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox1.setChecked(true);
                checkBox2.setChecked(true);
            }
        });


        valorRespuesta = itemRespuesta.getValoracion();



    }


    public void calificarPuntuacion(){

        int valor = Integer.parseInt(valorRespuesta);

        int p=0;

        databaseManagerValoracion = new DatabaseManagerValoracion(getApplicationContext());

        if(databaseManagerValoracion.comprobarValoracion(elIDR,elUsuario)){

            Toast.makeText(this, "Usted ya califico la respuesta", Toast.LENGTH_SHORT).show();

        }
        else{

            if(checkBox1.isChecked()){
                p=1;
            }

            if(checkBox2.isChecked()){
                checkBox1.setChecked(true);
                p=2;
            }

            if(checkBox3.isChecked()){
                checkBox1.setChecked(true);
                checkBox2.setChecked(true);
                p=valor+3;

            }

            databaseManagerValoracion.insertar_parametros(null,elIDR,elUsuario,p+"");

            valor = valor+p;

            databaseManagerRespuesta.actualizar_parametros(identRespuesta,itemRespuesta.getIdPregunta(),itemRespuesta.getDescripcion(),itemRespuesta.getBytes(),itemRespuesta.getUsuario(),valor+"");

            Toast.makeText(this, "Usted ha calificado la respuesta", Toast.LENGTH_SHORT).show();

        }

    }
}
