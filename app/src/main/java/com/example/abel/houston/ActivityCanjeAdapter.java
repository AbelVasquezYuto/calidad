package com.example.abel.houston;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abel.houston.entity.Canje;

import java.util.ArrayList;

public class ActivityCanjeAdapter extends RecyclerView.Adapter<ActivityCanjeAdapter.ViewHolderDatos> {

    ArrayList<Canje> listCanje;

    public ActivityCanjeAdapter(ArrayList<Canje> listCanje) {
        this.listCanje = listCanje;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_canje_item, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
        holder.nombre.setText(listCanje.get(position).getNombre());
        holder.descripcion.setText(listCanje.get(position).getInfo());
        holder.foto.setImageResource(listCanje.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listCanje.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView nombre, descripcion;
        ImageView foto;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.idNombre);
            descripcion = (TextView) itemView.findViewById(R.id.idInfo);
            foto = (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
