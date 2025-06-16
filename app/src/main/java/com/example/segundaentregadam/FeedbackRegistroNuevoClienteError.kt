package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FeedbackRegistroNuevoClienteError : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_nuevo_cliente_error)

        val btnVolverIntentar = findViewById<Button>(R.id.btnVolverIntentar)
        val btnPaginaPrincipal = findViewById<Button>(R.id.btnIrPrincipal)

        btnVolverIntentar.setOnClickListener{
            val intentoVolver = Intent(this, registrarCliente::class.java)
            startActivity(intentoVolver)
        }

        btnPaginaPrincipal.setOnClickListener{
            val intentoVolverPrincipal = Intent(this, principal::class.java)
            startActivity(intentoVolverPrincipal)
        }


    }
}