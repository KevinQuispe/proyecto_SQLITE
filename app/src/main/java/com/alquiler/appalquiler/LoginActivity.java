package com.alquiler.appalquiler;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alquiler.appalquiler.utilidades.Utilidades;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText user,pass;
    Button login,sigup;
    ConexionSqlHelper conn;
    String nombre="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //llamar a los datos ID
        conn=new ConexionSqlHelper(getApplicationContext(),"bd_proyecto01",null,1);
        user=(EditText) findViewById(R.id.idusername);
        pass=(EditText) findViewById(R.id.idpassword);
        login=(Button) findViewById(R.id.btnlogin);
        sigup=(Button)findViewById(R.id.btnsigup);
        login.setOnClickListener(this);
        sigup.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        String usuario=user.getText().toString();
        String clave=pass.getText().toString();

        switch (v.getId()){
            case R.id.btnlogin:
                if (usuario.length()!=0 && clave.length()!=0){
                   if(consultarSQL()==true){
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("usuario",nombre);
                        //iniciar activite
                        startActivity(intent);
                        Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else if (usuario.equals("") || clave.equals("")){
                    Toast.makeText(this,"Complete los campos",Toast.LENGTH_SHORT).show();

                }

                break;

            case R.id.btnsigup:
                Intent intent=new Intent(getApplicationContext(),Register_usuario.class);
                startActivity(intent);
               // Toast.makeText(this,"Funcionalidad en desarrollo",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this,"Error Unknown",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private boolean consultarSQL() {
        boolean resul=false;
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={user.getText().toString(),pass.getText().toString()};
        try {
            Cursor cursor=db.rawQuery("select "+ Utilidades.CAMPO_NOMBRES+" from "+Utilidades.TABLA_USUARIO+
                    " where "+Utilidades.CAMPO_USUARIO+"= ? and "+Utilidades.CAMPO_PASSWORD+"=?",parametros);
            if(cursor.moveToFirst()==true) {
                nombre=cursor.getString(0);
               // Toast.makeText(getApplicationContext(), cursor.getString(0), Toast.LENGTH_SHORT).show();
                resul=true;
            }else{
                Toast.makeText(getApplicationContext(),"Dattos Incorrectos", Toast.LENGTH_SHORT).show();
                resul=false;
            }
           cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return resul;
    }


}
