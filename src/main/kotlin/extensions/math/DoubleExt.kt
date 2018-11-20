package extensions.math

import java.text.DecimalFormat

/*
 * Extensions for Double.
 */

/**
 * Rounds the Double to [n] decimal places.
 */
fun Double.roundedToNDecimalPlaces(n: Int): Double {
    require(n >= 0) { "Invalid # of decimal places: $n" }
    val formatter = DecimalFormat("#.${"#".repeat(n)}")
    return formatter.format(this).toDouble()
}

/**
 * Format the [Double] as a String with [n] decimal places.
 */
fun Double.formatToNDecimalPlaces(n: Int): String = "%.${n}f".format(this)

/**
 * Coerce the [Double] to be within range [Int.MIN_VALUE]..[Int.MAX_VALUE], and convert the
 * coerced value to an [Int].
 */
fun Double.coerceToIntRange(): Int = coerceIn(Int.MIN_VALUE.toDouble()..Int.MAX_VALUE.toDouble()).toInt()
