package extensions.math

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.math.BigInteger
import kotlin.system.measureTimeMillis

class MathExtTest {

    private val primesUnder200 = listOf(
        2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
        67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179,
        181, 191, 193, 197, 199
    )

    @Test
    fun isPrime() {
        (0..200).forEach { i ->
            if (primesUnder200.contains(i)) {
                assertTrue(i.isPrime())
            } else {
                assertFalse(i.isPrime())
            }
        }
    }

    @Test
    fun testGCD() {
        assertEquals(1, gcd(1, 0))
        assertEquals(1, gcd(0, 1))
        assertEquals(21, gcd(0, 21))
        assertEquals(1, gcd(1, 1))
        assertEquals(1, gcd(1, 7))
        assertEquals(7, gcd(7, 7))
        assertEquals(1, gcd(3, 4))
        assertEquals(1, gcd(3, 8))
        assertEquals(3, gcd(3, 12))
        assertEquals(6, gcd(6, 12))
        assertEquals(4, gcd(8, 12))
        assertEquals(3, gcd(9, 12))
        assertEquals(3, gcd(15, 12))
        assertEquals(6, gcd(18, 12))
        assertEquals(1, gcd(19, 12))
        assertEquals(6, gcd(24, 18))
        assertEquals(6, gcd(30, 18))
        assertEquals(21, gcd(1071, 462))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `testGCD - zero zero`() {
        gcd(0, 0)
    }

    @Test
    fun medianTest() {
        assertEquals(2, median(1, 2, 3))
        assertEquals(-2, median(-1, -2, -3))
        assertEquals(3, median(1, 3, 3))
        assertEquals(3, median(3, 3, 3))
    }

    @Test
    fun sumOfFirstNPositiveIntegers() {
        assertEquals(1, sumOfFirstNPositiveIntegers(1))
        assertEquals(3, sumOfFirstNPositiveIntegers(2))
        assertEquals(15, sumOfFirstNPositiveIntegers(5))
        assertEquals(55, sumOfFirstNPositiveIntegers(10))
        assertEquals(5050, sumOfFirstNPositiveIntegers(100))
    }

    @Test
    fun factorial() {
        assertEquals(BigInteger.ONE, 0.factorial())
        assertEquals(BigInteger.ONE, 1.factorial())
        assertEquals(BigInteger.valueOf(2L), 2.factorial())
        assertEquals(BigInteger.valueOf(6L), 3.factorial())
        assertEquals(BigInteger.valueOf(24L), 4.factorial())
        assertEquals(BigInteger.valueOf(120L), 5.factorial())
        assertEquals(BigInteger.valueOf(720L), 6.factorial())
        assertEquals(BigInteger.valueOf(5040L), 7.factorial())
        assertEquals(BigInteger.valueOf(3628800L), 10.factorial())
    }

    @Test
    fun powerSet() {
        val times = arrayListOf<Long>()
        repeat(10) {
            val ms = measureTimeMillis {
                assertEquals(setOf(emptySet<Int>()), emptySet<Int>().powerSet())
                assertEquals(setOf(setOf(), setOf(1)), setOf(1).powerSet())
                assertEquals(setOf(setOf(), setOf(1), setOf(2), setOf(1, 2)), setOf(1, 2).powerSet())
                assertEquals(
                    setOf(setOf(), setOf(1), setOf(2), setOf(3), setOf(1, 2), setOf(1, 3), setOf(2, 3), setOf(1, 2, 3)),
                    setOf(1, 2, 3).powerSet()
                )
                assertEquals(
                    setOf(
                        setOf(), setOf(1), setOf(2), setOf(3), setOf(4),
                        setOf(1, 2), setOf(1, 3), setOf(1, 4), setOf(2, 3), setOf(2, 4), setOf(3, 4),
                        setOf(1, 2, 3), setOf(1, 2, 4), setOf(1, 3, 4), setOf(2, 3, 4),
                        setOf(1, 2, 3, 4)
                    ),
                    listOf(1, 2, 3, 4).powerSet()
                )
            }
            times += ms
        }
        val adjustedTimes = times.drop(3)   // 1st one or two affected by test startup
        println("adjustedTimes for powerSet: $adjustedTimes")
        println("average run: ${adjustedTimes.average().roundedToNDecimalPlaces(2)}")
    }

    @Test
    fun combinations() {
        val times = arrayListOf<Long>()
        repeat(15) {
            val ms = measureTimeMillis {
                assertEquals(
                    setOf(emptySet<Int>()),
                    setOf(1, 2, 3).combinations(k = 0)
                )

                assertEquals(
                    setOf(setOf(1), setOf(2), setOf(3)),
                    setOf(1, 2, 3).combinations(k = 1)
                )

                assertEquals(
                    setOf(setOf(1, 2), setOf(1, 3), setOf(2, 3)),
                    setOf(1, 2, 3).combinations(k = 2)
                )

                assertEquals(
                    setOf(setOf(1, 2, 3)),
                    setOf(1, 2, 3).combinations(k = 3)
                )

                assertEquals(
                    setOf(
                        setOf(1, 2, 3, 4),
                        setOf(1, 2, 3, 5),
                        setOf(1, 2, 3, 6),
                        setOf(1, 2, 4, 5),
                        setOf(1, 2, 4, 6),
                        setOf(1, 2, 5, 6),
                        setOf(1, 3, 4, 5),
                        setOf(1, 3, 4, 6),
                        setOf(1, 3, 5, 6),
                        setOf(1, 4, 5, 6),
                        setOf(2, 3, 4, 5),
                        setOf(2, 3, 4, 6),
                        setOf(2, 3, 5, 6),
                        setOf(2, 4, 5, 6),
                        setOf(3, 4, 5, 6)
                    ),
                    setOf(1, 2, 3, 4, 5, 6).combinations(k = 4)
                )

                assertEquals(252, (1..10).toSet().combinations(k = 5).size)

            }
            times += ms
        }
        val adjustedTimes = times.drop(3)
        println("adjustedTimes for combinations (power set): $adjustedTimes")
        println("average run: ${adjustedTimes.average().roundedToNDecimalPlaces(2)}")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `combinations - negative k`() {
        setOf(1, 2, 3).combinations(k = -5)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `combinations - k greater than size`() {
        setOf(1, 2, 3).combinations(k = 5)
    }


    @Test
    fun combinations2() {
        val times = arrayListOf<Long>()
        repeat(15) {
            val ms = measureTimeMillis {
                assertEquals(
                    setOf(emptySet<Int>()),
                    setOf(1, 2, 3).combinations2(k = 0)
                )

                assertEquals(
                    setOf(setOf(1), setOf(2), setOf(3)),
                    setOf(1, 2, 3).combinations2(k = 1)
                )

                assertEquals(
                    setOf(setOf(1, 2), setOf(1, 3), setOf(2, 3)),
                    setOf(1, 2, 3).combinations2(k = 2)
                )

                assertEquals(
                    setOf(setOf(1, 2, 3)),
                    setOf(1, 2, 3).combinations2(k = 3)
                )

                assertEquals(
                    setOf(
                        setOf(1, 2, 3, 4),
                        setOf(1, 2, 3, 5),
                        setOf(1, 2, 3, 6),
                        setOf(1, 2, 4, 5),
                        setOf(1, 2, 4, 6),
                        setOf(1, 2, 5, 6),
                        setOf(1, 3, 4, 5),
                        setOf(1, 3, 4, 6),
                        setOf(1, 3, 5, 6),
                        setOf(1, 4, 5, 6),
                        setOf(2, 3, 4, 5),
                        setOf(2, 3, 4, 6),
                        setOf(2, 3, 5, 6),
                        setOf(2, 4, 5, 6),
                        setOf(3, 4, 5, 6)
                    ),
                    setOf(1, 2, 3, 4, 5, 6).combinations2(k = 4)
                )

                assertEquals(252, (1..10).toSet().combinations2(k = 5).size)

            }
            times += ms
        }
        val adjustedTimes = times.drop(3)
        println("adjustedTimes for combinations2 (power set): $adjustedTimes")
        println("average run: ${adjustedTimes.average().roundedToNDecimalPlaces(2)}")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `combinations2 - negative k`() {
        setOf(1, 2, 3).combinations2(k = -5)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `combinations2 - k greater than size`() {
        setOf(1, 2, 3).combinations2(k = 5)
    }

    @Test
    fun permutations() {
        val times = arrayListOf<Long>()
        repeat(10) {
            val ms = measureTimeMillis {
                assertEquals(
                    setOf(emptyList<Int>()),
                    emptyList<Int>().permutations()
                )

                assertEquals(
                    setOf(
                        listOf(1, 2, 3),
                        listOf(1, 3, 2),
                        listOf(2, 1, 3),
                        listOf(2, 3, 1),
                        listOf(3, 1, 2),
                        listOf(3, 2, 1)
                    ),
                    listOf(1, 2, 3).permutations()
                )

                assertEquals(
                    setOf(
                        listOf(1, 1, 2),
                        listOf(1, 2, 1),
                        listOf(2, 1, 1)
                    ),
                    listOf(1, 1, 2).permutations()
                )

                assertEquals(
                    setOf(
                        listOf(0, 1, 2, 3, 4), listOf(0, 1, 2, 4, 3), listOf(0, 1, 3, 2, 4), listOf(0, 1, 3, 4, 2),
                        listOf(0, 1, 4, 2, 3), listOf(0, 1, 4, 3, 2), listOf(0, 2, 1, 3, 4), listOf(0, 2, 1, 4, 3),
                        listOf(0, 2, 3, 1, 4), listOf(0, 2, 3, 4, 1), listOf(0, 2, 4, 1, 3), listOf(0, 2, 4, 3, 1),
                        listOf(0, 3, 1, 2, 4), listOf(0, 3, 1, 4, 2), listOf(0, 3, 2, 1, 4), listOf(0, 3, 2, 4, 1),
                        listOf(0, 3, 4, 1, 2), listOf(0, 3, 4, 2, 1), listOf(0, 4, 1, 2, 3), listOf(0, 4, 1, 3, 2),
                        listOf(0, 4, 2, 1, 3), listOf(0, 4, 2, 3, 1), listOf(0, 4, 3, 1, 2), listOf(0, 4, 3, 2, 1),
                        listOf(1, 0, 2, 3, 4), listOf(1, 0, 2, 4, 3), listOf(1, 0, 3, 2, 4), listOf(1, 0, 3, 4, 2),
                        listOf(1, 0, 4, 2, 3), listOf(1, 0, 4, 3, 2), listOf(1, 2, 0, 3, 4), listOf(1, 2, 0, 4, 3),
                        listOf(1, 2, 3, 0, 4), listOf(1, 2, 3, 4, 0), listOf(1, 2, 4, 0, 3), listOf(1, 2, 4, 3, 0),
                        listOf(1, 3, 0, 2, 4), listOf(1, 3, 0, 4, 2), listOf(1, 3, 2, 0, 4), listOf(1, 3, 2, 4, 0),
                        listOf(1, 3, 4, 0, 2), listOf(1, 3, 4, 2, 0), listOf(1, 4, 0, 2, 3), listOf(1, 4, 0, 3, 2),
                        listOf(1, 4, 2, 0, 3), listOf(1, 4, 2, 3, 0), listOf(1, 4, 3, 0, 2), listOf(1, 4, 3, 2, 0),
                        listOf(2, 0, 1, 3, 4), listOf(2, 0, 1, 4, 3), listOf(2, 0, 3, 1, 4), listOf(2, 0, 3, 4, 1),
                        listOf(2, 0, 4, 1, 3), listOf(2, 0, 4, 3, 1), listOf(2, 1, 0, 3, 4), listOf(2, 1, 0, 4, 3),
                        listOf(2, 1, 3, 0, 4), listOf(2, 1, 3, 4, 0), listOf(2, 1, 4, 0, 3), listOf(2, 1, 4, 3, 0),
                        listOf(2, 3, 0, 1, 4), listOf(2, 3, 0, 4, 1), listOf(2, 3, 1, 0, 4), listOf(2, 3, 1, 4, 0),
                        listOf(2, 3, 4, 0, 1), listOf(2, 3, 4, 1, 0), listOf(2, 4, 0, 1, 3), listOf(2, 4, 0, 3, 1),
                        listOf(2, 4, 1, 0, 3), listOf(2, 4, 1, 3, 0), listOf(2, 4, 3, 0, 1), listOf(2, 4, 3, 1, 0),
                        listOf(3, 0, 1, 2, 4), listOf(3, 0, 1, 4, 2), listOf(3, 0, 2, 1, 4), listOf(3, 0, 2, 4, 1),
                        listOf(3, 0, 4, 1, 2), listOf(3, 0, 4, 2, 1), listOf(3, 1, 0, 2, 4), listOf(3, 1, 0, 4, 2),
                        listOf(3, 1, 2, 0, 4), listOf(3, 1, 2, 4, 0), listOf(3, 1, 4, 0, 2), listOf(3, 1, 4, 2, 0),
                        listOf(3, 2, 0, 1, 4), listOf(3, 2, 0, 4, 1), listOf(3, 2, 1, 0, 4), listOf(3, 2, 1, 4, 0),
                        listOf(3, 2, 4, 0, 1), listOf(3, 2, 4, 1, 0), listOf(3, 4, 0, 1, 2), listOf(3, 4, 0, 2, 1),
                        listOf(3, 4, 1, 0, 2), listOf(3, 4, 1, 2, 0), listOf(3, 4, 2, 0, 1), listOf(3, 4, 2, 1, 0),
                        listOf(4, 0, 1, 2, 3), listOf(4, 0, 1, 3, 2), listOf(4, 0, 2, 1, 3), listOf(4, 0, 2, 3, 1),
                        listOf(4, 0, 3, 1, 2), listOf(4, 0, 3, 2, 1), listOf(4, 1, 0, 2, 3), listOf(4, 1, 0, 3, 2),
                        listOf(4, 1, 2, 0, 3), listOf(4, 1, 2, 3, 0), listOf(4, 1, 3, 0, 2), listOf(4, 1, 3, 2, 0),
                        listOf(4, 2, 0, 1, 3), listOf(4, 2, 0, 3, 1), listOf(4, 2, 1, 0, 3), listOf(4, 2, 1, 3, 0),
                        listOf(4, 2, 3, 0, 1), listOf(4, 2, 3, 1, 0), listOf(4, 3, 0, 1, 2), listOf(4, 3, 0, 2, 1),
                        listOf(4, 3, 1, 0, 2), listOf(4, 3, 1, 2, 0), listOf(4, 3, 2, 0, 1), listOf(4, 3, 2, 1, 0)
                    ),
                    listOf(0, 1, 2, 3, 4).permutations()
                )

                listOf(0, 1, 2, 3, 4, 5, 6, 7).permutations()
            }
            times += ms
        }
        val adjustedTimes = times.drop(3)   // 1st one or two affected by test startup
        println("adjustedTimes for permutations: $adjustedTimes")
        println("average run: ${adjustedTimes.average().roundedToNDecimalPlaces(2)}")
    }

    @Test
    fun choose() {
        assertEquals(BigInteger.ONE, choose(0, 0))

        (0..10).forEach { i ->
            assertEquals(BigInteger.ONE, choose(i, 0))       // C(n, 0)
            assertEquals(BigInteger.ONE, choose(i, i))       // C(n, n)
        }

        assertEquals(BigInteger.valueOf(7L), choose(7, 1))   // C(n, 1)
        assertEquals(BigInteger.valueOf(7L), choose(7, 6))   // C(n, n-1)
        assertEquals(BigInteger.valueOf(21L), choose(7, 2))
        assertEquals(BigInteger.valueOf(21L), choose(7, 5))
        assertEquals(BigInteger.valueOf(35L), choose(7, 3))
        assertEquals(BigInteger.valueOf(35L), choose(7, 4))

        assertEquals(BigInteger.valueOf(10272278170L), choose(50, 10))
        assertEquals(BigInteger.valueOf(10272278170L), choose(50, 40))
        assertEquals(BigInteger.valueOf(121548660036300L), choose(50, 24))
        assertEquals(BigInteger.valueOf(126410606437752L), choose(50, 25))
        assertEquals(BigInteger.valueOf(121548660036300L), choose(50, 26))

        assertEquals(BigInteger.valueOf(75287520L), choose(100, 5))
        assertEquals(BigInteger("100891344545564193334812497256"), choose(100, 50))
    }
}