package de.nimelrian.aoc2023

import de.nimelrian.aoc.aoc

fun main() = aoc {
    part(solver = ::solve1)
}

fun solve1(lines: Sequence<String>): Int {
    val wholeSchematic = lines.joinToString("\n")
    val lineLength = wholeSchematic.indexOf('\n') + 1

    val symbolIndices = wholeSchematic.asSequence()
            .mapIndexedNotNull { index, char -> index.takeIf { char.isSymbol() } }
            .toList()
    val symbols = symbolIndices.map {
        Pair(wholeSchematic[it], getSearchIndices(it, lineLength, wholeSchematic.length - 1))
    }
    val searchIndices = symbols.flatMap { it.second }

    val numbers = Regex("\\d+")
        .findAll(wholeSchematic)
        .map { Pair(it.value.toInt(), it.range) }
        .toList()
    val partNumbers = numbers
        .filter { (_, range) -> searchIndices.any { it in range } }
        .toList()

    return partNumbers.sumOf { it.first }
}

fun getSearchIndices(baseIndex: Int, lineLength: Int, max: Int): List<Int> {
    val indicesLineAbove = IntRange(baseIndex - lineLength - 1, baseIndex - lineLength + 1)
    val indicesSymbolLine = IntRange(baseIndex - 1, baseIndex + 1)
    val indicesLineBelow = IntRange(baseIndex + lineLength - 1, baseIndex + lineLength + 1)
    return (indicesLineAbove + indicesSymbolLine + indicesLineBelow).filter { it in 0..max }
}

fun Char.isSymbol() = (this.isDigit() || this == '.' || this == '\n').not()
