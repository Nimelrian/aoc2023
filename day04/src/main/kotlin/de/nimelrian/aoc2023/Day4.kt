package de.nimelrian.aoc2023

import de.nimelrian.aoc.From
import de.nimelrian.aoc.aoc

val WHITESPACE_REGEX = Regex("\\s+")

fun main() = aoc {
    part(solver = ::solve1)
    part(solver = ::solve2)
}

fun solve1(lines: Sequence<String>): Int {
    return lines.map(Scratchcard::from)
        .map(Scratchcard::getScore)
        .sum()
}

fun solve2(lines: Sequence<String>): Any {
    val winningNumbersByCard = lines.map(Scratchcard::from)
        .map(Scratchcard::getWinningNumberCount)
        .withIndex()
        .associateBy({ it.index + 1 }, IndexedValue<Int>::value)

    return winningNumbersByCard.asSequence()
        .fold(winningNumbersByCard.mapValues { 1 }.toMutableMap()) { cardCopies, (index, winningNumbers) ->
            if (winningNumbers == 0) return@fold cardCopies

            (index + 1..index + winningNumbers).forEach {
                cardCopies[it] = cardCopies[it]!! + cardCopies[index]!!
            }
            cardCopies
        }
        .values
        .sum()
}

data class Scratchcard(val winningNumbers: List<Int>, val scratchedNumbers: List<Int>) {
    companion object : From<String, Scratchcard> {
        override fun from(source: String): Scratchcard {
            val (winningNumbersString, scratchedNumbersString) = source
                .split(':')[1]
                .split('|')
                .map(String::trim)
            val winningNumbers = winningNumbersString.split(WHITESPACE_REGEX).map { it.toInt() }
            val scratchedNumbers = scratchedNumbersString.split(WHITESPACE_REGEX).map { it.toInt() }
            return Scratchcard(winningNumbers, scratchedNumbers)
        }
    }

    fun getScore() = when (val winningNumbers = getWinningScratchedNumbers().size) {
        0 -> 0
        else -> (1 shl (winningNumbers - 1))
    }

    fun getWinningNumberCount() = getWinningScratchedNumbers().size

    private fun getWinningScratchedNumbers() = scratchedNumbers.intersect(winningNumbers.toSet())
}
