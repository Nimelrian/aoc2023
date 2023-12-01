package de.nimelrian.aoc2023

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MainKtTest {

    @Test
    fun part1() {
        val input = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent()
        val returnValue = solve1(input.lineSequence())
        assertThat(returnValue).isEqualTo(142)
    }

    @Test
    fun part1RegressionTest() {
        val input = (javaClass.getResourceAsStream("/input")!!)
            .bufferedReader()
            .lineSequence()
        val returnValue = solve1(input)
        assertThat(returnValue).isEqualTo(55130)
    }

    @Nested
    inner class Part2 {
        @Test
        fun part2() {
            val input = """
                two1nine
                eightwothree
                abcone2threexyz
                xtwone3four
                4nineeightseven2
                zoneight234
                7pqrstsixteen
            """.trimIndent()
            val returnValue = solve2(input.lineSequence())
            assertThat(returnValue).isEqualTo(281)
        }
    }
}
