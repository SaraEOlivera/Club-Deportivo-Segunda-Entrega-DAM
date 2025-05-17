package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pago : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pago)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val boton = findViewById<Button>(R.id.btnVolver)
        boton.setOnClickListener {
            val intento = Intent(this, Horarios::class.java)
            startActivity(intento)
        }

        val pagar = findViewById<Button>(R.id.btnPagar)
        pagar.setOnClickListener {
            val intento = Intent(this, Feedback_Registro::class.java)
            startActivity(intento)
        }

    }
}