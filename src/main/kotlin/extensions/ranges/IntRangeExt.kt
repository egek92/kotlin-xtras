package extensions.ranges

/**
 * Get the middle value in the range.
 * ```
 * (2..4).mid   // 3
 * (2..5).mid   // 3
 * ```
 */
val IntRange.mid: Int get() = start + ((endInclusive - start) / 2)

fun IntRange.isNotEmpty(): Boolean = !isEmpty()

