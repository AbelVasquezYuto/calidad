package com.example.abel.houston;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.abel.houston.database.DatabaseManagerPregunta;
import com.example.abel.houston.database.DatabaseManagerRespuesta;

import java.io.ByteArrayOutputStream;

public class ActivityAgregarRespuestas extends AppCompatActivity {

    private static final String TAG = "ActivityAgregarRespuestas";
    private ImageView imageViewR;
    private Button btnAgregarRespuestasR;
    private EditText etAgregarDescripcionR;
    private String idPregunta,nomUsuario;
    private String stDescripcionR;
    private DatabaseManagerRespuesta databaseManagerRespuesta;
    private int request_code = 1;
    private Bitmap bitmap_foto;
    private RoundedBitmapDrawable roundedBitmapDrawable;
    private byte[] bytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_respuestas);

        imageViewR = (ImageView) findViewById(R.id.iv_agregar_respuesta);
        etAgregarDescripcionR = (EditText) findViewById(R.id.et_descripcion_respuesta);
        btnAgregarRespuestasR = (Button) findViewById(R.id.btn_agregar_respuestasXD);

        Bundle b = getIntent().getExtras();

        idPregunta = b.getString("IdPREGUNTA");
        nomUsuario = b.getString("IdUSUARIO");

        btnAgregarRespuestasR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarRespuesta();
            }
        });

        bitmap_foto = BitmapFactory.decodeResource(getResources(),R.drawable.camara);
        roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap_foto);
        roundedBitmapDrawable.setCircular(true);
        imageViewR.setImageDrawable(roundedBitmapDrawable);

        imageViewR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                //verificacion de la version de plataforma
                if(Build.VERSION.SDK_INT < 19){
                    //android 4.3  y anteriores
                    i = new Intent();
                    i.setAction(Intent.ACTION_GET_CONTENT);
                }else {
                    //android 4.4 y superior
                    i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                }
                i.setType("image/*");
                startActivityForResult(i, request_code);
            }
        });

    }

    public void agregarRespuesta(){
        if (!validarRespuesta()) return;

        stDescripcionR = etAgregarDescripcionR.getText().toString();

        Log.i(TAG,idPregunta);
        Log.i(TAG,nomUsuario);
        Log.i(TAG,stDescripcionR);

        final ProgressDialog progressDialog = new ProgressDialog(ActivityAgregarRespuestas.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        databaseManagerRespuesta = new DatabaseManagerRespuesta(this);


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        databaseManagerRespuesta.insertar_parametros(null,idPregunta,stDescripcionR,bytes,nomUsuario,"0");
                        //managerPregunta.insertar_parametros(null,valorTipo,stDescripcion,bytes,valorUsuario);
                        String mesg = "Se ha registrado la respuesta";
                        Toast.makeText(getBaseContext(),mesg, Toast.LENGTH_LONG).show();
                        finish();
                        progressDialog.dismiss();

                    }
                }, 3000);
    }

    public boolean validarRespuesta(){
        boolean valid = true;

        String descripcion = etAgregarDescripcionR.getText().toString();

        if (descripcion.isEmpty() || descripcion.length() < 3) {
            etAgregarDescripcionR.setError("Ingrese al menos 3 caracteres");
            valid = false;
        } else {
            etAgregarDescripcionR.setError(null);
        }

        return valid;
    }

    private byte[] imageToByte(ImageView image) {
        Bitmap bitmapFoto = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapFoto.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == request_code){
            imageViewR.setImageURI(data.getData());
            bytes = imageToByte(imageViewR);

            // para que se vea la imagen en circulo
            Bitmap bitmap = ((BitmapDrawable)imageViewR.getDrawable()).getBitmap();
            roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
            roundedBitmapDrawable.setCircular(true);
            imageViewR.setImageDrawable(roundedBitmapDrawable);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
