package com.example.listausarios.data.db.repository.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.listausarios.data.db.ListaUsuariosContract;
import com.example.listausarios.data.db.ListaUsuariosOpenHelper;
import com.example.listausarios.data.pojo.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mamorky on 20/02/18.
 */

public class UsuarioDao {
    public ArrayList<Usuario> getAll(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        SQLiteDatabase db = ListaUsuariosOpenHelper.getInstance().openDatabase();

        Cursor cursor = db.query(ListaUsuariosContract.usuario_entries.TABLE_NAME,ListaUsuariosContract.usuario_entries.ALL_COLUMNS,null,null,null,null,null);

        if(cursor.moveToFirst()){
            do {
                Usuario usuario = new Usuario(cursor.getInt(0),cursor.getString(1),cursor.getString(2),new Date(cursor.getString(3)));
                usuarios.add(usuario);
            }while(cursor.moveToNext());
        }
        ListaUsuariosOpenHelper.getInstance().closeDatabase();

        return usuarios;
    }

    public long addUser(Usuario usuario) {
        SQLiteDatabase db = ListaUsuariosOpenHelper.getInstance().openDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ListaUsuariosContract.usuario_entries.COLUMN_NAME,usuario.getNombre());
        contentValues.put(ListaUsuariosContract.usuario_entries.COLUMN_APELLIDO,usuario.getApellido());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        contentValues.put(ListaUsuariosContract.usuario_entries.COLUMN_FECHA_NAC,simpleDateFormat.format(usuario.getFechaNac()));

        long id = db.insert(ListaUsuariosContract.usuario_entries.TABLE_NAME,null,contentValues);
        ListaUsuariosOpenHelper.getInstance().closeDatabase();
        return id;
    }

    public long deleteUser(Usuario usuario) {
        SQLiteDatabase db = ListaUsuariosOpenHelper.getInstance().openDatabase();

        String whereClause = ListaUsuariosContract.usuario_entries._ID+"=?";
        String[] whereArgs = {String.valueOf(usuario.getId())};

        long row = db.delete(ListaUsuariosContract.usuario_entries.TABLE_NAME,whereClause,whereArgs);

        ListaUsuariosOpenHelper.getInstance().closeDatabase();
        return row;
    }
}
