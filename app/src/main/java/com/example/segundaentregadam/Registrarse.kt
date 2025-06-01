package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Registrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrarse)

        val dbHelper = UserDBHelper(this)
        val btnConfirmar = findViewById<Button>(R.id.btnConfirmar)
        val editNombre = findViewById<EditText>(R.id.editTextUsuario)
        val editpass = findViewById<EditText>(R.id.editTextPassword)
        val editRepetirPass = findViewById<EditText>(R.id.editRepetirPassword)

        btnConfirmar.setOnClickListener(){
            val nombre = editNombre.text.toString().trim()
            val pass = editpass.text.toString().trim()
            val repetirPass = editRepetirPass.text.toString().trim()
            if (dbHelper.insertarAdmins(nombre, pass)){
                val intRegistrarsePrincipal = Intent(this,Feedback_Registro::class.java)
                startActivity(intRegistrarsePrincipal)
            } else{
                val intentarError = Intent(this, FeedbackRegistroError::class.java)
                startActivity(intentarError)
            }
            editNombre.text.clear()
            editpass.text.clear()
            editRepetirPass.text.clear()
        }




    }
}