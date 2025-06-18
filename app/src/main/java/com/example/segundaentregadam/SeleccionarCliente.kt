package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SeleccionarCliente : AppCompatActivity() {
    private lateinit var dbHelper:UserDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seleccionar_cliente)

        dbHelper = UserDBHelper(this)
        val btnIrAPagos = findViewById<Button>(R.id.btnIrAPagos)
        val btnBuscarSocio = findViewById<Button>(R.id.btnBuscarSocio)
        val editDNI = findViewById<EditText>(R.id.editDniSocio)
        val cardInfoSocio = findViewById<CardView>(R.id.cardInfoSocio)
        val datosSocio = findViewById<TextView>(R.id.txtDatosSocio)

       
        btnBuscarSocio.setOnClickListener(){
            val dni = editDNI.text.toString().trim()
            val cursor = dbHelper.obtenerSocio(dni)
            if(cursor != null){
                val nombre= cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                val apellido= cursor.getString(cursor.getColumnIndexOrThrow("apellido"))
                val dniSocio= cursor.getString(cursor.getColumnIndexOrThrow("dni"))

                val mostrarTexto = "Nombre: $nombre\n Apellido: $apellido \n DNI: $dniSocio"
                datosSocio.text = mostrarTexto
            } else {
                Toast.makeText(this, "Socio inexistente", Toast.LENGTH_SHORT).show()
            }
            editDNI.text.clear()
        }

        btnIrAPagos.setOnClickListener(){
            val intSeleccionarSocioPago = Intent(this, Pago::class.java)
            startActivity(intSeleccionarSocioPago)
        }
        val btnvolverPrincipal = findViewById<Button>(R.id.btnvolverPrincipal)
        btnvolverPrincipal.setOnClickListener(){
            val intListadoSociosMenuPrincipal = Intent(this, principal::class.java)
            startActivity(intListadoSociosMenuPrincipal)
        }
    }

}