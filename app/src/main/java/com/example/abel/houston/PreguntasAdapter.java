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
    private OnClienteItemClickListener onClienteItemClick;

    public PreguntasAdapter(OnClienteItemClickListener onClienteItemClick){
        this.onClienteItemClick =onClienteItemClick;
    }

    public void addList(List<Pregunta> preguntas){
        this.preguntas = preguntas;
        notifyDataSetChanged();
    }


    @Override
    public PreguntasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_preguntas_item,parent,false);

        return new PreguntasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PreguntasViewHolder holder, int position) {
        final Pregunta pregunta = preguntas.get(position);

        holder.tvUser.setText(pregunta.getUsuario());
        holder.tvTipo.setText(pregunta.getTipo());
        holder.tvDescripcion.setText(pregunta.getDescripcion());

        if(pregunta.getBytes()!=null){
            byte[] foodImage = pregunta.getBytes();
            Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
            holder.ivFOTO.setImageBitmap(bitmap);
        }
        else {
            holder.ivFOTO.setImageResource(R.drawable.camara);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClienteItemClick!=null){
                    onClienteItemClick.onItemClick(pregunta);
                }
            }
        });

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

    public interface OnClienteItemClickListener{
        void onItemClick(Pregunta pregunta);// metodo para seleccionar la pregunta
        //void onEditarClienteClick(Cliente cliente);//metodo para el click del boton editar
    }

}
