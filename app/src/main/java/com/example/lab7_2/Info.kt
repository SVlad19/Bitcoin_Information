package com.example.lab7_2

data class Info (
    val time: TimeData,
    val disclaimer: String,
    val chartName: String,
    val bpi: BpiData
)

data class TimeData(
    val updated: String,
    val updatedISO: String,
    val updateduk: String
)

data class BpiData(
    val USD: CurrencyData,
    val GBP: CurrencyData,
    val EUR: CurrencyData
)

data class CurrencyData(
    val code: String,
    val symbol: String,
    val rate: String,
    val description: String,
    val rate_float: Double
)