package com.example.carroseletricosapp.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.carroseletricosapp.R
import com.example.carroseletricosapp.data.CarrosAPI
import com.example.carroseletricosapp.domain.Carro
import com.example.carroseletricosapp.ui.adapter.CarroAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarroFragment : Fragment() {

    lateinit var fabCalcular: FloatingActionButton
    lateinit var listaCarros: RecyclerView
    lateinit var barraDeProgresso: ProgressBar
    lateinit var semInternetImagem: ImageView
    lateinit var semInternetText: TextView
    lateinit var carrosApi: CarrosAPI

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.carro_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarRetrofit()
        configurarView(view)
        configurarListeners()
    }

    override fun onResume() {
        super.onResume()
        if (verificarInternet(context)) {
            buscarTodosOsCarros()
        } else {
            emptyState()
        }
    }

    fun configurarRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://igorbag.github.io/cars-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        carrosApi = retrofit.create(CarrosAPI::class.java)
    }

    fun buscarTodosOsCarros() {
        carrosApi.buscarTodosOsCarros().enqueue(object : Callback<List<Carro>> {
            override fun onResponse(call: Call<List<Carro>>, response: Response<List<Carro>>) {
                if (response.isSuccessful) {
                    barraDeProgresso.isVisible = false
                    semInternetImagem.isVisible = false
                    semInternetText.isVisible = false
                    response.body()?.let {
                        configurarLista(it)
                    }
                } else {
                    Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Carro>>, t: Throwable) {
                Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
            }

        })
    }

    fun emptyState() {
        barraDeProgresso.isVisible = false
        listaCarros.isVisible = false
        semInternetImagem.isVisible = true
        semInternetText.isVisible = true
    }

    fun configurarView(view: View) {
        view.apply {
            fabCalcular = findViewById(R.id.fab_calcular)
            listaCarros = findViewById(R.id.rv_lista_carros)
            barraDeProgresso = findViewById(R.id.pb_loading)
            semInternetImagem = findViewById(R.id.iv_empty_state)
            semInternetText = findViewById(R.id.tv_no_wifi)
        }
    }

    fun configurarLista(lista: List<Carro>) {
        val carroAdapter = CarroAdapter(lista)
        listaCarros.apply {
            listaCarros.isVisible = true
            adapter = carroAdapter
        }
        carroAdapter.itemCarroListner = {carro ->
            val bateria = carro.bateria
        }
    }

    fun configurarListeners() {
        fabCalcular.setOnClickListener {
            startActivity(Intent(context, CalcularAutonomiaActivity::class.java))
        }
    }

    fun verificarInternet(context: Context?): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}