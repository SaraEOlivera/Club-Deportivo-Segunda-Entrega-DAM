package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class registrarCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_cliente)


        val btnconfirmarRegistroNuevo = findViewById<Button>(R.id.btnconfirmarRegistroNuevo)

        btnconfirmarRegistroNuevo.setOnClickListener(){
            val intRegistrarClienteFeedback = Intent(this, feedbackRegistroNuevoCliente::class.java)
            startActivity(intRegistrarClienteFeedback)
        }
    }
}