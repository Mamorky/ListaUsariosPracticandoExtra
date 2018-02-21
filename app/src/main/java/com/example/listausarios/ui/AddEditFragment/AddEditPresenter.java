package com.example.listausarios.ui.AddEditFragment;

import com.example.listausarios.data.pojo.Usuario;
import com.example.listausarios.ui.AddEditFragment.Contract.AddEditContract;

/**
 * Created by mamorky on 20/02/18.
 */

public class AddEditPresenter implements AddEditContract.presenter,AddEditContract.interactor.OnAddSucess{
    private AddEditContract.view view;
    private AddEditContract.interactor interactor;

    public AddEditPresenter(AddEditContract.view view){
        this.view = view;
        interactor = new AddEditInteractor();
    }

    @Override
    public void addUsuario(Usuario usuario) {
        interactor.addUsuario(this,usuario);
    }

    @Override
    public void onAddSuccess() {
        view.onAddSuccess();
    }

    @Override
    public void onAddError(Throwable throwable) {
        view.onAddError(throwable);
    }
}
