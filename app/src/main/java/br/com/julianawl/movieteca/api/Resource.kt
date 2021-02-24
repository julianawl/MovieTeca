package br.com.julianawl.movieteca.api

class Resource<T>(
    val dado: T?,
    val erro: String? = null
)