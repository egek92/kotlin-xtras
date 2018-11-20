package extensions.comparables

/**
 * Return the object with the largest value of the specified objects.
 */
fun <T: Comparable<T>> maxOf(vararg values: T): T {
    require(values.isNotEmpty()) { "Cannot take max of 0 values" }
    return values.max()!!
}

/**
 * Return the object with the smallest value of the specified objects.
 */
fun <T: Comparable<T>> minOf(vararg values: T): T {
    require(values.isNotEmpty()) { "Cannot take min of 0 values" }
    return values.min()!!
}
