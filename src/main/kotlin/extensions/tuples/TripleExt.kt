package extensions.tuples

/**
 * [Triple] extensions
 */

fun Triple<Boolean, Boolean, Boolean>.toBooleanArray(): BooleanArray = booleanArrayOf(first, second, third)
fun Triple<Byte, Byte, Byte>.toByteArray(): ByteArray = byteArrayOf(first, second, third)
fun Triple<Char, Char, Char>.toCharArray(): CharArray = charArrayOf(first, second, third)
fun Triple<Double, Double, Double>.toDoubleArray(): DoubleArray = doubleArrayOf(first, second, third)
fun Triple<Float, Float, Float>.toFloatArray(): FloatArray = floatArrayOf(first, second, third)
fun Triple<Int, Int, Int>.toIntArray(): IntArray = intArrayOf(first, second, third)
fun Triple<Long, Long, Long>.toLongArray(): LongArray = longArrayOf(first, second, third)
fun Triple<Short, Short, Short>.toShortArray(): ShortArray = shortArrayOf(first, second, third)

fun <T : Comparable<T>> Triple<T, T, T>.max() = maxOf(first, second, third)
fun <T : Comparable<T>> Triple<T, T, T>.min() = minOf(first, second, third)
fun <T : Comparable<T>> Triple<T, T, T>.median(): T = maxOf(minOf(first, second), minOf(maxOf(first, second), third))

// contains
fun <T> Triple<T, T, T>.contains(value: T) = first == value || second == value || third == value