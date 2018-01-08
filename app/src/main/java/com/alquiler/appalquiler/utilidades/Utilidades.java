package com.alquiler.appalquiler.utilidades;

/**
 * Created by jesus on 1/11/2017.
 */

public class Utilidades {

    //campos tabla persona
    public static final String TABLA_PERSONA = "persona";
    public static final String CAMPO_DNI = "dni";
    public static final String CAMPO_NOMBRES = "nombres";
    public static final String CAMPO_TELEFONO = "telefono";
    public static final String CAMPO_CORREO = "correo";
    public static final String BASE_DATOS = "bd_proyecto01";
    public static final String CAMPO_ESTADO= "estado";

    public static final String CREATE_TABLE_PERSONA = "CREATE TABLE " + TABLA_PERSONA + " " +
            "(" + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT," + CAMPO_DNI + " TEXT UNIQUE NOT NULL," +
            CAMPO_NOMBRES + " TEXT ," + CAMPO_TELEFONO + " TEXT," +CAMPO_CORREO + " TEXT UNIQUE," + CAMPO_ESTADO + " TEXT)";

    //campos tabla habitacion
    public static final String TABLA_HABITACION = "habitacion";
    public static final String CAMPO_PRECIO = "precio";
    public static final String CAMPO_TIPOSERVICIO = "tipoServicio";
    public static final String CAMPO_NUMEROHABITACION = "NumHab";


    public static final String CREATE_TABLE_HABITACION = "CREATE TABLE " + TABLA_HABITACION + " " +
            "(" + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT," + CAMPO_NUMEROHABITACION + " INTEGER UNIQUE NOT NULL," +
            CAMPO_TIPOSERVICIO + " TEXT ," +CAMPO_PRECIO + " DOUBLE ," + CAMPO_DNI + " TEXT ,"+ CAMPO_ESTADO + " TEXT)";

    //campos usuario
    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_USUARIO = "user";
    public static final String CAMPO_PASSWORD = "contra";

    public static final String CREATE_TABLE_USUARIO = "CREATE TABLE " + TABLA_USUARIO + " " +
            "(" + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT," + CAMPO_NOMBRES + " TEXT," +
            CAMPO_USUARIO + " TEXT UNIQUE," +CAMPO_PASSWORD + " TEXT ," + CAMPO_CORREO + " TEXT UNIQUE)";

    //tabla asignar habitacion
    public static String TABLA_REGISTRO="habitaciones";

    public static final String CREATE_TABLE_REGISTRO = "CREATE TABLE " + TABLA_REGISTRO + " " +
            "(" + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT," + CAMPO_NUMEROHABITACION + " INTEGER," +
             CAMPO_DNI + " TEXT )";


}
