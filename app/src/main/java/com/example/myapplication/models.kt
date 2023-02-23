package dev.mvillasenor.evaluation

data class Country(
    val code: String,
    val name: String,
    val nativeName: String,
    val phone: List<Int>,
    val continent: String,
    val capital: String,
    val currency: List<String>,
    val languages: List<String>,
)