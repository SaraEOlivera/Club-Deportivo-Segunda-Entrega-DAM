package com.example.segundaentregadam

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDBHelper(context: Context):SQLiteOpenHelper(context, "ClubDB", null, 1) {
    //onCreate
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT UNIQUE,
                clave TEXT
            )
        """.trimIndent())

        db.execSQL("INSERT INTO usuarios(nombre, clave) VALUES ('admin', '1234')")
        db.execSQL("INSERT INTO usuarios(nombre, clave) VALUES ('sara', 'sara2025')")
    }
    //onUpgrade
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
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

    //fun insertarSocios(){}

}