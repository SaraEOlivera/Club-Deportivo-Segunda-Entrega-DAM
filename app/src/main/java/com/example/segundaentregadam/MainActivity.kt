package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.bienvenida)

        val btnlogin = findViewById<Button>(R.id.btnIniciarSesion)

        btnlogin.setOnClickListener(){
            val intentar = Intent(this, Login::class.java)
            startActivity(intentar);
        }
    }

}