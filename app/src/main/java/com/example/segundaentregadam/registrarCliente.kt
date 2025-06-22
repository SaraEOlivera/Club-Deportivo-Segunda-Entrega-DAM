package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
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
        val chbxSocio = findViewById<CheckBox>(R.id.chkbEsSocio)
        val chkbPresentoFicha = findViewById<CheckBox>(R.id.chkbPresentoFicha)
        val btnconfirmarRegistroNuevo = findViewById<Button>(R.id.btnconfirmarRegistroNuevo)

        btnconfirmarRegistroNuevo.setOnClickListener(){
            val nombre = edtxNombre.text.toString().trim()
            val apellido = edtxApellido.text.toString().trim()
            val dni = edtxDni.text.toString().trim()

            if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val esSocio = chbxSocio.isChecked
            val presentoFicha = chkbPresentoFicha.isChecked

            if (presentoFicha){
                if (dbHelper.insertarClientes(nombre, apellido, dni, esSocio)){
                    val intRegistroSocio = Intent(this, feedbackRegistroNuevoCliente::class.java)
                    startActivity(intRegistroSocio)
                }
                else{
                    val IntentError = Intent(this, FeedbackRegistroNuevoClienteError::class.java)
                    startActivity(IntentError)
                }
            }
            else {
                Toast.makeText(this, "Falta presentar ficha médica.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
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