package com.example.carroseletricosapp.data

import com.example.carroseletricosapp.domain.Carro

object FabricaDeCarros {
    val list = listOf<Carro>(
     Carro(
         id = 1,
         preco = "R$ 300.000,00",
         bateria = "300 kWh",
         potencia = "200cv",
         recarga = "30 min",
         urlPhoto = "www.google.com.br",
         carrofavorito = false
     )
    )
}