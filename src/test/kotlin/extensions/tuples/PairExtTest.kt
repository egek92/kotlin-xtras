package extensions.tuples

import extensions.math.EPSILON
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PairExtTest {

    @Test
    fun toBooleanArray() {
        assertArrayEquals(booleanArrayOf(true, false), Pair(true, false).toBooleanArray())
    }

    @Test
    fun toByteArray() {
        assertArrayEquals(byteArrayOf(0b01, 0b11), Pair<Byte, Byte>(0b01, 0b11).toByteArray())
    }

    @Test
    fun toCharArray() {
        assertArrayEquals(charArrayOf('a', 'b'), Pair('a', 'b').toCharArray())
    }

    @Test
    fun toDoubleArray() {
        assertArrayEquals(doubleArrayOf(1.0, 2.0), Pair(1.0, 2.0).toDoubleArray(), 0.000001)
    }

    @Test
    fun toFloatArray() {
        assertArrayEquals(floatArrayOf(123.4f, 567.8f), Pair(123.4f, 567.8f).toFloatArray(), EPSILON.toFloat())
    }

    @Test
    fun toIntArray() {
        assertArrayEquals(intArrayOf(1, 2), Pair(1, 2).toIntArray())
    }

    @Test
    fun toLongArray() {
        assertArrayEquals(longArrayOf(1234L, 5678L), Pair(1234L, 5678L).toLongArray())
    }

    @Test
    fun toShortArray() {
        assertArrayEquals(shortArrayOf(0, 1), Pair<Short, Short>(0, 1).toShortArray())
    }

    @Test
    fun nullabilityOneType() {
        // Pair<T?, T?>
        val neitherNullA = Pair(7, 9)
        val firstNullA = Pair(null, 9)
        val secondNullA = Pair(7, null)
        val bothNullA = Pair<Int?, Int?>(null, null)

        assertFalse(neitherNullA.bothNull())
        assertFalse(firstNullA.bothNull())
        assertFalse(secondNullA.bothNull())
        assertTrue(bothNullA.bothNull())

        assertFalse(neitherNullA.eitherNull())
        assertTrue(firstNullA.eitherNull())
        assertTrue(secondNullA.eitherNull())
        assertTrue(bothNullA.eitherNull())

        assertTrue(neitherNullA.neitherNull())
        assertFalse(firstNullA.neitherNull())
        assertFalse(secondNullA.neitherNull())
        assertFalse(bothNullA.neitherNull())

        // Pair<T?, U?>
        // Not sure why exactly this works since ext. fn defined on Pair<T?, T?>, not Pair<T?, U?>...
        val neitherNullB = Pair(7, "9")
        val firstNullB = Pair<Int?, String>(null, "9")
        val secondNull = Pair<Int, String?>(7, null)
        val bothNull = Pair<Int?, String?>(null, null)

        assertFalse(neitherNullB.bothNull())
        assertFalse(firstNullB.bothNull())
        assertFalse(secondNull.bothNull())
        assertTrue(bothNull.bothNull())

        assertFalse(neitherNullB.eitherNull())
        assertTrue(firstNullB.eitherNull())
        assertTrue(secondNull.eitherNull())
        assertTrue(bothNull.eitherNull())

        assertTrue(neitherNullB.neitherNull())
        assertFalse(firstNullB.neitherNull())
        assertFalse(secondNull.neitherNull())
        assertFalse(bothNull.neitherNull())

        // Not sure why it is available on Pair<T, T>, either
        val nonnullablePair: Pair<Int, Int> = Pair(1, 7)
        assertFalse(nonnullablePair.bothNull())
        assertFalse(nonnullablePair.eitherNull())
        assertTrue(nonnullablePair.neitherNull())
    }

    @Test
    fun and() {
        assertEquals(Triple(1, 2, 3), Pair(1, 2) and 3)
        assertEquals(Triple(1, "foo", 99.99), Pair(1, "foo") and 99.99)
        assertEquals(Triple(1, null, 99.99), Pair(1, null) and 99.99)
    }

    @Test
    fun reversed() {
        assertEquals(Pair(9, 7), Pair(7, 9).reversed())
    }

    @Test
    fun reversedIf() {
        assertEquals(Pair(9, 7), Pair(7, 9).reversedIf { it.first < it.second })
        assertEquals(Pair(7, 9), Pair(7, 9).reversedIf { it.first > it.second })
    }

    @Test
    fun maxAndMin() {
        assertEquals(7, Pair(7, -9).max())
        assertEquals(-9, Pair(7, -9).min())
    }

    @Test
    fun contains() {
        assertTrue(Pair(7, -9).contains(7))
        assertTrue(Pair(7, -9).contains(-9))
        assertFalse(Pair(7, -9).contains(9))
    }

    @Test
    fun shorter() {
        assertEquals("A", Pair("A", "ABC").shorter())
        assertEquals("ABC", Pair("abcd1234", "ABC").shorter())
        assertEquals("ABC", Pair("ABC", "DEF").shorter())
    }

    @Test
    fun longer() {
        assertEquals("ABC", Pair("A", "ABC").longer())
        assertEquals("abcd1234", Pair("abcd1234", "ABC").longer())
        assertEquals("DEF", Pair("ABC", "DEF").longer())
    }

    @Test
    fun shorterAndLonger() {
        assertEquals(Pair("A", "ABC"), Pair("A", "ABC").shorterAndLonger())
        assertEquals(Pair("ABC", "abcd1234"), Pair("abcd1234", "ABC").shorterAndLonger())
        assertEquals(Pair("ABC", "DEF"), Pair("ABC", "DEF").shorterAndLonger())
    }
}