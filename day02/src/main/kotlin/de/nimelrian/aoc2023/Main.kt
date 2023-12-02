package de.nimelrian.aoc2023

import de.nimelrian.aoc.From
import de.nimelrian.aoc.aoc
import kotlin.math.max

fun main() = aoc {
    part(solver = ::solve1)
    part(solver = ::solve2)
}

fun solve1(lines: Sequence<String>): Int {
    val bag = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14,
    )

    return lines.map(Game::from)
        .filter { game ->
            game.sets.all {
                it.cubes.all { (color, count) -> ((bag[color] ?: 0) >= count) }
            }
        }
        .map(Game::id)
        .sum()
}

fun solve2(lines: Sequence<String>): Int {
    return lines.map(Game::from)
        .map { game ->
            game.sets.asSequence()
                .flatMap { it.cubes.entries }
                .fold(mutableMapOf<String, Int>()) { minCubeCounts, (color, count) ->
                    minCubeCounts[color] = max(minCubeCounts[color] ?: 0, count)
                    minCubeCounts
                }.values.fold(1, Int::times)
        }
        .sum()
}

data class Game(val id: Int, val sets: List<CubeSet>) {
    companion object : From<String, Game> {
        override fun from(source: String): Game {
            val (gameInfo, sets) = source.split(':')
            val (_, gameId) = gameInfo.split(' ')
            val cubeSets = sets.split(';').map(CubeSet::from)
            return Game(gameId.toInt(), cubeSets)
        }
    }
}

data class CubeSet(val cubes: Map<String, Int>) {
    companion object : From<String, CubeSet> {
        override fun from(source: String): CubeSet = source.split(',')
            .map(String::trim)
            .associate {
                val (count, color) = it.split(' ')
                color to count.toInt()
            }
            .let(::CubeSet)
    }
}
