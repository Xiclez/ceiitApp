package com.example.ceiitApp.models

data class Prestamo(
    val id: String,
    val nombre: String,
    val categoria: String,
    val fechaPrestamo: String,
    val fechaDevolucionEstimada: String,
    val estado: String,
    val urlImagen: String
)