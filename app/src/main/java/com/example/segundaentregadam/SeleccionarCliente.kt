package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SeleccionarCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seleccionar_cliente)

        val btnIrAPagos = findViewById<Button>(R.id.btnIrAPagos)

        btnIrAPagos.setOnClickListener(){
            val intSeleccionarSocioPago = Intent(this, Pago::class.java)
            startActivity(intSeleccionarSocioPago)
        }

    }
}