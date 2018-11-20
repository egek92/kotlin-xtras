package extensions.binary

/*
 * Bitwise Operators:
 * `&` for & (`Int.and(other: Int)`)
 * `|` for | (`Int.or(other: Int)`)
 * `^` for ^ (`Int.xor(other: Int)`)
 *
 * !a for ~a (`Int.inv()`)
 * shiftLeft for (`infix fun shl(bitCount: Int): Int`)
 * shiftRight for (`infix fun shr(bitCount: Int): Int`)
 * unsignedShiftRight for (`infix fun ushr(bitCount: Int): Int`)
 *
 * Note: function names cannot contain `<` or `>`
 *
 * This file also implements [indexed access operator](http://tinyurl.com/y7un2skt) for `Int`,
 * enabling the `k`th bit of n to be accessed by n[k].
 */

/**
 * Symbolic form of [Int.and].
 * ```
 * 1 `&` 1 == 1
 * ```
 */
infix fun Int.`&`(other: Int): Int = this and other

/**
 * Symbolic form of [Int.or].
 * ```
 * 1 `|` 0 == 1
 * ```
 */
infix fun Int.`|`(other: Int): Int = this or other

/**
 * Symbolic form of [Int.xor].
 * ```
 * 1 `^` 1 == 0
 * ```
 */
infix fun Int.`^`(other: Int): Int = this xor other

/**
 * Bitwise complement - inverts every bit in the [Int].
 *
 * Implements [Int.not] so that the `!` operator can be used on an [Int].
 * `!a` is translated to `a.not()` in Kotlin (see [Prefix Operators](http://tinyurl.com/y7un2skt))
 *
 * With this function, `!a` in Kotlin is equivalent to `~a` in Java.
 *
 * ```
 * !10 == -11
 * ```
 */
operator fun Int.not() = this.inv()

/**
 * Calls [Int.shl].
 * ```
 * 1 shiftLeft 2 == 4
 * ```
 */
infix fun Int.shiftLeft(bitCount: Int): Int = this shl bitCount

/**
 * Calls [Int.shr].
 * ```
 * 8 shiftRight 2 == 2
 * ```
 */
infix fun Int.shiftRight(bitCount: Int): Int = this shr bitCount

/**
 * Calls [Int.ushr].
 * ```
 * -8 unsignedShiftRight 3 == 536870911
 * ```
 */
infix fun Int.unsignedShiftRight(bitCount: Int): Int = this ushr bitCount

/**
 * Implements [indexed access operator](http://tinyurl.com/y7un2skt) for
 * [Int], so that the `k`th bit of an [Int] can be accessed like this:
 * ```
 * val x = 7           // 0b0111
 * val bit0 = x[0]     // 1
 * val bit3 = x[3]     // 0
 * ```
 */
operator fun Int.get(k: Int): Int = valueOfKthBit(k)
