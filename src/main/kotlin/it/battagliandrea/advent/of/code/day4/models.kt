package it.battagliandrea.advent.of.code.day4

import it.battagliandrea.advent.of.code.utils.readLines

fun List<String>.toInt() = this.map { it.trim().toInt() }

data class Scratchcard(
    val winningNumbers: List<Int>,
    val numbers: List<Int>
){
    fun getPoints(): Int {
        val intersect = winningNumbers.intersect(numbers.toSet())
        return if(intersect.isNotEmpty()){
            intersect.take(intersect.size -1)
                .fold(1){ acc, _ -> acc * 2 }
        } else {
            0
        }
    }

    companion object {
        fun createFromFileName(fileName: String) =
            fileName
                .readLines()
                 .map { line ->
                     val (winningNumbers, numbers) = line.substringAfter(": ").split(" | ")
                     Scratchcard(
                         winningNumbers = winningNumbers.windowed(2, 3).toInt(),
                         numbers = numbers.windowed(2, 3).toInt()
                     )
                }
    }
}