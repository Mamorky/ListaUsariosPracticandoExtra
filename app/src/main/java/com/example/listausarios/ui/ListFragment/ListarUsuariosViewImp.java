package com.example.listausarios.ui.ListFragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listausarios.R;
import com.example.listausarios.adapter.ListaUsuariosAdapter;
import com.example.listausarios.data.db.repository.UsuarioRepository;
import com.example.listausarios.data.pojo.Usuario;
import com.example.listausarios.ui.AddEditFragment.AddEditFragmentImp;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListarUsuariosViewImp.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListarUsuariosViewImp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListarUsuariosViewImp extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    public static final String TAG = "fragmentList";

    private String mParam1;
    private OnFragmentInteractionListener mCallback;
    private ListaUsuariosAdapter adapter;

    private ListView listView;

    public ListarUsuariosViewImp() {
        // Required empty public constructor
    }

    public static ListarUsuariosViewImp newInstance(Bundle bundle) {
        ListarUsuariosViewImp fragment = new ListarUsuariosViewImp();

        if(bundle != null)
            fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listar_usuarios_view_imp,container,false);

        listView = view.findViewById(R.id.listviewUsuarios);
        adapter = new ListaUsuariosAdapter(getActivity());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        adapter.clear();
        adapter.addAll(UsuarioRepository.getInstance().getUsuarios());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario usuario = (Usuario) parent.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable(ARG_PARAM1,usuario);
                mCallback.onOpenAddEdit(bundle);
            }
        });

        registerForContextMenu(listView);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (OnFragmentInteractionListener) activity;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.prinicpal_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_usuario:
                mCallback.onOpenAddEdit(null);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        menu.setHeaderTitle("Titulo");
        inflater.inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_eliminar:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Usuario usuario = (Usuario) listView.getItemAtPosition(menuInfo.position);
                UsuarioRepository.getInstance().deleteUser(usuario);
                adapter.clear();
                adapter.addAll(UsuarioRepository.getInstance().getUsuarios());
                return true;
            case R.id.action_cancelar:
                return false;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    public interface OnFragmentInteractionListener {
        void onOpenAddEdit(Bundle bundle);
    }
}
