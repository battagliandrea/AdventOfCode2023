package it.battagliandrea.advent.of.code.day1.models

data class CalibrationLine (
    val code: String
){
    fun getCalibrationValue(): Long {
        val filterValue = code.first { it.isDigit() }
        val lastValue = code.last { it.isDigit() }
        return "${filterValue}${lastValue}".toLong()
    }
}
