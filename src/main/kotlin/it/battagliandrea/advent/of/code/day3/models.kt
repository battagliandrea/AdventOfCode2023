package it.battagliandrea.advent.of.code.day3

import it.battagliandrea.advent.of.code.utils.readLines
import kotlin.math.abs

data class Cell(val x: Int, val y: Int)

fun Cell.neighbours(other: Cell) =
    abs(x - other.x) <= 1 && abs(y - other.y) <= 1

sealed class Entity(val cells: List<Cell>)

fun Entity.neighbours(other: Entity) =
    cells.any { cell -> other.cells.any { cell.neighbours(it) } }

fun Entity.neighboursAny(others: List<Entity>) =
    others.any { it.neighbours(this) }

class Symbol(x: Int, y: Int, val value: Char) : Entity(listOf(Cell(x, y)))

class Number(x: Int, y: Int, val value: String) : Entity(List(value.length) { i -> Cell(x + i, y) })

data class Engine(
    private val numbers: List<Number>,
    private val symbols: List<Symbol>
) {

    fun getPartNumbers() = numbers
        .filter { number -> number.neighboursAny(symbols) }

    fun getGearRatio() = symbols
        .filter { it.value == '*' }
        .map { symbol ->
            val neighbours = numbers.filter { it.neighbours(symbol) }
            if (neighbours.size == 2) {
                val (a, b) = neighbours
                a.value.toInt() * b.value.toInt()
            } else {
                0
            }
        }

    companion object {

        fun createFromFileName(fileName: String) : Engine {
            val input = fileName.readLines()

            val numbers = mutableListOf<Number>()
            val symbols = mutableListOf<Symbol>()

            input.forEachIndexed { y, row ->
                var currentNumber = ""

                row.forEachIndexed { x, char ->
                    when (char) {
                        '.' -> Unit
                        in '0'..'9' -> currentNumber += char
                        else -> symbols += (Symbol(x, y, char))
                    }
                    if (char !in '0'..'9' && currentNumber.isNotEmpty()) {
                        numbers += (Number(x - currentNumber.length, y, currentNumber))
                        currentNumber = ""
                    }
                    numbers.forEach{ it.value }
                }

                if (currentNumber.isNotEmpty()) {
                    numbers += (Number(row.length - currentNumber.length, y, currentNumber))
                }
            }

            return Engine(numbers = numbers, symbols = symbols)
        }
    }
}
