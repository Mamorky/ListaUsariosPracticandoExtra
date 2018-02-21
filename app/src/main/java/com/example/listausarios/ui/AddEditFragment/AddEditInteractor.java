package com.example.listausarios.ui.AddEditFragment;

import android.content.BroadcastReceiver;
import android.content.Intent;

import com.example.listausarios.broadcast.NotifyAddUsuario;
import com.example.listausarios.data.db.repository.UsuarioRepository;
import com.example.listausarios.data.pojo.Usuario;
import com.example.listausarios.ui.AddEditFragment.Contract.AddEditContract;

/**
 * Created by mamorky on 20/02/18.
 */

public class AddEditInteractor implements AddEditContract.interactor{
    @Override
    public void addUsuario(OnAddSucess onAddSucess,Usuario usuario) {
        long id = UsuarioRepository.getInstance().addUser(usuario);

        if(id == -1){
            onAddSucess.onAddError(new Throwable("Se produjo un error al insertar"));
        }
        else
            onAddSucess.onAddSuccess();
    }
}
