package com.example.segundaentregadam

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListadoSocios : AppCompatActivity() {

    private lateinit var dbHelper:UserDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_socios)

        dbHelper = UserDBHelper(this)
        mostrarSociosConCuotaVencida()

        val btnvolverPrincipal = findViewById<Button>(R.id.btnVolver)

        btnvolverPrincipal.setOnClickListener(){
            val intListadoSociosMenuPrincipal = Intent(this, principal::class.java)
            startActivity(intListadoSociosMenuPrincipal)
        }
    }
    private fun mostrarSociosConCuotaVencida(){
        val listView = findViewById<ListView>(R.id.listaSocios)
        val lista =  dbHelper.obtenerSociosConCuotaVencida()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
        listView.adapter = adapter
    }
}