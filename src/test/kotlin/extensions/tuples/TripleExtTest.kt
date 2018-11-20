package extensions.tuples

import extensions.math.EPSILON
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TripleExtTest {

    private val intTriple = Triple(4, 3, 10)
    private val strTriple = Triple("foo", "bar", "baz")

    @Test
    fun toIntArray() {
        assertArrayEquals(intArrayOf(4, 3, 10), intTriple.toIntArray())
    }

    @Test
    fun toBooleanArray() {
        assertArrayEquals(booleanArrayOf(true, false, true), Triple(true, false, true).toBooleanArray())
    }

    @Test
    fun toByteArray() {
        assertArrayEquals(byteArrayOf(0b01, 0b10, 0b11), Triple<Byte, Byte, Byte>(0b01, 0b10, 0b11).toByteArray())
    }

    @Test
    fun toCharArray() {
        assertArrayEquals(charArrayOf('a', 'b', 'c'), Triple('a', 'b', 'c').toCharArray())
    }

    @Test
    fun toDoubleArray() {
        assertArrayEquals(doubleArrayOf(1.0, 2.0, 3.0), Triple(1.0, 2.0, 3.0).toDoubleArray(), EPSILON)
    }

    @Test
    fun toFloatArray() {
        assertArrayEquals(floatArrayOf(123.4f, 567.8f, 999.9f), Triple(123.4f, 567.8f, 999.9f).toFloatArray(), EPSILON.toFloat())
    }

    @Test
    fun toLongArray() {
        assertArrayEquals(longArrayOf(1234L, 5678L, 9999L), Triple(1234L, 5678L, 9999L).toLongArray())
    }

    @Test
    fun toShortArray() {
        assertArrayEquals(shortArrayOf(0, 1, 2), Triple<Short, Short, Short>(0, 1, 2).toShortArray())
    }

    @Test
    fun median() {
        assertEquals(4, intTriple.median())
        assertEquals(4.2, Triple(4.1, 4.2, 4.3).median(), EPSILON)
        assertEquals('m', Triple('a', 'm', 'z').median())
    }

    @Test
    fun contains() {
        assertTrue(intTriple.contains(4))
        assertTrue(intTriple.contains(3))
        assertTrue(intTriple.contains(10))
        assertFalse(intTriple.contains(99))

        assertTrue(strTriple.contains("foo"))
        assertFalse(strTriple.contains("foobar"))
        assertFalse(strTriple.contains(4))
    }
}