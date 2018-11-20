package extensions.tuples

/**
 * [Pair] extensions
 */

/* To Array */
fun Pair<Boolean, Boolean>.toBooleanArray(): BooleanArray = booleanArrayOf(first, second)
fun Pair<Byte, Byte>.toByteArray(): ByteArray = byteArrayOf(first, second)
fun Pair<Char, Char>.toCharArray(): CharArray = charArrayOf(first, second)
fun Pair<Double, Double>.toDoubleArray(): DoubleArray = doubleArrayOf(first, second)
fun Pair<Float, Float>.toFloatArray(): FloatArray = floatArrayOf(first, second)
fun Pair<Int, Int>.toIntArray(): IntArray = intArrayOf(first, second)
fun Pair<Long, Long>.toLongArray(): LongArray = longArrayOf(first, second)
fun Pair<Short, Short>.toShortArray(): ShortArray = shortArrayOf(first, second)

fun <T : Comparable<T>> Pair<T, T>.max() = maxOf(first, second)
fun <T : Comparable<T>> Pair<T, T>.min() = minOf(first, second)

/* Null checking */
fun <T : Any?> Pair<T?, T?>.bothNull(): Boolean = first == null && second == null
fun <T : Any?> Pair<T?, T?>.eitherNull(): Boolean = first == null || second == null
fun <T : Any?> Pair<T?, T?>.neitherNull(): Boolean = first != null && second != null

// Source: http://tinyurl.com/y6wn9b2k
infix fun <A, B, C> Pair<A, B>.and(that: C): Triple<A, B, C> = Triple(this.first, this.second, that)

// Flip the pair's order
fun <T, U> Pair<T, U>.reversed() = Pair(second, first)
inline fun <T> Pair<T, T>.reversedIf(predicate: (Pair<T, T>) -> Boolean) =
    if (predicate(this)) Pair(second, first) else this

fun <T> Pair<T, T>.contains(value: T) = first == value || second == value


/* Pair<String, String> */
/**
 * @return The shorter of 2 strings (if equal length, the first of the two is returned)
 */
fun Pair<String, String>.shorter(): String = if (first.length <= second.length) first else second

/**
 * @return The longer of 2 strings (if equal length, the second of the two is returned)
 */
fun Pair<String, String>.longer(): String = if (first.length > second.length) first else second

/**
 * @return A pair containing the shorter of the 2 strings and the longer of the 2 strings.
 */
fun Pair<String, String>.shorterAndLonger(): Pair<String, String> = Pair(shorter(), longer())
