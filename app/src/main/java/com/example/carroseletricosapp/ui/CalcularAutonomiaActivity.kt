package com.example.carroseletricosapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carroseletricosapp.R

class CalcularAutonomiaActivity : AppCompatActivity() {

    lateinit var btnVoltar: ImageView
    lateinit var precokwh: EditText
    lateinit var kmPercorrido: EditText
    lateinit var resultado: TextView
    lateinit var btnCalcular: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_autonomia)
        configurarViews()
        configurarListeners()
    }

    fun configurarViews() {
        btnVoltar = findViewById(R.id.iv_voltar)
        precokwh = findViewById(R.id.et_preco_kwh)
        kmPercorrido = findViewById(R.id.et_km_percorrido)
        btnCalcular = findViewById(R.id.btn_calcular)
        resultado = findViewById(R.id.tv_resultado)
    }

    fun configurarListeners() {
        btnCalcular.setOnClickListener {
            calcular()
        }
        btnVoltar.setOnClickListener {
            finish()
        }
    }

    fun calcular() {
        val preco = precokwh.text.toString().toFloat()
        val km = kmPercorrido.text.toString() .toFloat()
        val calculo = preco / km
        resultado.text = calculo.toString()
    }
}