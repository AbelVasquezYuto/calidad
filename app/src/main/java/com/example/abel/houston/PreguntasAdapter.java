package com.example.abel.houston;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abel.houston.entity.Pregunta;
import com.example.abel.houston.entity.User;

import java.util.List;

/**
 * Created by ABEL on 14/10/2018.
 */

public class PreguntasAdapter extends RecyclerView.Adapter<PreguntasAdapter.PreguntasViewHolder>{

    private List<Pregunta> preguntas;

    public void addList(List<Pregunta> preguntas){
        this.preguntas = preguntas;
    }


    @Override
    public PreguntasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_preguntas_item,parent,false);

        return new PreguntasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PreguntasViewHolder holder, int position) {
        Pregunta pregunta = preguntas.get(position);
        holder.tvUser.setText("Usuario: "+pregunta.getUsuario());
        holder.tvTipo.setText("Tipo de pregunta: "+pregunta.getTipo());
        holder.tvDescripcion.setText("Descripcion: "+pregunta.getDescripcion());

        if(pregunta.getBytes()!=null){
            byte[] foodImage = pregunta.getBytes();
            Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
            holder.ivFOTO.setImageBitmap(bitmap);
        }
        else {
            holder.ivFOTO.setImageResource(R.drawable.camara);
        }


    }

    @Override
    public int getItemCount() {
        return preguntas==null ? 0 : preguntas.size();
    }

    class PreguntasViewHolder extends RecyclerView.ViewHolder{

        TextView tvUser, tvTipo, tvDescripcion;
        //Button btnEditar;
        ImageView ivFOTO;

        public PreguntasViewHolder(View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tv_user_preguntas_item);
            tvTipo = itemView.findViewById(R.id.tv_tipo_preguntas_item);
            tvDescripcion = itemView.findViewById(R.id.tv_descripcion_preguntas_item);
            //btnEditar = itemView.findViewById(R.id.btn_editar);
            ivFOTO = itemView.findViewById(R.id.imageView_preguntas);
        }
    }


}
