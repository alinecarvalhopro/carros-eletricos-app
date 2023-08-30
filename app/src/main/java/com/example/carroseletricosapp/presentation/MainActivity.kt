package com.example.carroseletricosapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carroseletricosapp.R
import com.example.carroseletricosapp.presentation.adapter.CarroAdapter

class MainActivity : AppCompatActivity() {

    lateinit var btnCalcular: Button
    lateinit var listaCarros: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configurarViews()
        configurarListeners()
        configurarLista()
    }

    fun configurarViews() {
        btnCalcular = findViewById(R.id.btn_calcular)
        listaCarros = findViewById(R.id.rv_lista_carros)
    }

    fun configurarLista() {
        var dados = arrayOf("Carro 1 preço", "Carro 2 preço", "Carro 3 preço", "Carro 4 preço", "Carro 5 preço")

        val adapter = CarroAdapter(dados)
        listaCarros.adapter = adapter
    }



    fun configurarListeners() {
        btnCalcular.setOnClickListener {
            startActivity(Intent(this, CalcularAutonomiaActivity::class.java))
        }
    }

}