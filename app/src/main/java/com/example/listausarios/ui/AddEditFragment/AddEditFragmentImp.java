package com.example.listausarios.ui.AddEditFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listausarios.R;
import com.example.listausarios.broadcast.NotifyAddUsuario;
import com.example.listausarios.data.pojo.Usuario;
import com.example.listausarios.ui.AddEditFragment.Contract.AddEditContract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddEditFragmentImp.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddEditFragmentImp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEditFragmentImp extends Fragment implements DatePickerDialog.OnDateSetListener,AddEditContract.view{
    private static final String ARG_PARAM1 = "param1";

    public static final String TAG = "addEditFragment";

    EditText edtNombre,edtApellido;
    TextView txvFecha;
    Button btnFecha,btnAdd;
    TextInputLayout tilNombre,tilApellido;
    DatePickerDialog pickerDialog;

    AddEditContract.presenter presenter;

    Date fecha;

    private OnFragmentInteractionListener mListener;


    public AddEditFragmentImp() {
        // Required empty public constructor
    }

    public static AddEditFragmentImp newInstance(Bundle bundle) {
        AddEditFragmentImp fragment = new AddEditFragmentImp();

        if(bundle != null)
            fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_fragment_imp,null);

        edtNombre = view.findViewById(R.id.edtNombre);
        edtApellido = view.findViewById(R.id.edtApellido);

        txvFecha = view.findViewById(R.id.txvFecha);

        btnAdd = view.findViewById(R.id.btnAdd);
        btnFecha = view.findViewById(R.id.btnFecha);

        pickerDialog = new DatePickerDialog(getActivity(),AddEditFragmentImp.this,Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new AddEditPresenter(this);

        if(getArguments() != null){
            edtNombre.setEnabled(false);
            edtApellido.setEnabled(false);

            Usuario usuario = getArguments().getParcelable(ARG_PARAM1);

            edtNombre.setText(usuario.getNombre());
            edtApellido.setText(usuario.getApellido());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            txvFecha.setText(simpleDateFormat.format(usuario.getFechaNac()));
        }

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerDialog.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getArguments() == null){
                    Usuario usuario = new Usuario(0,edtNombre.getText().toString(),edtApellido.getText().toString(),fecha);
                    presenter.addUsuario(usuario);
                }
            }
        });
    }

    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onListUsuarios();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (OnFragmentInteractionListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String dateString = dayOfMonth+"/"+(month+1)+"/"+year;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fecha = simpleDateFormat.parse(dateString);
            txvFecha.setText(simpleDateFormat.format(fecha));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAddSuccess() {
        Toast.makeText(getActivity(),"Usuario Añadido",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(NotifyAddUsuario.NAME_ACTION);
        intent.putExtra("ll","Añadiste Algo");
        getActivity().sendBroadcast(intent);
        mListener.onListUsuarios();
    }

    @Override
    public void onAddError(Throwable throwable) {
        Toast.makeText(getActivity(),"Hubo un error al añadir",Toast.LENGTH_SHORT).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onListUsuarios();
    }
}
