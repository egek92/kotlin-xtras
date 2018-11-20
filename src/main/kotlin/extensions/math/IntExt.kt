package extensions.math

import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.log10
import kotlin.math.pow

/*
 * Extensions for class: Int.
 */

val Int.isEven: Boolean get() = this % 2 == 0
val Int.isOdd: Boolean get() = this % 2 != 0

/**
 * Returns true if the receiver evenly divides [n] (`this|n`).
 * Equivalently, `n` is a multiple of the receiver.
 */
fun Int.divides(n: Int): Boolean = n % this == 0

/**
 * Returns true if the receiver is a multiple of `n`
 */
fun Int.isMultipleOf(n: Int): Boolean = this % n == 0

/**
 * Applies Java's [Character.forDigit].
 */
fun Int.charForDigit(): Char = Character.forDigit(this, 10)

/**
 * Returns the digits in the [Int] receiver.
 * e.g., `497.digits() --> [4, 9, 7]`
 *
 * Requires that the integer be >= 0
 *
 * Time/Space: `O(n)/O(n)` where `n` is the # of digits.
 */
fun Int.digits(): List<Int> {
    require(this >= 0) { "Must be > 0" }
    if (this == 0) return listOf(0)

    var remainder = absoluteValue
    val digits = mutableListOf<Int>()

    while (remainder > 0) {
        val rightmostDigit = remainder % 10
        digits.add(rightmostDigit)
        remainder /= 10
    }

    return digits.reversed()
}

/**
 * Return the integer produced by reversing all digits in the integer,
 * or `null` if the reversed integer does not fit into 32-bit Int range
 * (i.e., causes overflow)
 */
fun Int.withDigitsReversed(): Int? {
    if (this < 0) {
        val absValue = abs(this)
        if (absValue < 0) return null   // Edge case: Int.MIN_VALUE

        val reversedAbsVal = absValue.withDigitsReversed() ?: return null
        return reversedAbsVal * -1
    }

    var x: Long = this.toLong()
    var r: Long = 0

    while (x > 0L) {
        r = (r * 10) + (x % 10)
        x /= 10

        // Overflow occurred
        if (r > Int.MAX_VALUE) return null
    }

    return r.toInt()
}


/**
 * Return the number of digits in an integer.
 *
 * Ex. `4321.numberOfDigits() -> 4`
 *
 * Time/Space: `O(1)/O(1)`
 */
fun Int.numberOfDigits(): Int = when (this) {
    0 -> 1                  // log(0.0) -> -inf
    Int.MIN_VALUE -> 10     // abs(Int.MIN_VALUE) -> Int overflow
    else -> (log10(absoluteValue.toDouble()) + 1.0).toInt()
}

fun Int.leastSignificantDigit(): Int = this % 10
fun Int.mostSignificantDigit(): Int = this / 10.0.pow(numberOfDigits() - 1).toInt()

// note: 03 will be returned as 3
fun Int.nLeastSignificantDigits(n: Int): Int {
    require(n >= 1) { "invalid argument n: $n. Must be >= 1" }
    return this % 10.0.pow(n).toInt()
}

fun Int.nMostSignificantDigits(n: Int): Int {
    val totalDigits = numberOfDigits()
    require(n in 1..totalDigits) { "invalid argument n: $n. Must be in range [0, total number of digits]" }
    return this / 10.0.pow(totalDigits - n).toInt()
}
