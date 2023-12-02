package it.battagliandrea.advent.of.code.day1

object Dictionary {
    val digits = buildMap {
        listOf(
            1 to "one",
            2 to "two",
            3 to "three",
            4 to "four",
            5 to "five",
            6 to "six",
            7 to "seven",
            8 to "eight",
            9 to "nine",
        ).forEach { (number, name) ->
            put(number.toString(), number)
            put(name, number)
        }
    }
}
