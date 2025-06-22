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

class Pago : AppCompatActivity() {

    private lateinit var dbHelper:UserDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pago)

        dbHelper = UserDBHelper(this)

        val precio = intent.getStringExtra("precio") ?: ""
        val actividadOCuota = intent.getStringExtra("actividadOCuota") ?: ""
        val actividad = intent.getStringExtra("actividad") ?: ""
        val horario = intent.getStringExtra("horario") ?: ""
        val dniSocio = intent.getStringExtra("dniSocio") ?: ""

        val montoAPagar = findViewById<TextView>(R.id.txtMonto)
        montoAPagar.text = "$" + precio


        val boton = findViewById<Button>(R.id.btnVolver)
        boton.setOnClickListener {
            val intento = Intent(this, principal::class.java)
            startActivity(intento)
        }

        val pagar = findViewById<Button>(R.id.btnPagar)
        pagar.setOnClickListener {
            if (actividadOCuota == "Actividad"){
                dbHelper.descontarCupo(actividad, horario)
            }
            else {
                dbHelper.registrarPagoDeCuota(dniSocio)
            }
            val intento = Intent(this, Feedback_Registro::class.java)
            startActivity(intento)
        }
    }
}