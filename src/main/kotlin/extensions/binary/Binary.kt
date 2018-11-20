package extensions.binary

import kotlin.math.log2

/**
 * Extensions for Bit Manipulation & handling binary numbers.
 */
const val INT_BITS = 32
const val ALL_1_BITMASK_32_BIT = 0.inv()
const val ALL_1_BITMASK_64_BIT = (0L).inv()

/* CHECKING BIT-BASED PROPERTIES */
val Int.isOddNumber: Boolean get() = (this and 1) == 1
val Int.isEvenNumber: Boolean get() = (this and 1) == 0
val Int.isPowerOfTwo: Boolean get() = this != 0 && (this `&` (this - 1)) == 0

/* CHECKING THE VALUE OF BITS */
/**
 * @param k the kth least significant digit, where k = 0 is the least significant bit.
 */
fun Int.hasKthBitSet(k: Int): Boolean {
    require(k in 0..31) { "k must be in range 0..31" }
    return (this and (1 shl k)) != 0
}

/**
 * @param k the kth least significant digit, where k = 0 is the least significant bit.
 */
fun Int.valueOfKthBit(k: Int): Int = if (hasKthBitSet(k)) 1 else 0

/**
 * @return The position of the lowest (rightmost) set bit in this [Int]. 0 indicates the least significant bit.
 */
fun Int.extractRightmost1Bit(): Int {
    val x = this
    if (x == 0) return -1

    return x `&` (-x) // 4 (0100) if lowest set bit is at k = 2
}

/**
 * @return The position of the lowest (rightmost) set bit in this [Int]. 0 indicates the least significant bit.
 */
fun Int.positionOfRightmost1Bit(): Int {
    val x = this
    if (x == 0) return -1

    val valueOfLowestSetBit = extractRightmost1Bit()
    return log2(valueOfLowestSetBit.toDouble()).toInt()
}

/**
 * @return The position of the lowest (rightmost) unset bit in this [Int]. 0 indicates the least significant bit.
 */
fun Int.extractRightmost0Bit(): Int {
    val x = this
    if (x == -1) return -1

    return x.inv() `&` (x + 1)    // ~x & (x + 1)
}

/**
 * @return The position of the lowest (rightmost) set bit in this [Int]. 0 indicates the least significant bit.
 */
fun Int.positionOfRightmost0Bit(): Int {
    val x = this
    if (x == -1) return -1

    val valueOfLowestUnsetBit = extractRightmost0Bit()
    return log2(valueOfLowestUnsetBit.toDouble()).toInt()
}

/**
 * See [Integer.bitCount].
 * @return the number of set (`1`) bits in the [Int].
 */
fun Int.numberOfOneBits(): Int = Integer.bitCount(this)

/**
 * @return the number of unset (`0`) bits in the [Int].
 */
fun Int.numberOfZeroBits(): Int = INT_BITS - Integer.bitCount(this)

/**
 * Shows how to do it manually, instead of using [Integer.bitCount].
 * @return the number of set (`1`) bits in the [Int].
 */
fun Int.numberOfOneBitsAlt(): Int =
    (0 until 32).fold(0) { acc, k ->
        if (this and (0b1 shl k) != 0)
            acc + 1
        else acc
    }

/**
 * Shows how to do it manually, instead of using [Integer.bitCount].
 * @return the number of unset (`0`) bits in the [Int].
 */
fun Int.numberOfZeroBitsAlt(): Int =
    (0 until 32).fold(0) { acc, k ->
        if (this and (0b1 shl k) == 0)
            acc + 1
        else acc
    }

/* SETTING BITS */
/**
 * @return the value produced by setting the [k]th bit of the [Int].
 * @throws IllegalArgumentException if k is invalid
 */
fun Int.withKthBitSet(k: Int): Int {
    require(k in 0..31) { "k must be in range 0..31" }
    return this `|` (1 shl k)
}

/**
 * @return the value produced by unsetting the [k]th bit of the [Int].
 * @throws IllegalArgumentException if k is invalid
 */
fun Int.withKthBitUnset(k: Int): Int {
    require(k in 0..31) { "k must be in range 0..31" }
    return this `&` (1 shl k).inv()
}

/**
 * @return the value produced by flipping the [k]th bit of the [Int].
 * @throws IllegalArgumentException if k is invalid

 */
fun Int.toggleKthBit(k: Int): Int {
    require(k in 0..31) { "k must be in range 0..31" }
    return this `^` (1 shl k)
}

/**
 * @return the [Int] produced by setting the last (least-significant) bit (to 1)
 */
fun Int.withLastBitSet(): Int = this or 1

/**
 * @return the [Int] produced by unsetting the last (least-significant) bit (i.e., making it 0)
 */
fun Int.withLastBitUnset(): Int = this and 1.inv()

/**
 * @return the [Int] produced by unsetting the least-significant set bit.
 */
fun Int.unsetRightmost1Bit(): Int = this and (this - 1)

fun Int.setRightmost0Bit(): Int {
    if (this == -1)     // no 0 bits
        return -1

    val rightmost0Bit = extractRightmost0Bit()
    return this xor rightmost0Bit
}

/**
 * See [Integer.reverse]
 */
fun Int.reverseBits(): Int = Integer.reverse(this)

/**
 * See [Integer.reverseBytes]
 */
fun Int.reverseBytes(): Int = Integer.reverseBytes(this)

/**
 * @return the value produced by swapping the bits at positions [i] and [j]
 */
fun Int.swapBits(i: Int, j: Int): Int {
    val iBit = (this shr i) `&` 1
    val jBit = (this shr j) `&` 1

    if (iBit `^` jBit == 0)     // both 0 or both 1
        return this

    return this `^` ((1 shl i) `|` (1 shl j))
}

/* BINARY <-> INT HELPER FUNCTIONS */
/**
 * Convert an [Int] to binary string:
 *
 * The result is padded to the specified number of bits, or 32 by default.
 *
 * ```
 * 67.toBinaryString()    =>  00000000000000000000000001000011
 * (-67).toBinaryString() =>  11111111111111111111111110111101
 * ```
 *
 * @param bits The number of bits in the binary string representation (default: 32)
 */
fun Int.toBinaryString(bits: Int = 32): String = when {
    this >= 0 -> Integer.toBinaryString(this).padStart(bits, '0')
    else -> Integer.toBinaryString(this)
}

/**
 * Convert a [Long] to binary string:
 *
 * The result is padded to the specified number of bits, or 64 by default.
 *
 * ```
 * 67.toBinaryString()    =>  00000000000000000000000001000011
 * (-67).toBinaryString() =>  11111111111111111111111110111101
 * ```
 *
 * @param bits The number of bits in the binary string representation (default: 32)
 */
fun Long.toBinaryString(bits: Int = 64): String = when {
    this >= 0L -> java.lang.Long.toBinaryString(this).padStart(bits, '0')
    else -> java.lang.Long.toBinaryString(this)
}

/**
 * Converts a _non-negative_ binary string to an integer.
 * @exception  NumberFormatException If the binary string represents a negative number or can't be parsed
 */
fun String.parseNonNegativeBinaryString(): Int = Integer.parseInt(this, 2)
