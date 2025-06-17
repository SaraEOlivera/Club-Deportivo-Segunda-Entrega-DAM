package com.example.segundaentregadam

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.sql.Date

class UserDBHelper(context: Context):SQLiteOpenHelper(context, "ClubDB", null, 4) {
    //onCreate
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT UNIQUE,
                clave TEXT
            )
        """.trimIndent())
        db.execSQL("INSERT INTO usuarios(nombre, clave) VALUES ('a', 'a')")
        db.execSQL("INSERT INTO usuarios(nombre, clave) VALUES ('admin', '1234')")
        db.execSQL("INSERT INTO usuarios(nombre, clave) VALUES ('sara', 'sara2025')")

        db.execSQL("""
            CREATE TABLE actividades (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT UNIQUE,
                profesor TEXT
            )
        """.trimIndent())
        db.execSQL("INSERT INTO actividades(nombre, profesor) VALUES ('Futbol', 'Leo Messi')")
        db.execSQL("INSERT INTO actividades(nombre, profesor) VALUES ('Basquet', 'Manu Ginobili')")

        db.execSQL("""
            CREATE TABLE clientes (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                apellido TEXT,
                dni TEXT UNIQUE,
                esSocio Integer
                --fechaNac DATE-->
            )
        """.trimIndent())
        db.execSQL("""
            INSERT INTO clientes(nombre, apellido, dni, esSocio) VALUES (
            'Juan', 'Perez', '77', 1
            )
        """.trimIndent())

        db.execSQL("""
            INSERT INTO clientes(nombre, apellido, dni, esSocio) VALUES (
            'Juana', 'Perez', '25', 0
            )
        """.trimIndent())
    }
    //onUpgrade
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS usuarios")
        db.execSQL("DROP TABLE IF EXISTS actividades")
        db.execSQL("DROP TABLE IF EXISTS clientes")
        onCreate(db)
    }

    fun login (nombre:String, clave:String):Boolean{
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE nombre =? AND clave=?",
            arrayOf(nombre, clave)
        )
        val existe =cursor.count > 0
        return  existe
    }

    fun insertarAdmins(nombre:String, clave:String):Boolean{
        val db = writableDatabase
        val valores = ContentValues().apply {
            put("nombre", nombre)
            put("clave", clave)
        }
        val result = db.insert("usuarios", null, valores)
        return result != -1L
    }

    fun insertarClientes(nombre: String, apellido:String, dni:String, esSocio:Boolean):Boolean{
        val db = writableDatabase
        val valores = ContentValues().apply {
            put("nombre", nombre)
            put("apellido", apellido)
            put("dni", dni)
            put("esSocio", if (esSocio) 1 else 0)
        }
        val result = db.insert("clientes", null, valores)
        return  result != -1L
    }

    fun obtenerActividades():List<String>{

        val actividades = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT nombre, profesor FROM actividades",
            null
        )
        if (cursor.moveToFirst()) {
            do {
                val actividad = cursor.getString(0)
                val profesor = cursor.getString(1)
                actividades.add("$actividad - $profesor")
            } while (cursor.moveToNext())
        }
        cursor.close()
        return actividades
    }
}





























