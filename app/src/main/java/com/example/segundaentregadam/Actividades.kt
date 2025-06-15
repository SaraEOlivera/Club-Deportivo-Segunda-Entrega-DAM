package com.example.segundaentregadam

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

class Actividades : AppCompatActivity() {

    private lateinit var dbHelper:UserDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actividades)

        dbHelper = UserDBHelper(this)
        mostrarActividades()

        val elegirActividad = findViewById<ListView>(R.id.listActividades1)
        elegirActividad.setOnItemClickListener  { parent, view, position, id ->
            val actividadSeleccionada = parent.getItemAtPosition(position).toString()
            val intento = Intent(this, Horarios::class.java)
            intento.putExtra("Actividad", actividadSeleccionada)
            startActivity(intento)
        }

        val boton = findViewById<Button>(R.id.btnIrPrincipal2)
        boton.setOnClickListener {
            val intento = Intent(this, principal::class.java)
            startActivity(intento)
        }




    }
    private fun mostrarActividades(){
        val listView = findViewById<ListView>(R.id.listActividades1)
        val lista =  dbHelper.obtenerActividades()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
        listView.adapter = adapter
    }
}