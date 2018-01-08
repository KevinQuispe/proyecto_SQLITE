package com.alquiler.appalquiler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alquiler.appalquiler.entidades.persona;
import com.alquiler.appalquiler.utilidades.Utilidades;


public class DetalleInquilino extends Fragment {
    TextView campoNumHab,campoPrecio,campoServicio,campoDni,campoNombres,campoTelefono,campoCorreo  ;
    ConexionSqlHelper conn;
    public DetalleInquilino() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_detalle_inquilino, container, false);
        conn=new ConexionSqlHelper(getContext(),"bd_proyecto01",null,1);
        //llenar campos inquilino
        campoDni =(TextView) view.findViewById(R.id.tvDNI);
        campoNombres=(TextView) view.findViewById(R.id.tvNombres);
        campoTelefono=(TextView) view.findViewById(R.id.tvTelefono);
        campoCorreo=(TextView) view.findViewById(R.id.tvCorreo);
        campoNombres.setText(getArguments().getString("nombreKey"));
        campoDni.setText(getArguments().getString("dniKey"));
        campoTelefono.setText(getArguments().getString("telefonoKey"));
        campoCorreo.setText(getArguments().getString("correoKey"));
        //llenar campos habitacion
        campoNumHab=(TextView) view.findViewById(R.id.tvHabitacionNumber);
        campoPrecio=(TextView) view.findViewById(R.id.tvPrecio);
        campoServicio=(TextView) view.findViewById(R.id.tvServicio);
           consultarRegistroHabitacion(getArguments().getString("dniKey"));

        return view;
    }

    private void consultarRegistroHabitacion(String dni) {
        SQLiteDatabase db=conn.getReadableDatabase();
        String []parametros={dni};
        String []campos={Utilidades.CAMPO_NUMEROHABITACION};
        try{
            Cursor cursor= db.query(Utilidades.TABLA_REGISTRO,campos,Utilidades.CAMPO_DNI+" = ?",parametros,null,null,null);
            if(cursor.moveToFirst()==true) {
                campoNumHab.setText(cursor.getString(0));
                consultarDatosHabitacion(cursor.getString(0));
                cursor.close();
            }else {
                Toast.makeText(getContext(),"ERROR",Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private void consultarDatosHabitacion(String NumHab) {
        SQLiteDatabase db=conn.getReadableDatabase();
        String []parametros={NumHab};
        String []campos={Utilidades.CAMPO_PRECIO,Utilidades.CAMPO_TIPOSERVICIO};
        try{
            Cursor cursor= db.query(Utilidades.TABLA_HABITACION,campos,Utilidades.CAMPO_NUMEROHABITACION+" = ?",parametros,null,null,null);
            if(cursor.moveToFirst()==true) {
                campoPrecio.setText(cursor.getString(0));
                campoServicio.setText(cursor.getString(1));
                cursor.close();
            }else {
                Toast.makeText(getContext(),"ERROR",Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


}
