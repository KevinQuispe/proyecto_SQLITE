package com.alquiler.appalquiler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class TipoHabitacion extends Fragment  implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //declaracion de variables
    public Button btnreg,botonnew;
    public EditText campohabitacion,campoprecio,camposervicio;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public TipoHabitacion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TipoHabitacion.
     */
    // TODO: Rename and change types and number of parameters
    public static TipoHabitacion newInstance(String param1, String param2) {
        TipoHabitacion fragment = new TipoHabitacion();
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
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_tipo_habitacion, container, false);
        //LLAMAR AL ID DE LOS BOTONES
        btnreg = (Button) view.findViewById(R.id.btnregistrar);
        botonnew = (Button) view.findViewById(R.id.btnnuevo);
        campohabitacion = (EditText) view.findViewById(R.id.ettipo);
        campoprecio = (EditText) view.findViewById(R.id.etservicio);
        camposervicio = (EditText) view.findViewById(R.id.etservicio);
        //mis escucha aqui para el boton
        btnreg.setOnClickListener(this);
        botonnew.setOnClickListener(this);
        //LLAMAR A LOS CAMPOS EDIT TEXT
       /*
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //campturar campos
                String hab,precio,servicio;
                hab=campohabitacion.getText().toString();
                precio=campoprecio.getText().toString();
                servicio=camposervicio.getText().toString();

                if (v.getId()==btnreg.getId()){
                    Toast.makeText(getActivity(),"Hola Soy el fragment 1",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getActivity(),"Hola Soy el fragment",Toast.LENGTH_LONG).show();
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });
        return  view;
        */
        //click para nuevo
        return view;
    }
    public void pasarDatos(View view) {
        Intent intent=new Intent();
        Bundle parametros = new Bundle();
        String message = campohabitacion.getText().toString();
        parametros.putString("message",message);
        intent.putExtras(parametros);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        String hab,precio,servicio;
        hab=campohabitacion.getText().toString();
        precio=campoprecio.getText().toString();
        servicio=camposervicio.getText().toString();

        if (v.getId()==btnreg.getId()){
            RegistrarHabitacion habitacion=new RegistrarHabitacion();
            FragmentTransaction transaction= getFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedorfragment,habitacion);
            transaction.commit();
            Toast.makeText(getActivity(),"Ahora registra tu habitacion",Toast.LENGTH_LONG).show();
        }
        else if (v.getId()==botonnew.getId()){
            if (!hab.equals("") && !precio.equals("") && !servicio.equals("")){
                limpiarcampos();
            }
            else{
                Toast.makeText(getActivity(),"Los campos estan limpios",Toast.LENGTH_LONG).show();
            }
        }
        else{
        }
    }
    public void  limpiarcampos(){
        campohabitacion.setText("");
        camposervicio.setText("");
        campoprecio.setText("");
        Toast.makeText(getActivity(),"campos limpios",Toast.LENGTH_LONG).show();
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

}
