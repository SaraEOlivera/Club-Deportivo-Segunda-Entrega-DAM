package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FeedbackLoginError : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_login_error)

        val btnFbLogin = findViewById<Button>(R.id.btnVolverIntentar)
        btnFbLogin.setOnClickListener(){
            val intFbErrorLogin = Intent(this, Login::class.java)
            startActivity(intFbErrorLogin)
        }




    }
}
