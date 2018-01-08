package com.alquiler.appalquiler;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.alquiler.appalquiler.utilidades.Utilidades;

public class ConexionSqlHelper extends SQLiteOpenHelper {

     public ConexionSqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREATE_TABLE_PERSONA);
        db.execSQL(Utilidades.CREATE_TABLE_HABITACION);
        db.execSQL(Utilidades.CREATE_TABLE_USUARIO);
        db.execSQL(Utilidades.CREATE_TABLE_REGISTRO);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS persona");
        db.execSQL("DROP TABLE IF EXISTS habitacion");
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS habitaciones");
    }
}
