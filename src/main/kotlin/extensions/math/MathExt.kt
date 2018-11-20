package extensions.math

import java.lang.IllegalArgumentException
import java.math.BigInteger
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Mathematical functions
 * Permutation/Combinations taken from: https://github.com/MarcinMoskala/KotlinDiscreteMathToolkit
 */
const val EPSILON = 1e-10

/**
 * Returns the median (middle value) of three [Int].
 */
fun median(a: Int, b: Int, c: Int) = max(min(a, b), min(max(a, b), c))

/**
 * **Time**: `O(n)`
 *
 * **Space**: `O(1)`
 */
fun Int.isPrime(): Boolean {
    if (this <= 1)
        return false

    val sqrt = sqrt(toDouble()).toInt()
    return (2..sqrt).all { this % it != 0 }
}

/**
 * Greatest Common Divisor.
 * If k is a non-zero integer, then k divides zero (k|0).
 * The largest common divisor of k and zero is k.
 * So gcd(k, 0) = gcd(0, k) = k.
 * gcd(0, 0) is undefined.
 *
 * http://mfleck.cs.illinois.edu/building-blocks/version-1.0/number-theory.pdf
 */
fun gcd(a: Int, b: Int): Int {
    require(a != 0 || b != 0) { "gcd(0, 0) is undefined" }
    return calculateGCD(a, b)
}

tailrec fun calculateGCD(a: Int, b: Int): Int = if (b == 0) a else calculateGCD(b, a % b)

/**
 * Returns the sum of the first n positive integers:
 * Sum of `1..n`: `1 + 2 + 3 + ... + n-1 + n`
 */
fun sumOfFirstNPositiveIntegers(n: Int) = ((n * (n + 1)) / 2)

/**
 * Calculates the factorial, `n!`.
 */
fun Int.factorial(): BigInteger {
    require(this >= 0) { "Must be > 0" }
    return when (this) {
        in 0..1 -> BigInteger.ONE
        else -> {
            var result = BigInteger.ONE
            var i = BigInteger.valueOf(this.toLong())
            while (i > BigInteger.ONE) {
                result = i.multiply(result)
                i = i.minus(BigInteger.ONE)
            }
            return result
        }
    }
}

/* Discrete Math/Combinatorics */
/**
 * Calculate the number of k-combinations: C(n, k) (aka nCk, nCr, 'n choose k').
 */
fun choose(n: Int, k: Int): BigInteger {
    require(n >= 0 && k >= 0) { "C(n, k) for C($n, $k): n and k must be > 0" }
    require(n >= k) { "C(n, k) for C($n, $k): n must be >= k" }

    if (n == k || k == 0) {
        return BigInteger.ONE
    } else if (k == 1) {
        return BigInteger.valueOf(n.toLong())
    }

    val numerator = n.factorial()
    val denominator = (k.factorial()).multiply((n - k).factorial())
    return numerator.divide(denominator)
}

/**
 * Generates all combinations of size [k].
 */
fun <T> Set<T>.combinations(k: Int): Set<Set<T>> = when {
    k !in 0..size -> throw IllegalArgumentException("k must be in 0 <= k <= n, but was $k")
    k == 0 -> setOf(setOf())
    k == size -> setOf(this)
    else -> powerSet()
        .asSequence()
        .filter { it.size == k }
        .toSet()
}

fun <T> Set<T>.combinations2(k: Int): Set<Set<T>> = toList().combinations(k)
    .map { it.toSet() }
    .toSet()

/**
 * Generates all combinations of size [k]. This is just a different implementation
 * of the one above.
 * TODO - time & delete slower of the 2
 */
fun <T> List<T>.combinations(k: Int): List<List<T>> = when {
    isEmpty() || size == k -> listOf(this)
    k !in 0..size -> throw IllegalArgumentException("k must be in 0..$size, but was $k (input: $this)")
    k == 0 -> listOf(emptyList())
    k == 1 -> map { listOf(it) }
    else -> {
        val combinations: MutableList<List<T>> = ArrayList()
        for (i in 0..(size - k)) {
            val tailCombinations = drop(i + 1).combinations(k - 1)
            combinations += tailCombinations.map { listOf(this[i]) + it }
        }
        combinations
    }
}

/**
 * Generates the Power Set of the [Collection].
 *
 * **Time**: `O(2^n)`
 *
 * **Space**: `O(2^n)`
 */
fun <T> Collection<T>.powerSet(): Set<Set<T>> {
    val powerSet: MutableSet<Set<T>> = hashSetOf(emptySet())    // contains only the null set {{}}
    for (x in this)
        powerSet += powerSet.map { it + x }
    return powerSet
}

/**
 * Generates all permutations of the list.
 */
fun <T> List<T>.permutations(): Set<List<T>> = when (size) {
    0 -> setOf(emptyList())
    1 -> setOf(listOf(first()))
    2 -> setOf(this, listOf(this[1], this[0]))
    else -> {
        val permutations: MutableSet<List<T>> = hashSetOf()
        for (i in indices) {
            val otherElements = filterIndexed { index, _ -> index != i }
            val newPermutations = otherElements.permutations().map {
                it + this[i]
            }
            permutations.addAll(newPermutations)
        }
        permutations
    }
}