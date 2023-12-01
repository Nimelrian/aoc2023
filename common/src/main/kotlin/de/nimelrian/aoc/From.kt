package de.nimelrian.aoc

interface From<T, Self> {
    fun from(source: T): Self
    fun T.into(): Self = from(this)
}
