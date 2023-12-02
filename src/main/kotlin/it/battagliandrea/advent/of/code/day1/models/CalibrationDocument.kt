package it.battagliandrea.advent.of.code.day1.models

import it.battagliandrea.advent.of.code.utils.readLines

data class CalibrationDocument (
    val lines: List<CalibrationLine>
){
    fun calibrationSum() = lines
        .map(CalibrationLine::getCalibrationValue)
        .sum()

    companion object {
        fun createFromFileName(fileName: String) =
            fileName.readLines()
                .map { CalibrationLine(it) }
                .let { CalibrationDocument(it) }
    }
}