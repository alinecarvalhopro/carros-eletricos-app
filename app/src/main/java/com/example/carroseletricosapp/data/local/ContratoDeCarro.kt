package com.example.carroseletricosapp.data.local

import android.provider.BaseColumns

object ContratoDeCarro {
    object EntradaDeCarro : BaseColumns {
        const val TABLE_NAME = "carro"
        const val COLUMN_NAME_PRECO = "preco"
        const val COLUMN_NAME_BATERIA = "bateria"
        const val COLUMN_NAME_POTENCIA = "potencia"
        const val COLUMN_NAME_RECARGA = "recarga"
        const val COLUMN_NAME_URL_PHOTO = "urlPhoto"
    }

    const val SQL_TABLE = "CREATE TABLE ${EntradaDeCarro.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${EntradaDeCarro.COLUMN_NAME_PRECO} TEXT," +
            "${EntradaDeCarro.COLUMN_NAME_BATERIA} TEXT," +
            "${EntradaDeCarro.COLUMN_NAME_POTENCIA} TEXT," +
            "${EntradaDeCarro.COLUMN_NAME_RECARGA} TEXT," +
            "${EntradaDeCarro.COLUMN_NAME_URL_PHOTO} TEXT,)"

    const val SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS ${EntradaDeCarro.TABLE_NAME}"
}