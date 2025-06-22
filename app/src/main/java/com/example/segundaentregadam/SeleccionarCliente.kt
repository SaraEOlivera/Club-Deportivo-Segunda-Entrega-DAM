package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.view.View
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

        var habilitadoAPagar = false
        val btnIrAPagos = findViewById<Button>(R.id.btnIrAPagos)
        val btnBuscarSocio = findViewById<Button>(R.id.btnBuscarSocio)
        val editDNI = findViewById<EditText>(R.id.editDniSocio)
        val cardInfoSocio = findViewById<CardView>(R.id.cardInfoSocio)
        val datosSocio = findViewById<TextView>(R.id.txtDatosSocio)
        val btnVolverAHorarios = findViewById<Button>(R.id.btnvolverAHorarios)

        val activityPorLaQueFueRedirigida = intent.getStringExtra("activityPorLaQueFueRedirigida") ?: ""
        val actividad = intent.getStringExtra("actividad") ?: ""
        val horario = intent.getStringExtra("horario") ?: ""
        var precio = ""
        var dniSocio = ""

        if (activityPorLaQueFueRedirigida == "Horarios"){
            btnVolverAHorarios.visibility = View.VISIBLE
            precio = dbHelper.obtenerPrecioDeActividad(actividad).toString()
        }
        else {
            precio = dbHelper.obtenerPrecioDeCuota().toString()
        }
       
        btnBuscarSocio.setOnClickListener(){
            val dni = editDNI.text.toString().trim()
            val cursor = dbHelper.obtenerCliente(dni)
            if(cursor != null){
                val nombre= cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                val apellido= cursor.getString(cursor.getColumnIndexOrThrow("apellido"))
                val dni= cursor.getString(cursor.getColumnIndexOrThrow("dni"))
                val esSocio = cursor.getInt(cursor.getColumnIndexOrThrow("esSocio")) == 1

                val infoCliente = if(esSocio){
                    "Socio"
                } else {
                    "No es un socio"
                }

                val mostrarTexto = "Nombre: $nombre\n Apellido: $apellido \n DNI: $dni \n Estado: $infoCliente"

                datosSocio.text = mostrarTexto

                if (activityPorLaQueFueRedirigida == "Horarios") {
                    habilitadoAPagar = (infoCliente == "No es un socio")
                } else {
                    if (infoCliente == "Socio") {
                        habilitadoAPagar = dbHelper.verificarSiHayDeuda(dni)
                        if (habilitadoAPagar) {
                            dniSocio = dni
                        }
                        else {
                            Toast.makeText(this, "Socio con cuota al dia, no debe pagar.", Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            } else {
                Toast.makeText(this, "Socio inexistente", Toast.LENGTH_SHORT).show()
                datosSocio.text = ""
                habilitadoAPagar = false
            }
            editDNI.text.clear()
        }

        btnIrAPagos.setOnClickListener(){
            if (habilitadoAPagar && activityPorLaQueFueRedirigida == "Horarios") {
                val intento = Intent(this, Pago::class.java)
                intento.putExtra("precio", precio)
                intento.putExtra("actividadOCuota", "Actividad")
                intento.putExtra("actividad", actividad)
                intento.putExtra("horario", horario)
                startActivity(intento)
            }
            else if (habilitadoAPagar && activityPorLaQueFueRedirigida != "Horarios"){
                val intento = Intent(this, Pago::class.java)
                intento.putExtra("precio", precio)
                intento.putExtra("actividadOCuota", "Cuota")
                intento.putExtra("dniSocio", dniSocio)
                startActivity(intento)
            }
            else {
                Toast.makeText(this, "Seleccione un cliente válido", Toast.LENGTH_SHORT).show()
            }
        }

        btnVolverAHorarios.setOnClickListener(){
            val intento = Intent(this, Horarios::class.java)
            intento.putExtra("actividad", actividad)
            startActivity(intento)
        }

        val boton = findViewById<Button>(R.id.btnvolverAPrincipal)
        boton.setOnClickListener {
            val intento = Intent(this, principal::class.java)
            startActivity(intento)
        }
    }
}