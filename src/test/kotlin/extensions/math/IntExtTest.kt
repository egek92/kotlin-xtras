package extensions.math

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class IntExtTest {

    @Test
    fun isEven() {
        setOf(-10, -4, 0, 2, 4, 6, 20, 100, 128).forEach { assertTrue(it.isEven) }
        setOf(-9, -3, -1, 1, 3, 7, 21, 101, 127).forEach { assertFalse(it.isEven) }
    }

    @Test
    fun isOdd() {
        setOf(-9, -3, -1, 1, 3, 7, 21, 101, 127).forEach { assertTrue(it.isOdd) }
        setOf(-10, -4, 0, 2, 4, 6, 20, 100, 128).forEach { assertFalse(it.isOdd) }
    }

    @Test
    fun divides() {
        assertTrue(8.divides(0))
        assertTrue(1.divides(8))
        assertTrue(8.divides(8))
        assertTrue(8.divides(24))
    }

    @Test(expected = ArithmeticException::class)
    fun `divides - 0 divides 0`() {
        0.divides(0)
    }

    @Test
    fun isMultipleOf() {
        assertTrue(0.isMultipleOf(8))
        assertTrue(8.isMultipleOf(1))
        assertTrue(8.isMultipleOf(8))
        assertTrue(24.isMultipleOf(8))
    }

    @Test(expected = ArithmeticException::class)
    fun `isMultipleOf - 0 isMultipleOf 0`() {
        0.isMultipleOf(0)
    }

    @Test
    fun charForDigit() {
        assertEquals('0', 0.charForDigit())
        assertEquals('1', 1.charForDigit())
        assertEquals('9', 9.charForDigit())
    }

    @Test
    fun digits() {
        assertEquals(listOf(0), 0.digits())
        assertEquals(listOf(1), 1.digits())
        assertEquals(listOf(9, 9), 99.digits())
        assertEquals(listOf(1, 2, 3, 4), 1234.digits())
    }

    @Test(expected = IllegalArgumentException::class)
    fun `digits throws when negative`() {
        (-7).digits()
    }

    @Test
    fun withDigitsReversed() {
        assertEquals(0, 0.withDigitsReversed())
        assertEquals(1, 1.withDigitsReversed())
        assertEquals(21, 12.withDigitsReversed())
        assertEquals(4321, 1234.withDigitsReversed())
        assertEquals(-54321, (-12345).withDigitsReversed())
        assertEquals(-1, (-1).withDigitsReversed())
        assertEquals(-1, (-100).withDigitsReversed())
        assertEquals(1001, 1001.withDigitsReversed())
        assertEquals(-1001, (-1001).withDigitsReversed())
        assertEquals(1234567, 7654321.withDigitsReversed())
        assertEquals(-1234567, (-7654321).withDigitsReversed())
        assertEquals(-1234567, (-76543210).withDigitsReversed())
        assertNull((Int.MIN_VALUE + 1).withDigitsReversed())
        assertNull(Int.MIN_VALUE.withDigitsReversed())
        assertNull(Int.MAX_VALUE.withDigitsReversed())
        assertNull(1534236469.withDigitsReversed())
    }

    @Test
    fun numberOfDigits() {
        assertEquals(4, 1234.numberOfDigits())
        assertEquals(2, 99.numberOfDigits())
        assertEquals(2, 10.numberOfDigits())
        assertEquals(1, 9.numberOfDigits())
        assertEquals(1, 1.numberOfDigits())
        assertEquals(1, (-1).numberOfDigits())
        assertEquals(1, (-9).numberOfDigits())
        assertEquals(2, (-10).numberOfDigits())
        assertEquals(4, (-9999).numberOfDigits())
        assertEquals(1, 0.numberOfDigits())
        assertEquals(10, Int.MAX_VALUE.numberOfDigits())
        assertEquals(10, Int.MIN_VALUE.numberOfDigits())
    }

    @Test
    fun mostSignificantDigit() {
        assertEquals(1, 123.mostSignificantDigit())
    }

    @Test
    fun leastSignificantDigit() {
        assertEquals(3, 123.leastSignificantDigit())
    }

    @Test
    fun nMostSignificantDigits() {
        assertEquals(12, 123.nMostSignificantDigits(2))
    }

    @Test
    fun nLeastSignificantDigits() {
        assertEquals(23, 123.nLeastSignificantDigits(2))
    }
}