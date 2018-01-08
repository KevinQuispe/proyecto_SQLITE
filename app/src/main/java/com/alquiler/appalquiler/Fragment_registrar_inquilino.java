package com.alquiler.appalquiler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.alquiler.appalquiler.ConexionSqlHelper;
import com.alquiler.appalquiler.utilidades.Utilidades;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_registrar_inquilino.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_registrar_inquilino#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_registrar_inquilino extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText campoDNI,campoNombre,campoTelefono,campoCorreo;
    Button btnRegistraPerson,btnlimpiar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment_registrar_inquilino() {
        // Required empty public constructor
    }


    public static Fragment_registrar_inquilino newInstance(String param1, String param2) {
        Fragment_registrar_inquilino fragment = new Fragment_registrar_inquilino();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_registrar_inquilino, container, false);

        campoDNI=(EditText) view.findViewById(R.id.ETdni);
        campoNombre=(EditText) view.findViewById(R.id.etNombres);
        campoTelefono=(EditText) view.findViewById(R.id.etTelefono);
        campoCorreo=(EditText) view.findViewById(R.id.etCorreo);

        btnRegistraPerson=(Button) view.findViewById(R.id.btnregistrarInquilino);
        btnRegistraPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((campoDNI.getText().length()!=0)&&(campoNombre.getText().length()!=0)
                        &&(campoTelefono.getText().length()!=0) &&(campoCorreo.getText().length()!=0)){
                    if((campoTelefono.getText().length()==9) &&(campoDNI.getText().length()==8)){
                        registrarPersona();
                    }else {
                        Toast.makeText(getContext(),"VERIFIQUE DNI O TELEFONO",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getContext(),"COMPLETE DATOS",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnlimpiar=(Button) view.findViewById(R.id.btnnuevo);
        btnlimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void registrarSQLpersona() {
        ConexionSqlHelper conn = new ConexionSqlHelper(this.getContext(),"bd_proyecto01",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        String insert="INSERT INTO "+Utilidades.TABLA_PERSONA+" ("+Utilidades.CAMPO_DNI+","+Utilidades.CAMPO_NOMBRES+","+
                Utilidades.CAMPO_TELEFONO+","+Utilidades.CAMPO_CORREO+")"+
                "values ("+campoDNI.getText().toString()+", '"+campoNombre.getText().toString()+"','"+campoTelefono.getText().toString()+"','"+campoCorreo.getText().toString()+"')";

        db.execSQL(insert);
        db.close();
    }

    private void registrarPersona() {
        ConexionSqlHelper conn = new ConexionSqlHelper(this.getContext(),"bd_proyecto01",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Utilidades.CAMPO_DNI,campoDNI.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRES,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());
        values.put(Utilidades.CAMPO_CORREO,campoCorreo.getText().toString());
        values.put(Utilidades.CAMPO_ESTADO,"0");
        Long idResultante=db.insert(Utilidades.TABLA_PERSONA,Utilidades.CAMPO_DNI,values);
        if(idResultante==-1){
            Toast.makeText(getContext(),"DNI YA EXISTE :",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getContext(),"EL CLIENTE: "+campoNombre.getText()+"\nSE REGISTRO CON EXITO",Toast.LENGTH_LONG).show();
            limpiar();
        }

        db.close();

    }

    private void limpiar() {
        campoDNI.setText("");
        campoNombre.setText("");
        campoTelefono.setText("");
        campoCorreo.setText("");
    }
}
