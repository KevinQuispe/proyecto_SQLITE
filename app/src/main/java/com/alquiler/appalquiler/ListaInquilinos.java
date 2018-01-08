package com.alquiler.appalquiler;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alquiler.appalquiler.entidades.persona;
import com.alquiler.appalquiler.utilidades.Utilidades;

import java.util.ArrayList;


public class ListaInquilinos extends Fragment {
    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<persona> listaPerson;
    ConexionSqlHelper conn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_lista_inquilinos, container, false);
        conn=new ConexionSqlHelper(getContext(), Utilidades.BASE_DATOS,null,1);
        listViewPersonas=(ListView) view.findViewById(R.id.listPerson);
        consultarListPerson();

        ArrayAdapter adaptador=new ArrayAdapter(getContext(),android.R.layout.simple_selectable_list_item,listaInformacion);
        //obtener lista
        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //call frag
                Fragment fragment = null;
                FragmentManager fragmentManager = getFragmentManager();
                persona per=listaPerson.get(position);
                fragment = new DetalleInquilino();

                Bundle bundle=new Bundle();
                bundle.putSerializable("inquilino",per);
                bundle.putString("nombreKey",listaPerson.get(position).getNombres().toString());
                bundle.putString("dniKey",listaPerson.get(position).getDni().toString());
                bundle.putString("telefonoKey",listaPerson.get(position).getTelefono().toString());
                bundle.putString("correoKey",listaPerson.get(position).getCorreo().toString());
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.contenedorfragment, fragment).commit();


           /*     switch (position){
                    case 0:
                        fragment = new DetalleInquilino();
                        Toast.makeText(getContext(),"me salio csm",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        fragment = new DetalleInquilino();
                        //Intent intent = new Intent(getActivity(), MainActivity.class);
                        //startActivity(intent);
                        Toast.makeText(getContext(),"me salio csm",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        fragment = new DetalleInquilino();
                        Toast.makeText(getContext(),"me salio csm",Toast.LENGTH_LONG).show();
                        break;
                }
                if (fragment != null) {
                    fragment = new DetalleInquilino();
                    fragmentManager.beginTransaction().replace(R.id.contenedorfragment, fragment).commit();
                } */
            }
            private FragmentManager getSupportFragmentManager() {
                return null;
            }
        });
        listViewPersonas.setAdapter(adaptador);
        return view;
    }

    private void consultarListPerson() {
        SQLiteDatabase db=conn.getReadableDatabase();
        persona person=null;
        listaPerson=new ArrayList<persona>();
        Cursor cursor=db.rawQuery("select * from "+Utilidades.TABLA_PERSONA+" where "+Utilidades.CAMPO_ESTADO+"= 1"+" ORDER BY nombres",null);
        while (cursor.moveToNext()){
            person=new persona();
            person.setDni(cursor.getString(1));
            person.setNombres(cursor.getString(2));
            person.setTelefono(cursor.getString(3));
            person.setCorreo(cursor.getString(4));
            listaPerson.add(person);
        }
        obtenerListaView();
    }

    private void obtenerListaView() {
        listaInformacion=new ArrayList<String>();
        for (int i=0; i<listaPerson.size();i++){
            listaInformacion.add(listaPerson.get(i).getDni()+" - "+listaPerson.get(i).getNombres());
        }
    }



}
