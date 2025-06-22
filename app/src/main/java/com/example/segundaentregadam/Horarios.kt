package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Horarios : AppCompatActivity() {

    private lateinit var dbHelper:UserDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_horarios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = UserDBHelper(this)

        val texto = intent.getStringExtra("actividad") ?: ""
        val actividad = texto.split(" - ")[0]

        mostrarHorarios(actividad)

        val boton = findViewById<Button>(R.id.btnVolver)
        boton.setOnClickListener {
            val intento = Intent(this, Actividades::class.java)
            startActivity(intento)
        }
        val elegirHorario = findViewById<ListView>(R.id.listHorarios)
        elegirHorario.setOnItemClickListener  { parent, view, position, id ->
            val horarioSeleccionado = parent.getItemAtPosition(position).toString()

            if (horarioSeleccionado == "No hay cupo disponible en ningun horario") {
                return@setOnItemClickListener
            }

            val horario = horarioSeleccionado.split(" hs ")[0]

            val intento = Intent(this, SeleccionarCliente::class.java)
            intento.putExtra("activityPorLaQueFueRedirigida", "Horarios")
            intento.putExtra("actividad", actividad)
            intento.putExtra("horario", horario)
            startActivity(intento)
        }
    }

    private fun mostrarHorarios(actividad: String) {
        val listView = findViewById<ListView>(R.id.listHorarios)
        var lista = dbHelper.obtenerHorariosDeActividad(actividad)
        if (lista.isEmpty()) {
            lista = listOf("No hay cupo disponible en ningun horario")
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
        listView.adapter = adapter
    }
}