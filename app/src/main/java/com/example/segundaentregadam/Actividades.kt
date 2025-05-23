package com.example.segundaentregadam

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

class Actividades : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actividades)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val elegirActividad = findViewById<CardView>(R.id.cardActividades1)
        elegirActividad.setOnClickListener {
            val intento = Intent(this, Horarios::class.java)
            startActivity(intento)
        }

        val boton = findViewById<Button>(R.id.btnIrPrincipal2)
        boton.setOnClickListener {
            val intento = Intent(this, principal::class.java)
            startActivity(intento)
        }
    }
}