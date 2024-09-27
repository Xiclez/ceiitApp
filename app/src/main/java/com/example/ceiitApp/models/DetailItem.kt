package com.example.ceiitApp.models

sealed class DetailItem {
    data class PrestamoItem(val prestamo: Prestamo) : DetailItem()
    data class ObjetoItem(val objeto: Objeto) : DetailItem()
    data class NoticiaItem(val noticia: Noticia) : DetailItem()
}
