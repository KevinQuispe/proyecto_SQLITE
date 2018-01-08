package com.alquiler.appalquiler;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alquiler.appalquiler.entidades.persona;
import com.alquiler.appalquiler.utilidades.Utilidades;

import java.util.ArrayList;

public class RegistrarHabitacion extends Fragment{

    Spinner comboService;
    EditText campoNumHabitacion,precio;
    ConexionSqlHelper  conn;
    Button btnRegisHabi,btnlimpiar;

    public RegistrarHabitacion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //edit_message = (EditText) getView().findViewById(R.id.editTextuser);
        //Intent thisForm = new Intent();
        //Bundle parametros = thisForm.getExtras();
        //String message = parametros.getString("message");
        //edit_message.setText("Hola " + message);

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_registrar_habitacion, container, false);
        comboService=(Spinner) view.findViewById(R.id.spinnerServicio);
        campoNumHabitacion=(EditText) view.findViewById(R.id.ETNumberHabitacion);
        precio=(EditText) view.findViewById(R.id.ETPrecio);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getContext(),R.array.comboServicio,android.R.layout.simple_spinner_dropdown_item);
        comboService.setAdapter(adapter);
        conn = new ConexionSqlHelper(getContext(),"bd_proyecto01",null,1);

        btnRegisHabi=(Button) view.findViewById(R.id.btnregistrarHab);
        btnRegisHabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(campoNumHabitacion.getText().length()!=0 && precio.getText().length()!=0){
                     double var=Double.parseDouble(precio.getText().toString());
                        if(var>1){
                            registrarHabitacion();
                        }
                        else {
                            Toast.makeText(getContext(),"EL PRECIO INGRESADO NO ES CORRECTO O DEBE SUPERAR A S/.0",Toast.LENGTH_SHORT).show();
                        }
                }
                else{
                    Toast.makeText(getContext(),"COMPLETE DATOS",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnlimpiar=(Button) view.findViewById(R.id.btnnuevoH);
        btnlimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

        return view;
    }




    private void registrarHabitacion() {
        ConexionSqlHelper conn = new ConexionSqlHelper(getContext(),"bd_proyecto01",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Utilidades.CAMPO_NUMEROHABITACION,campoNumHabitacion.getText().toString());
        values.put(Utilidades.CAMPO_TIPOSERVICIO,comboService.getSelectedItem().toString());
        values.put(Utilidades.CAMPO_PRECIO,precio.getText().toString());
        values.put(Utilidades.CAMPO_ESTADO,"0");

        Long idResultante=db.insert(Utilidades.TABLA_HABITACION,Utilidades.CAMPO_NUMEROHABITACION,values);
        if(idResultante==-1){
            Toast.makeText(getContext(),"EL N° DE HABITACION YA EXISTE :",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getContext(),"LA HABITACION N° "+campoNumHabitacion.getText()+"\nSE REGISTRO CON EXITO",Toast.LENGTH_LONG).show();
            limpiar();
        }

        db.close();

    }

    private void limpiar() {

        precio.setText("");
        campoNumHabitacion.setText("");
    }




}
