package com.example.abel.houston;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abel.houston.entity.Respuesta;

import java.util.List;

/**
 * Created by ABEL on 29/10/2018.
 */

public class RespuestasAdapter extends RecyclerView.Adapter<RespuestasAdapter.RespuestasViewHolder> {

    private List<Respuesta> respuestas;
    private RespuestasAdapter.OnRespuestaItemClickListener onRespuestaItemClick;

    public RespuestasAdapter(OnRespuestaItemClickListener onRespuestaItemClick){
        this.onRespuestaItemClick = onRespuestaItemClick;
    }

    public void addListRespuesta(List<Respuesta> respuestas){
        this.respuestas = respuestas;
        notifyDataSetChanged();
    }

    @Override
    public RespuestasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_respuestas_item,parent,false);
        return new RespuestasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RespuestasViewHolder holder, int position) {
        final Respuesta respuesta = respuestas.get(position);

        holder.tvUserR.setText(respuesta.getUsuario());
        holder.tvValorR.setText(respuesta.getValoracion());
        holder.tvDescripcionR.setText(respuesta.getDescripcion());

        if(respuesta.getBytes()!=null){
            byte[] foodImage = respuesta.getBytes();
            Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
            holder.ivFOTOR.setImageBitmap(bitmap);
        }
        else {
            holder.ivFOTOR.setImageResource(R.drawable.camara);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onRespuestaItemClick!=null){
                    onRespuestaItemClick.onItemClickRespuesta(respuesta);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return respuestas==null ? 0 : respuestas.size();
    }

    class RespuestasViewHolder extends RecyclerView.ViewHolder{

        TextView tvUserR, tvValorR, tvDescripcionR;
        //Button btnEditar;
        ImageView ivFOTOR;

        public RespuestasViewHolder(View itemView) {
            super(itemView);
            tvUserR = itemView.findViewById(R.id.tv_user_respuestas_item);
            tvValorR = itemView.findViewById(R.id.tv_valor_respuestas_item);
            tvDescripcionR = itemView.findViewById(R.id.tv_descripcion_respuestas_item);

            ivFOTOR = itemView.findViewById(R.id.imageView_respuestas_item);
        }
    }

    public interface OnRespuestaItemClickListener{
        void onItemClickRespuesta(Respuesta respuesta);
    }

}
