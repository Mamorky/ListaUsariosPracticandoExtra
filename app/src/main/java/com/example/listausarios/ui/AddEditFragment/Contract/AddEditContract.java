package com.example.listausarios.ui.AddEditFragment.Contract;

import com.example.listausarios.data.pojo.Usuario;

/**
 * Created by mamorky on 20/02/18.
 */

public class AddEditContract {
    public interface view{
        void onAddSuccess();
        void onAddError(Throwable throwable);
    }

    public interface presenter{
        void addUsuario(Usuario usuario);
    }

    public interface interactor{
        void addUsuario(OnAddSucess onAddSucess,Usuario usuario);
        interface OnAddSucess{
            void onAddSuccess();
            void onAddError(Throwable throwable);
        }
    }
}
