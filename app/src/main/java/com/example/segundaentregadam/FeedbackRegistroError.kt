package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FeedbackRegistroError : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_registro_error)


        val btnFbLoginError = findViewById<Button>(R.id.btnIrPrincipal)
        btnFbLoginError.setOnClickListener(){
            val intFbErrorRegistrarse = Intent(this, Registrarse::class.java)
            startActivity(intFbErrorRegistrarse)
        }
    }

}