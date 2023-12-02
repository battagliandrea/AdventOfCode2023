package it.battagliandrea.advent.of.code.utils

import java.io.File
import java.io.FileNotFoundException

@Throws(FileNotFoundException::class)
fun String.readLines(): List<String> =
    File("src/main/resources/$this").bufferedReader().readLines()

@Throws(FileNotFoundException::class)
fun String.readText(): String =
    File("src/main/resources/$this").bufferedReader().readText()
