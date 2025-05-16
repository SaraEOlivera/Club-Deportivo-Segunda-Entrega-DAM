package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)


        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegistrarseAdmin = findViewById<Button>(R.id.btnRegistrarse)

        btnLogin.setOnClickListener(){
            val intentar = Intent(this, principal::class.java)
            startActivity(intentar);
        }

        btnRegistrarseAdmin.setOnClickListener(){
            val intLoginRegistro = Intent(this, Registrarse::class.java)
            startActivity(intLoginRegistro);
        }


    }
}