package extensions.math

/*
 * Long type extensions.
 */

/**
 * Coerce the [Long] to be within range [Int.MIN_VALUE]..[Int.MAX_VALUE], and convert the
 * coerced value to an [Int].
 */
fun Long.coerceToIntRange(): Int = coerceIn(Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong()).toInt()
