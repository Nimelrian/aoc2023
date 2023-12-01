package de.nimelrian.aoc2023

import org.assertj.core.api.Assertions.assertThat
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
}