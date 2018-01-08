package com.alquiler.appalquiler;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alquiler.appalquiler.utilidades.Utilidades;

public class Register_usuario extends AppCompatActivity implements View.OnClickListener{

    Button login,regiter;
    EditText campoFullName,campoUserName,campoContra,campoEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_usuario);
        login=(Button) findViewById(R.id.btnlog);
        regiter=(Button) findViewById(R.id.btnregistrar);
        login.setOnClickListener(this);
        regiter.setOnClickListener(this);
        campoFullName=(EditText) findViewById(R.id.idfullname);
        campoUserName=(EditText) findViewById(R.id.idusername);
        campoContra=(EditText) findViewById(R.id.idpassword);
        campoEmail=(EditText) findViewById(R.id.idemail);
    }
    @Override public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnregistrar:
                if((campoFullName.getText().length()!=0) && (campoUserName.getText().length()!=0)
                        && (campoContra.getText().length()!=0) && (campoEmail.getText().length()!=0)){
                    registrarPersona();
                }else {
                    Toast.makeText(this,"Complete los campos",Toast.LENGTH_SHORT).show();
                }

                //Toast.makeText(this,"Funcionalidad en desarrollo",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnlog:
                Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void registrarPersona() {
        ConexionSqlHelper conn = new ConexionSqlHelper(getApplicationContext(),"bd_proyecto01",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRES,campoFullName.getText().toString());
        values.put(Utilidades.CAMPO_USUARIO,campoUserName.getText().toString());
        values.put(Utilidades.CAMPO_PASSWORD,campoContra.getText().toString());
        values.put(Utilidades.CAMPO_CORREO,campoEmail.getText().toString());

        Long idResultante=db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_CORREO,values);
        if(idResultante==-1){
            Toast.makeText(getApplicationContext(),"El correo ya existe :"+idResultante,Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Se ha registrado con exito\nBienvenido: "+campoFullName.getText(),Toast.LENGTH_LONG).show();
            Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            limpiar();
        }

        db.close();

    }

    private void limpiar() {
        campoFullName.setText("");
        campoUserName.setText("");
        campoEmail.setText("");
        campoContra.setText("");
    }
}
