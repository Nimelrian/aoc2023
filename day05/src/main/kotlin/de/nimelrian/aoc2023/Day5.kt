package de.nimelrian.aoc2023

import de.nimelrian.aoc.aoc
import de.nimelrian.aoc.chunkByPredicate
import de.nimelrian.aoc.takeHead
import java.util.function.Function
import kotlin.streams.asStream


fun main() = aoc {
    part(solver = ::solve1)
    part(solver = ::solve2)
}

fun solve1(lines: Sequence<String>): Long {
    val (seedLine, mapChunks) = lines.chunkByPredicate(true) { it.isEmpty() }
        .takeHead()
    val seeds = seedLine.single()
        .split(':')[1]
        .trim()
        .split(' ').asSequence()
        .map(String::toLong)

    val maps = buildMaps(mapChunks)

    return solveForSeeds(seeds, maps)
}

fun solve2(lines: Sequence<String>): Long {
    val (seedLine, mapChunks) = lines.chunkByPredicate(true) { it.isEmpty() }
        .takeHead()
    val seeds = seedLine.single()
        .split(':')[1]
        .trim()
        .split(' ').asSequence()
        .map(String::toLong)
        .chunked(2)
        .flatMap { (start, length) -> (start..<(start + length)).asSequence() }

    val maps = buildMaps(mapChunks)
    return solveForSeeds(seeds, maps)
}

fun solveForSeeds(seeds: Sequence<Long>, maps: List<TypeMap>): Long {
    val mapChain = maps.fold(Function.identity<Long>()) { chain, map ->
        chain.andThen { value ->
            val index = map.sources.indexOfFirst { it.contains(value) }
            if (index == -1)
                return@andThen value
            val offset = value - map.sources[index].first
            map.destinations[index].first + offset
        }
    }

    return seeds.asStream()
        .parallel()
        .map(mapChain::apply)
        .min(Long::compareTo)
        .orElseThrow()
}

fun buildMaps(mapChunks: Sequence<List<String>>): List<TypeMap> = mapChunks
    .map {
        val (sources, destinations) = it.asSequence()
            .drop(1)
            .map { mapLine ->
                val (destinationRangeStart, sourceRangeStart, rangeLength) = mapLine.trim()
                    .split(' ')
                    .map(String::toLong)

                Pair(
                    sourceRangeStart..<(sourceRangeStart + rangeLength),
                    destinationRangeStart..<(destinationRangeStart + rangeLength)
                )
            }
            .unzip()
        TypeMap(sources, destinations)
    }
    .toList()


data class TypeMap(val sources: List<LongRange>, val destinations: List<LongRange>)
