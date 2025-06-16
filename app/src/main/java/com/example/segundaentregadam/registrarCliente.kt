package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class registrarCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_cliente)

        val dbHelper = UserDBHelper(this)
        val edtxNombre = findViewById<EditText>(R.id.EdTextNombre)
        val edtxApellido = findViewById<EditText>(R.id.EdTextApellido)
        val edtxDni = findViewById<EditText>(R.id.EdTextDocumento)
        val btnconfirmarRegistroNuevo = findViewById<Button>(R.id.btnconfirmarRegistroNuevo)

        btnconfirmarRegistroNuevo.setOnClickListener(){
            val nombre = edtxNombre.text.toString().trim()
            val apellido = edtxApellido.text.toString().trim()
            val dni = edtxDni.text.toString().trim()


            if (dbHelper.insertarSocios(nombre, apellido, dni)){
                val intRegistroSocio = Intent(this, feedbackRegistroNuevoCliente::class.java)
                startActivity(intRegistroSocio)
            }
            else{
                val IntentError = Intent(this, FeedbackRegistroNuevoClienteError::class.java)
                startActivity(IntentError)
            }
            edtxNombre.text.clear()
            edtxApellido.text.clear()
            edtxDni.text.clear()

        }

        val boton = findViewById<Button>(R.id.btnVolver)
        boton.setOnClickListener {
            val intento = Intent(this, principal::class.java)
            startActivity(intento)
        }

    }
}