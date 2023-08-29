package com.example.carroseletricosapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.carroseletricosapp.R

class MainActivity : AppCompatActivity() {

    lateinit var btnCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configurarViews()
        configurarListeners()
    }

    fun configurarViews() {
        btnCalcular = findViewById(R.id.btn_calcular)
    }

    fun configurarListeners() {
        btnCalcular.setOnClickListener {
        startActivity(Intent(this, CalcularAutonomiaActivity::class.java))
        }
    }

}