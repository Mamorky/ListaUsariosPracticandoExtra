package com.example.listausarios.data.db.repository;

import com.example.listausarios.data.db.repository.dao.UsuarioDao;
import com.example.listausarios.data.pojo.Usuario;

import java.util.ArrayList;

/**
 * Created by mamorky on 20/02/18.
 */

public class UsuarioRepository {
    private static UsuarioRepository mInstance;
    private UsuarioDao mDao;

    private UsuarioRepository(){
        this.mDao = new UsuarioDao();
    }

    public static UsuarioRepository getInstance(){
        if(mInstance == null)
            mInstance = new UsuarioRepository();

        return mInstance;
    }

    public ArrayList<Usuario> getUsuarios(){
        return mDao.getAll();
    }
    public long addUser(Usuario usuario){return mDao.addUser(usuario);}

    public long deleteUser(Usuario usuario){return mDao.deleteUser(usuario);}
}
