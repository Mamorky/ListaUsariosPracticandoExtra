package com.example.listausarios;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.listausarios.data.db.ListaUsuariosOpenHelper;
import com.example.listausarios.data.db.repository.UsuarioRepository;
import com.example.listausarios.data.pojo.Usuario;
import com.example.listausarios.ui.AddEditFragment.AddEditFragmentImp;
import com.example.listausarios.ui.ListFragment.ListarUsuariosViewImp;
import com.example.listausarios.utils.MyContext;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListarUsuariosViewImp.OnFragmentInteractionListener,AddEditFragmentImp.OnFragmentInteractionListener{

    ListarUsuariosViewImp listarUsuariosViewImp;
    AddEditFragmentImp addEditFragmentImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MyContext(this);

        listarUsuariosViewImp = (ListarUsuariosViewImp) getSupportFragmentManager().findFragmentByTag(ListarUsuariosViewImp.TAG);

        if(listarUsuariosViewImp == null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            listarUsuariosViewImp = ListarUsuariosViewImp.newInstance(null);
            fragmentTransaction.add(R.id.frmContainer,listarUsuariosViewImp,ListarUsuariosViewImp.TAG).commit();
        }
    }

    @Override
    public void onOpenAddEdit(Bundle bundle) {
        addEditFragmentImp = (AddEditFragmentImp) getSupportFragmentManager().findFragmentByTag(AddEditFragmentImp.TAG);

        if(addEditFragmentImp == null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            addEditFragmentImp = AddEditFragmentImp.newInstance(bundle);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.frmContainer,addEditFragmentImp,AddEditFragmentImp.TAG).commit();
        }
    }

    @Override
    public void onListUsuarios() {
        getSupportFragmentManager().popBackStack();
    }
}
