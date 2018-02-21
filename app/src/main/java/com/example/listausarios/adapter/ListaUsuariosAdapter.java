package com.example.listausarios.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.listausarios.R;
import com.example.listausarios.data.pojo.Usuario;

import java.util.ArrayList;

/**
 * Created by mamorky on 20/02/18.
 */

public class ListaUsuariosAdapter extends ArrayAdapter<Usuario> {

    public ListaUsuariosAdapter(@NonNull Context context) {
        super(context, R.layout.item_persona,new ArrayList<Usuario>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootview = convertView;
        UsuarioHolder holder = new UsuarioHolder();

        if(rootview == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            rootview = inflater.inflate(R.layout.item_persona,null);
            holder = new UsuarioHolder();

            holder.txvNombre = rootview.findViewById(R.id.txvNombre);
            holder.txvApellido = rootview.findViewById(R.id.txvApellido);

            rootview.setTag(holder);
        }
        else
            holder = (UsuarioHolder) rootview.getTag();

        holder.txvNombre.setText(getItem(position).getNombre());
        holder.txvApellido.setText(getItem(position).getApellido());

        return rootview;
    }

    class UsuarioHolder {
        TextView txvNombre;
        TextView txvApellido;
    }
}
