package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Horarios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_horarios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val boton = findViewById<Button>(R.id.btnVolver)
        boton.setOnClickListener {
            val intento = Intent(this, Actividades::class.java)
            startActivity(intento)
        }

        val elegirHorario = findViewById<TextView>(R.id.txtListaDeHorarios)
        elegirHorario.setOnClickListener {
            val intento = Intent(this, Pago::class.java)
            startActivity(intento)
        }
    }
}