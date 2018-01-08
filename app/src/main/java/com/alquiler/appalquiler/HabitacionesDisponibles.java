package com.alquiler.appalquiler;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alquiler.appalquiler.entidades.Habitacion;
import com.alquiler.appalquiler.entidades.persona;
import com.alquiler.appalquiler.utilidades.Utilidades;

import java.util.ArrayList;


public class HabitacionesDisponibles extends Fragment {
    ListView listViewHabitacion;
    ArrayList<String> listaInformacionHab;
    ArrayList<Habitacion> listaHab;
    ConexionSqlHelper conn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_habitaciones_disponibles, container, false);
        conn=new ConexionSqlHelper(getContext(), Utilidades.BASE_DATOS,null,1);
        listViewHabitacion=(ListView) view.findViewById(R.id.listHabitacion);
        consultarListPerson();
        ArrayAdapter adaptador=new ArrayAdapter(getContext(),android.R.layout.simple_selectable_list_item,listaInformacionHab);
        listViewHabitacion.setAdapter(adaptador);
        return view;
    }

    private void consultarListPerson() {
        SQLiteDatabase db=conn.getReadableDatabase();
        Habitacion hab=null;
        listaHab=new ArrayList<Habitacion>();
        Cursor cursor=db.rawQuery("select * from "+Utilidades.TABLA_HABITACION +" where "+Utilidades.CAMPO_ESTADO+"= 0"+" ORDER BY NumHab",null);
        while (cursor.moveToNext()){
            hab=new Habitacion();
            hab.setNumHabitacion(cursor.getInt(1));
            hab.setPrecio(cursor.getDouble(3));
            hab.setTipoServicio(cursor.getString(2));
            //   Toast.makeText(getApplicationContext(),"num "+cursor.getInt(1)+"\nprecio "+cursor.getDouble(3)
            //          +"\nservicio"+cursor.getString(3)+"\ndni "+cursor.getString(2)
            //              ,Toast.LENGTH_LONG).show();
            listaHab.add(hab);
        }
        obtenerListaView();
    }

    private void obtenerListaView() {
        listaInformacionHab=new ArrayList<String>();
        for (int i=0; i<listaHab.size();i++){
            listaInformacionHab.add("NUMERO HABITACION: "+listaHab.get(i).getNumHabitacion()+" \nPRECIO: "+listaHab.get(i).getPrecio()+"\nTIPO DE SERVICIO:"+listaHab.get(i).getTipoServicio());
        }
    }

}
