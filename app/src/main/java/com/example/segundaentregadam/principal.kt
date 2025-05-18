package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)

        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        val btnActividades = findViewById<Button>(R.id.btnActividades)
        val btnAbonarCuota = findViewById<Button>(R.id.btnAbonarCuota)
        val btnRegistrarCliente = findViewById<Button>(R.id.btnRegistrarCliente)
        val btnSeleccionarCliente = findViewById<Button>(R.id.btnSeleccionarCliente)

        btnActividades.setOnClickListener(){
            val intPrincipalActividades = Intent(this, Actividades::class.java)
            startActivity(intPrincipalActividades)
        }

        btnAbonarCuota.setOnClickListener(){
            val intPrincipalSeleccionarCliente = Intent(this, SeleccionarCliente::class.java)
            startActivity(intPrincipalSeleccionarCliente)
        }

        btnRegistrarCliente.setOnClickListener(){
            val intPrincipalRegistroCliente = Intent(this, registrarCliente::class.java)
            startActivity(intPrincipalRegistroCliente)
        }

        btnSeleccionarCliente.setOnClickListener(){
            val intPrincipalListadoSocios = Intent(this, ListadoSocios::class.java)
            startActivity(intPrincipalListadoSocios)
        }

        btnCerrarSesion.setOnClickListener(){
            val intPrincipalBienvenida = Intent(this, MainActivity::class.java)
            startActivity(intPrincipalBienvenida)
        }

    }
}