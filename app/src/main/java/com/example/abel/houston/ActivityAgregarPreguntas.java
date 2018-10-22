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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abel.houston.database.DatabaseManagerPregunta;

import java.io.ByteArrayOutputStream;

public class ActivityAgregarPreguntas extends AppCompatActivity {

    private static final String TAG = "ActivityAgregarPreguntas";
    private Spinner spinnerTipo;
    private Button buttonAgregar;
    private EditText etDescripcion;
    private String stDescripcion;
    private String valorUsuario;
    private String valorTipo;
    private ImageView imageView;
    private DatabaseManagerPregunta managerPregunta;
    private int request_code = 1;
    private Bitmap bitmap_foto;
    private RoundedBitmapDrawable roundedBitmapDrawable;
    private byte[] bytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_preguntas);

        Bundle b = getIntent().getExtras();

        valorUsuario = b.getString("ValorDelUsuario");


        imageView = (ImageView) findViewById(R.id.iv_agregar_pregunta);
        spinnerTipo = (Spinner) findViewById(R.id.sp_tipos);
        etDescripcion = (EditText) findViewById(R.id.et_descripcion_pregunta);
        buttonAgregar = (Button) findViewById(R.id.btn_agregar_preguntasXD);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.combo_tipos,android.R.layout.simple_spinner_item);

        spinnerTipo.setAdapter(adapter);

        Log.i(TAG,valorUsuario);

        bitmap_foto = BitmapFactory.decodeResource(getResources(),R.drawable.camara);
        roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap_foto);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);

        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarPregunta();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
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

    private void registrarPregunta(){

        if (!validarPregunta()) return;

        stDescripcion = etDescripcion.getText().toString();
        valorTipo = spinnerTipo.getSelectedItem().toString();

        final ProgressDialog progressDialog = new ProgressDialog(ActivityAgregarPreguntas.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        managerPregunta = new DatabaseManagerPregunta(this);
        //managerPregunta.insertar_parametros(null,valorTipo,stDescripcion,bytes,valorUsuario);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                            managerPregunta.insertar_parametros(null,valorTipo,stDescripcion,bytes,valorUsuario);
                            //String mesg = String.format("%s ha sido guardado en la BBDD", sNombre);
                            String mesg = "Se ha registrado su pregunta";
                            Toast.makeText(getBaseContext(),mesg, Toast.LENGTH_LONG).show();
                            finish();
                            progressDialog.dismiss();

                    }
                }, 3000);

    }

    private boolean validarPregunta(){
        boolean valid = true;

        String descripcion = etDescripcion.getText().toString();

        if (descripcion.isEmpty() || descripcion.length() < 3) {
            etDescripcion.setError("Ingrese al menos 3 caracteres");
            valid = false;
        } else {
            etDescripcion.setError(null);
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
            imageView.setImageURI(data.getData());
            bytes = imageToByte(imageView);

            // para que se vea la imagen en circulo
            Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
            roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
            roundedBitmapDrawable.setCircular(true);
            imageView.setImageDrawable(roundedBitmapDrawable);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
