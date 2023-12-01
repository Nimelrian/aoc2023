package de.nimelrian.aoc2023

import de.nimelrian.aoc.aoc

fun main() = aoc {
    part(solver = ::solve1)
}

fun solve1(lines: Sequence<String>) =
    lines.map { line ->
        val (firstDigit, lastDigit) = line.asSequence()
            .mapNotNull { it.digitToIntOrNull() }
            .fold(Pair<Int?, Int?>(null, null)) { (first, _), next ->
                Pair(first ?: next, next)
            }

        requireNotNull(firstDigit)
        requireNotNull(lastDigit)

        firstDigit * 10 + lastDigit
    }
        .sum()