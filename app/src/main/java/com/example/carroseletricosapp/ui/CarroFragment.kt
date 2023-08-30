package com.example.carroseletricosapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.carroseletricosapp.R
import com.example.carroseletricosapp.data.FabricaDeCarros
import com.example.carroseletricosapp.ui.adapter.CarroAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CarroFragment : Fragment() {

    lateinit var fabCalcular: FloatingActionButton
    lateinit var listaCarros: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.carro_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarView(view)
        configurarLista()
        configurarListeners()
    }

    fun configurarView(view: View) {
        view.apply {
            fabCalcular = findViewById(R.id.fab_calcular)
            listaCarros = findViewById(R.id.rv_lista_carros)
        }
    }

    fun configurarLista() {
        val dados = FabricaDeCarros.list

        val adapter = CarroAdapter(dados)
        listaCarros.adapter = adapter
    }

    fun configurarListeners() {
        fabCalcular.setOnClickListener {
            startActivity(Intent(context, CalcularAutonomiaActivity::class.java))
        }
    }
}