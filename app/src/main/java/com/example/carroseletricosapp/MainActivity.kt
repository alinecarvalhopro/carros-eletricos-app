package com.example.carroseletricosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var precokwh: EditText
    lateinit var kmPercorrido: EditText
    lateinit var btnCalcular: Button
    lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configurarViews()
        configurarListeners()
    }

    fun configurarViews() {
        precokwh = findViewById(R.id.et_preco_kwh)
        kmPercorrido = findViewById(R.id.et_km_percorrido)
        btnCalcular = findViewById(R.id.btn_calcular)
        resultado = findViewById(R.id.tv_resultado)
    }

    fun configurarListeners() {
        btnCalcular.setOnClickListener {
            calcular()
        }
    }

    fun calcular() {
        val preco = precokwh.text.toString().toFloat()
        val km = kmPercorrido.text.toString() .toFloat()
        val calculo = preco / km
        resultado.text = calculo.toString()
    }
}