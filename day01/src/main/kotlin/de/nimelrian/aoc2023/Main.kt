package de.nimelrian.aoc2023

import de.nimelrian.aoc.aoc

val digitsToDigits = (0..10).associateBy { it.toString() }
val wordsToDigits = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
)

fun main() = aoc {
    part(solver = ::solve1)
    part(solver = ::solve2)
}

fun solve1(lines: Sequence<String>) = lines.map(CalibrationValueParser(digitsToDigits)).sum()

fun solve2(lines: Sequence<String>) = lines.map(CalibrationValueParser(digitsToDigits + wordsToDigits)).sum()

class CalibrationValueParser(private val digitLookupTable: Map<String, Int>) : (String) -> Int {
    override fun invoke(line: String): Int = parseNumberFromLine(line)

    private fun parseNumberFromLine(line: String): Int {
        val (firstDigit, lastDigit) = parseEnclosingDigitsFromLine(line)
        return firstDigit * 10 + lastDigit
    }

    private fun parseEnclosingDigitsFromLine(line: String): Pair<Int, Int> {
        val needles = digitLookupTable.keys
        val first = line.findAnyOf(needles)?.second?.let(::toDigit)
        val last = line.findLastAnyOf(needles)?.second?.let(::toDigit)

        requireNotNull(first)
        requireNotNull(last)

        return Pair(first, last)
    }

    private fun toDigit(candidate: String): Int =
        digitLookupTable[candidate] ?: throw IllegalArgumentException("Failed to convert $candidate to a digit")
}
