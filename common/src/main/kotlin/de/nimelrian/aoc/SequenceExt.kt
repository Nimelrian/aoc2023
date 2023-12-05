package de.nimelrian.aoc


/**
 * Creates a sequence of chunks determined by the given predicate. Chunks contain all elements between (and including)
 * the last matched item and the (excluding) next matched item.
 * Example: The sequence 1, 2, 3, 4, 5, 6, 7, 8 will be split into 3 chunks if the predicate `it % 3 == 0` is used:
 *
 * * Chunk 1 - 1, 2
 * * Chunk 2 - 3, 4, 5
 * * Chunk 3 - 6, 7, 8
 *
 */
fun <T> Sequence<T>.chunkByPredicate(skipMatchingItem: Boolean = false, predicate: (T) -> Boolean): Sequence<List<T>> {
    return sequence {
        val currentChunk = mutableListOf<T>()
        for (item in this@chunkByPredicate) {
            if (predicate(item)) {
                // Yield the chunked elements up to this item
                yield(currentChunk.toList())
                currentChunk.clear()
                if (!skipMatchingItem) {
                    currentChunk += item
                }
            } else {
                currentChunk += item
            }
        }

        // Last chunk
        yield(currentChunk)
    }
}

inline fun <T> Sequence<T>.takeWhileInclusive(crossinline predicate: (T) -> Boolean) = sequence {
    for (item in this@takeWhileInclusive) {
        yield(item)
        if (!predicate(item)) break
    }
}

fun <T> Sequence<T>.takeHead(): Pair<T, Sequence<T>> {
    val iter = this.iterator()
    val head = iter.next()
    return Pair(head, iter.asSequence())
}
