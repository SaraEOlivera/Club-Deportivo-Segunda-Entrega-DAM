package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListadoSocios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_socios)

        val btnvolverPrincipal = findViewById<Button>(R.id.btnvolverPrincipal)

        btnvolverPrincipal.setOnClickListener(){
            val intListadoSociosMenuPrincipal = Intent(this, principal::class.java)
            startActivity(intListadoSociosMenuPrincipal)
        }
    }
}