package com.example.segundaentregadam

import UserDBHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val dbHelper = UserDBHelper(this)
        val user = findViewById<EditText>(R.id.editTextUsuario)
        val pass = findViewById<EditText>(R.id.editTextPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegistrarseAdmin = findViewById<Button>(R.id.btnRegistrarse)

        btnLogin.setOnClickListener(){
            val userString = user.text.toString().trim()
            val passString = pass.text.toString().trim()

            if(dbHelper.login(userString, passString)){
                val intentar = Intent(this, principal::class.java)
                startActivity(intentar);
            } else
            {
                val intentarError = Intent(this, FeedbackLoginError::class.java)
                startActivity(intentarError);
            }

        }

        btnRegistrarseAdmin.setOnClickListener(){
            val intLoginRegistro = Intent(this, Registrarse::class.java)
            startActivity(intLoginRegistro);
        }


    }
}