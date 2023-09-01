package com.example.carroseletricosapp.data


import com.example.carroseletricosapp.domain.Carro
import retrofit2.Call
import retrofit2.http.GET
interface CarrosAPI {
    @GET("cars.json")
    fun buscarTodosOsCarros(): Call<List<Carro>>

}