package com.example.listausarios.data.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by mamorky on 20/02/18.
 */

public class Usuario implements Parcelable {
    private int id;
    private String Nombre;
    private String Apellido;
    private Date fechaNac;

    public Usuario(int id, String nombre, String apellido, Date fechaNac) {
        this.id = id;
        Nombre = nombre;
        Apellido = apellido;
        this.fechaNac = fechaNac;
    }

    protected Usuario(Parcel in) {
        id = in.readInt();
        Nombre = in.readString();
        Apellido = in.readString();
        fechaNac = new Date(in.readLong());
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(Nombre);
        dest.writeString(Apellido);
        dest.writeLong(fechaNac.getTime());
    }
}
