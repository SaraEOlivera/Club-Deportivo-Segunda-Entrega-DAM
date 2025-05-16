package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Feedback_Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_feedback_registro)

        val btnIrPrincipal = findViewById<Button>(R.id.btnIrPrincipal)

        btnIrPrincipal.setOnClickListener(){
            val intFeedbackPrincipal = Intent(this, principal::class.java)
            startActivity(intFeedbackPrincipal)
        }

    }
}