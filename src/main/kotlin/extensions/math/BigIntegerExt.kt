package extensions.math

import java.math.BigInteger

/**
 * Coerce the [BigInteger] to be within range [Int.MIN_VALUE]..[Int.MAX_VALUE], and convert the
 * coerced value to an [Int].
 */
fun BigInteger.coerceToIntRange(): Int = coerceIn(Int.MIN_VALUE.toBigInteger()..Int.MAX_VALUE.toBigInteger()).toInt()
