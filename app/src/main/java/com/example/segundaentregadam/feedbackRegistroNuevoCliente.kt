package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class feedbackRegistroNuevoCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_feedback_registro_nuevo_cliente)

        val boton = findViewById<Button>(R.id.btnVolver)
        boton.setOnClickListener {
            val intento = Intent(this, principal::class.java)
            startActivity(intento)
        }
    }
}