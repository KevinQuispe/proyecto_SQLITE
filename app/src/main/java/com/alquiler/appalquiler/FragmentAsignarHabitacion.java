package com.alquiler.appalquiler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.alquiler.appalquiler.entidades.Habitacion;
import com.alquiler.appalquiler.entidades.persona;
import com.alquiler.appalquiler.utilidades.Utilidades;

import java.util.ArrayList;

public class FragmentAsignarHabitacion extends Fragment {

    ArrayList<String> listapersonas;
    ArrayList<persona> personaList;
    ArrayList<String> listaHabitaciones;
    ArrayList<Habitacion> HabitacionesList;
    ConexionSqlHelper  conn;
    Spinner comboPersona,comboHabitacion;
    Button btnAsignar;

    public FragmentAsignarHabitacion() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_asignar_habitacion, container, false);
        comboPersona=(Spinner) view.findViewById(R.id.spinnerInquilino);
        conn = new ConexionSqlHelper(getContext(),"bd_proyecto01",null,1);
        consultarListaPersonas();
        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,listapersonas);
        comboPersona.setAdapter(adaptador);
        comboHabitacion=(Spinner) view.findViewById(R.id.spinnerHabitacion);
        consultarListaHabitaciones();
        ArrayAdapter<CharSequence> adaptador2=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,listaHabitaciones);
        comboHabitacion.setAdapter(adaptador2);

        btnAsignar=(Button) view.findViewById(R.id.btnAsignarHabitacion);
        btnAsignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //registrar tabla nueva
                //actualizar table habitacion
                //actualizar tabla persona
                Boolean valor=asignarHabitacion();
            }
        });



        return view;
    }

    private boolean asignarHabitacion(){
        Boolean resul = false;
        if(comboHabitacion.getSelectedItem().toString().equals("Seleccione") || comboPersona.getSelectedItem().toString().equals("Seleccione") ){
            Toast.makeText(getContext(), "DEBE SELECCION UNA HABITACION Y UN CLIENTE:", Toast.LENGTH_LONG).show();
        }else {
            String person= comboPersona.getSelectedItem().toString();
            ConexionSqlHelper conn = new ConexionSqlHelper(this.getContext(), "bd_proyecto01", null, 1);
            SQLiteDatabase db = conn.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_NUMEROHABITACION, comboHabitacion.getSelectedItem().toString());
            values.put(Utilidades.CAMPO_DNI,person.substring(0,8).toString());
          //  Toast.makeText(getContext(),"dni "+person.length()+"   dni "+person.substring(0,8),Toast.LENGTH_SHORT).show();
            Long idResultante = db.insert(Utilidades.TABLA_REGISTRO, Utilidades.CAMPO_DNI, values);
            if (idResultante == -1) {
                Toast.makeText(getContext(), "ERROR :" + idResultante, Toast.LENGTH_LONG).show();
            } else {
                actualizarPersona(person.substring(0,8).toString());
                actualizarHabitacion();
                Toast.makeText(getContext(), "se Registro con exito", Toast.LENGTH_LONG).show();
            }

            db.close();
        }
        return false;
    }

    private void actualizarPersona(String dni) {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={dni};
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_ESTADO,1);
        db.update(Utilidades.TABLA_PERSONA,values,Utilidades.CAMPO_DNI+"=?",parametros);
      //  Toast.makeText(getContext(),"correcto persona",Toast.LENGTH_SHORT).show();
        db.close();

    }
    private void actualizarHabitacion() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={comboHabitacion.getSelectedItem().toString()};
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_ESTADO,1);
        db.update(Utilidades.TABLA_HABITACION,values,Utilidades.CAMPO_NUMEROHABITACION+"=?",parametros);
      //  Toast.makeText(getContext(),"correcto habitacion",Toast.LENGTH_SHORT).show();
        db.close();

    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();
        persona person=null;
        personaList= new ArrayList<persona>();
        Cursor cursor=db.rawQuery("select * from "+ Utilidades.TABLA_PERSONA+" ORDER BY nombres",null);
        while (cursor.moveToNext()){
            person=new persona();
            person.setDni(cursor.getString(1));
            person.setNombres(cursor.getString(2));
            person.setTelefono(cursor.getString(3));
            person.setCorreo(cursor.getString(4));
            //mostrar en consola la informacion retornada
            Log.i("dni",person.getDni());
            Log.i("nombres",person.getNombres());
            //agregar los datos a lista
            personaList.add(person);

        }
        obtenerListaEnCombo();
    }

    private void obtenerListaEnCombo() {
        listapersonas=new ArrayList<String>();
        listapersonas.add("Seleccione");
        for (int i=0; i<personaList.size();i++){
            //listapersonas.add(personaList.get(i).getDni()+" - "+personaList.get(i).getNombres());
            listapersonas.add(personaList.get(i).getDni()+" - "+personaList.get(i).getNombres());
        }
    }

    private void consultarListaHabitaciones() {
        SQLiteDatabase db=conn.getReadableDatabase();
        Habitacion hab=null;
        HabitacionesList= new ArrayList<Habitacion>();
        Cursor cursor=db.rawQuery("select * from "+ Utilidades.TABLA_HABITACION +" where "+Utilidades.CAMPO_ESTADO+"= 0"+" ORDER BY NumHab",null);
        while (cursor.moveToNext()){
            hab=new Habitacion();
            hab.setNumHabitacion(cursor.getInt(1));
            hab.setPrecio(cursor.getDouble(2));
            hab.setTipoServicio(cursor.getString(3));

            //mostrar en consola la informacion retornada
            Log.i("dni",hab.getNumHabitacion().toString());
            Log.i("tipo servis ",hab.getTipoServicio().toString());
            //agregar los datos a lista
            HabitacionesList.add(hab);

        }
        obtenerListaEnCombo2();
    }

    private void obtenerListaEnCombo2() {
        listaHabitaciones=new ArrayList<String>();
        listaHabitaciones.add("Seleccione");
        for (int i=0; i<HabitacionesList.size();i++){
            //listapersonas.add(personaList.get(i).getDni()+" - "+personaList.get(i).getNombres());
            listaHabitaciones.add(HabitacionesList.get(i).getNumHabitacion().toString());
        }
    }


}
