package it.battagliandrea.advent.of.code.day1.models

import it.battagliandrea.advent.of.code.utils.readLines

data class CalibrationDocument (
    val lines: List<CalibrationLine>
){
    fun calibrationSumV1() = lines
        .map(CalibrationLine::getCalibrationValueV1)
        .sum()

    fun calibrationSumV2() = lines
        .map(CalibrationLine::getCalibrationValueV2)
        .sum()

    companion object {
        fun createFromFileName(fileName: String) =
            fileName.readLines()
                .map { CalibrationLine(it) }
                .let { CalibrationDocument(it) }
    }
}