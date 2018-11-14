package com.example.abel.houston;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityGuia extends AppCompatActivity {

    private Button irGuiaPregunta, irGuiaRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia);

        irGuiaPregunta = (Button) findViewById(R.id.btn_ir_agregar_pregunta_guia);
        irGuiaRespuesta = (Button) findViewById(R.id.btn_ir_agregar_respuesta_guia);

        irGuiaPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),GuiaPregunta.class);
                startActivity(intent);
            }
        });

        irGuiaRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),GuiaRespuesta.class);
                startActivity(intent);
            }
        });

    }

    public static class ActivityCanjeAdapter {
    }
}
