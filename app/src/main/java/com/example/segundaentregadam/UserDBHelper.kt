package com.example.segundaentregadam

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class UserDBHelper(context: Context):SQLiteOpenHelper(context, "ClubDB", null, 10) {
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
                profesor TEXT,
                precio DECIMAL
            )
        """.trimIndent())
        db.execSQL("INSERT INTO actividades(nombre, profesor, precio) VALUES ('Futbol', 'Leo Messi', 25123.0)")
        db.execSQL("INSERT INTO actividades(nombre, profesor, precio) VALUES ('Basquet', 'Manu Ginobili', 19321.4)")

        db.execSQL("""
            CREATE TABLE horarios (
	            id INTEGER PRIMARY KEY AUTOINCREMENT,	
                hora INTEGER,
                cupoDisponible INTEGER,
                actividad TEXT
            )
        """.trimIndent())
        db.execSQL("INSERT INTO horarios(hora, cupoDisponible, actividad) VALUES (11, 22, 'Futbol')")
        db.execSQL("INSERT INTO horarios(hora, cupoDisponible, actividad) VALUES (15, 2 , 'Futbol')")
        db.execSQL("INSERT INTO horarios(hora, cupoDisponible, actividad) VALUES (20, 8 , 'Futbol')")
        db.execSQL("INSERT INTO horarios(hora, cupoDisponible, actividad) VALUES (9, 2, 'Basquet')")
        db.execSQL("INSERT INTO horarios(hora, cupoDisponible, actividad) VALUES (13, 0 , 'Basquet')")
        db.execSQL("INSERT INTO horarios(hora, cupoDisponible, actividad) VALUES (18, 1 , 'Basquet')")

        db.execSQL("""
            CREATE TABLE cuotasMensuales (
	            id INTEGER PRIMARY KEY AUTOINCREMENT,	
                dniSocio INTEGER,
                cuotaPagada DATE
            )
        """.trimIndent())
        db.execSQL("INSERT INTO cuotasMensuales(dniSocio, cuotaPagada) VALUES (77, '2025-05-01')")

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
        db.execSQL("""
            CREATE TABLE valorDeCuota (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                precio DECIMAL
            )
        """.trimIndent())
        db.execSQL("""
            INSERT INTO valorDeCuota(precio) VALUES (38456.7)
        """.trimIndent())
    }
    //onUpgrade
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS usuarios")
        db.execSQL("DROP TABLE IF EXISTS actividades")
        db.execSQL("DROP TABLE IF EXISTS horarios")
        db.execSQL("DROP TABLE IF EXISTS cuotasMensuales")
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

    fun obtenerHorariosDeActividad(actividad: String):List<String>{

        val horariosDisponibles = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT hora, cupoDisponible FROM horarios WHERE actividad =?",
            arrayOf(actividad)
        )
        if (cursor.moveToFirst()) {
            do {
                val hora = cursor.getInt(0)
                val cupoDisponible = cursor.getInt(1)
                if (cupoDisponible != 0) {
                    horariosDisponibles.add("$hora hs - $cupoDisponible")
                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return horariosDisponibles
    }

    fun obtenerPrecioDeActividad(actividad: String): Double{

        var precio = 0.0
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT precio FROM actividades WHERE nombre = ? LIMIT 1",
            arrayOf(actividad)
        )
        if (cursor.moveToFirst()) {
            precio = cursor.getDouble(0)
        }
        cursor.close()
        return precio
    }

    fun descontarCupo(actividad: String, horario: String) {
        val db = writableDatabase
        db.execSQL("UPDATE horarios " +
                    "SET cupoDisponible = cupoDisponible - 1 " +
                    "WHERE actividad = ? AND hora = ?",
            arrayOf(actividad, horario)
        )
    }


    fun obtenerPrecioDeCuota(): Double {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT precio FROM valorDeCuota", null
        )
        var precio = 0.0
        if (cursor.moveToFirst()) {
            precio = cursor.getDouble(0)
        }
        cursor.close()
        return precio
    }

    fun obtenerCliente(dni: String):Cursor? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM clientes WHERE dni =?", arrayOf(dni)
        )
        if (cursor.moveToFirst()){
            return cursor
        } else{
            return null
        }
    }

    fun obtenerSociosConCuotaVencida(): List<String> {
        val lista = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT c.dni, c.nombre, c.apellido, MAX(cm.cuotaPagada) AS ultimaCuota " +
                "FROM clientes AS c " +
                "LEFT JOIN cuotasMensuales AS cm ON c.dni = cm.dniSocio " +
                "WHERE c.esSocio = 1 " +
                "GROUP BY c.dni, c.nombre, c.apellido", null
        )

        val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val hoy = Calendar.getInstance()

        if (cursor.moveToFirst()) {
            do {
                val dni = cursor.getString(0)
                val nombre = cursor.getString(1)
                val apellido = cursor.getString(2)
                val fechaStr = cursor.getString(3)

                val estaVencido = if (fechaStr != null) {
                    val fechaCuota = formato.parse(fechaStr)
                    val fechaLimite = Calendar.getInstance().apply {
                        time = fechaCuota!!
                        add(Calendar.MONTH, 1)
                    }
                    hoy.after(fechaLimite)
                } else {
                    true
                }
                if (estaVencido) {
                    lista.add("$nombre $apellido - DNI: $dni")
                }
            } while (cursor.moveToNext())
        }
        cursor.close()

        return lista
    }


    fun registrarPagoDeCuota(dniSocio: String) {
        val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val fechaActual = formato.format(java.util.Date())

        val db = writableDatabase
        val valores = ContentValues().apply {
            put("dniSocio", dniSocio)
            put("cuotaPagada", fechaActual)
        }
        db.insert("cuotasMensuales", null, valores)
    }

    fun verificarSiHayDeuda(dniSocio: String): Boolean  {

        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT MAX(cuotaPagada) FROM cuotasMensuales WHERE dniSocio = ?",
            arrayOf(dniSocio)
        )
        val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val hoy = Calendar.getInstance()

        var hayDeuda = true

        if (cursor.moveToFirst()) {
            val fechaStr = cursor.getString(0)
            if (fechaStr != null) {
                val fechaUltimoPago = formato.parse(fechaStr)
                val fechaLimite = Calendar.getInstance().apply {
                    time = fechaUltimoPago!!
                    add(Calendar.MONTH, 1)
                }
                hayDeuda = hoy.after(fechaLimite)
            }
        }
        cursor.close()
        return hayDeuda
    }
}






























