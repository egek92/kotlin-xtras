package extensions.sequences

/**
 * Return a sequence that repeats its elements infinitely.
 *
 * From Stack Overflow - [Source](https://stackoverflow.com/a/48007581)
 */
fun <T> Sequence<T>.repeating(): Sequence<T> = sequence {
    while (true) {
        yieldAll(this@repeating)
    }
}

/**
 * Return a sequence that repeats its elements [n] times.
 *
 * From Stack Overflow - [Source](https://stackoverflow.com/a/48007581)
 */
fun <T> Sequence<T>.repeated(n: Int): Sequence<T> = sequence {
    repeat(n) {
        yieldAll(this@repeated)
    }
}
