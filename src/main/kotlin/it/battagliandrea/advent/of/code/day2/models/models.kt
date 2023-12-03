package it.battagliandrea.advent.of.code.day2.models
import it.battagliandrea.advent.of.code.utils.readLines
import sun.java2d.loops.GeneralRenderer

enum class CubeColor {
    RED, GREEN, BLUE
}

data class GameSet(
    val cubes: Map<CubeColor, Int>
){
    val redNum = cubes[CubeColor.RED] ?: 0
    val greenNum = cubes[CubeColor.GREEN] ?: 0
    val blueNum = cubes[CubeColor.BLUE] ?: 0

    companion object {
        fun createFromString(line: String): List<GameSet> {
            return line.split(";")
                .map {
                    val red = "(\\d+)\\sred".toRegex().find(it)?.groupValues?.lastOrNull()?.toIntOrNull() ?: 0
                    val green = "(\\d+)\\sgreen".toRegex().find(it)?.groupValues?.lastOrNull()?.toIntOrNull() ?: 0
                    val blue = "(\\d+)\\sblue".toRegex().find(it)?.groupValues?.lastOrNull()?.toIntOrNull() ?: 0

                    GameSet(
                        cubes = mapOf(
                            CubeColor.RED to red,
                            CubeColor.BLUE to blue,
                            CubeColor.GREEN to green
                        )
                    )
                }
        }
    }
}

data class Game(
    val id: Int,
    val sets: List<GameSet>
) {
    val isPossible: Boolean =
       !sets.any { it.redNum > RED_MAX }
               && !sets.any{ it.greenNum > GREEN_MAX }
               && !sets.any{ it.blueNum > BLUE_MAX }

    fun getPower(): Int {
        val maxRed = sets.maxOfOrNull { it.redNum } ?: 0
        val maxGreen = sets.maxOfOrNull { it.greenNum } ?: 0
        val maxBlue = sets.maxOfOrNull { it.blueNum } ?: 0
        return maxRed * maxBlue * maxGreen
    }

    companion object {
        const val RED_MAX = 12
        const val GREEN_MAX = 13
        const val BLUE_MAX = 14

        fun createFromFileName(fileName: String): List<Game> =
            fileName.readLines()
                .map { line ->
                    val gameId = "^Game\\s(\\d+)".toRegex().find(line.substringBefore(":"))?.groupValues!!.last().toInt()
                    val gameSets = GameSet.createFromString(line)
                    Game(
                        id = gameId,
                        sets = gameSets
                    )
                }
    }
}