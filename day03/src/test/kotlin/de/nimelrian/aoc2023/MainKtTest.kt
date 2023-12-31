package de.nimelrian.aoc2023

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MainKtTest {

    @Test
    fun part1() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...*.*....
            .664.598..
            """.trimIndent()
        val returnValue = solve1(input.lineSequence())
        assertThat(returnValue).isEqualTo(4361)
    }

    @Test
    fun part1NumberAtEndAndStartOfFollowing() {
        val input = """
            ........+6
            4..*......
            ..........
            ......#...
            ...*......
            .....+....
            ..........
            ..........
            ...${'$'}.*....
            ..........
            """.trimIndent()
        val returnValue = solve1(input.lineSequence())
        assertThat(returnValue).isEqualTo(6)
    }

    @Test
    fun part1CommunityTest() {
        val input = """
            12.......*..
            +.........34
            .......-12..
            ..78........
            ..*....60...
            78..........
            .......23...
            ....90*12...
            ............
            2.2......12.
            .*.........*
            1.1.......56
            """.trimIndent()
        val returnValue = solve1(input.lineSequence())
        assertThat(returnValue).isEqualTo(413)
    }

    @Test
    fun part2() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...+.*....
            .664.598..
            """.trimIndent()
        val returnValue = solve2(input.lineSequence())
        assertThat(returnValue).isEqualTo(467835)
    }
}
