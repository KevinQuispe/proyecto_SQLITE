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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alquiler.appalquiler.entidades.Usuario;
import com.alquiler.appalquiler.entidades.persona;
import com.alquiler.appalquiler.utilidades.Utilidades;

import java.util.ArrayList;


public class ListaUsuarios extends Fragment {
    ListView listViewUsuarios;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUser;
    ConexionSqlHelper conn;

    public ListaUsuarios() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_usuarios, container, false);
        listViewUsuarios=(ListView) view.findViewById(R.id.listUsuarios);
        conn=new ConexionSqlHelper(getContext(), Utilidades.BASE_DATOS,null,1);
        consultarListUsuarios();
        ArrayAdapter adaptador=new ArrayAdapter(getContext(),android.R.layout.simple_selectable_list_item,listaInformacion);
        listViewUsuarios.setAdapter(adaptador);
        return view;
    }

    private void consultarListUsuarios() {
        SQLiteDatabase db=conn.getReadableDatabase();
        Usuario user=null;
        listaUser=new ArrayList<Usuario>();
        Cursor cursor=db.rawQuery("select * from "+Utilidades.TABLA_USUARIO+" ORDER BY nombres",null);
        while (cursor.moveToNext()){
            user=new Usuario();
            user.setNombre(cursor.getString(1));
            user.setUsuario(cursor.getString(2));
            user.setCorreo(cursor.getString(4));

            listaUser.add(user);
        }
        obtenerListaView();
    }

    private void obtenerListaView() {
        listaInformacion=new ArrayList<String>();
        for (int i=0; i<listaUser.size();i++){
            listaInformacion.add("NOMBRES :"+listaUser.get(i).getNombre()+"\nUSUARIO : "+listaUser.get(i).getUsuario()
            +"\nCORREO"+listaUser.get(i).getCorreo());
        }
    }



}
