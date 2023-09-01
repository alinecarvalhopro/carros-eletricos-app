package com.example.carroseletricosapp.ui

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carroseletricosapp.R
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.nio.channels.AsynchronousByteChannel

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
        configurarResultadoSalvo()
    }

    private fun configurarResultadoSalvo() {
        val valorCalculado = buscarSharedPref()
        resultado.text = valorCalculado.toString()
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
        val km = kmPercorrido.text.toString().toFloat()
        val calculo = preco / km
        resultado.text = calculo.toString()
        salvarSharedPref(calculo)
    }

    fun salvarSharedPref(resultado : Float) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putFloat(getString(R.string.calculo_salvo), resultado)
            apply()
        }
    }

    fun buscarSharedPref(): Float {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getFloat(getString(R.string.calculo_salvo), 0.0f)
    }

}