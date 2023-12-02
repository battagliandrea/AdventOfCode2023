package it.battagliandrea.advent.of.code.day1.models

import it.battagliandrea.advent.of.code.day1.Dictionary

data class CalibrationLine (
    val code: String
){
    fun getCalibrationValueV1(): Long {
        val firstValue = code.first { it.isDigit() }
        val lastValue = code.last { it.isDigit() }
        return "${firstValue}${lastValue}".toLong()
    }

    fun getCalibrationValueV2(): Long {
        val firstValueKey = code.findAnyOf(Dictionary.digits.keys)!!.second
        val firstValue = Dictionary.digits[firstValueKey]

        val lastValueKey = code.findLastAnyOf(Dictionary.digits.keys)!!.second
        val lastValue = Dictionary.digits[lastValueKey]

        return "${firstValue}${lastValue}".toLong()
    }


}
