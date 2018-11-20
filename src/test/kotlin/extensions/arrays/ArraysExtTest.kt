package extensions.arrays

import org.junit.Assert.*
import org.junit.Test

class ArraysExtTest {
    private val intArray = intArrayOf(1, 2, 3)
    private val charArray = charArrayOf('1', '2', '3')

    @Test
    fun groupingBy() {
        assertEquals(
            mapOf(1 to 2, 7 to 1),
            intArrayOf(1, 7, 1).groupingBy { it }.eachCount()
        )
    }

    @Test
    fun frequencyMap() {
        assertEquals(
            mapOf("foo" to 2, "bar" to 1, "baz" to 1),
            arrayOf("foo", "bar", "foo", "baz").frequencyMap()
        )

        assertEquals(
            mapOf(1 to 2, 7 to 1),
            intArrayOf(1, 7, 1).frequencyMap()
        )
    }

    @Test
    fun valueToIndicesMap() {
        assertEquals(
            mapOf("foo" to listOf(0, 2), "bar" to listOf(1), "baz" to listOf(3)),
            arrayOf("foo", "bar", "foo", "baz").valueToIndicesMap()
        )

        assertEquals(
            mapOf(1 to listOf(0, 2), 7 to listOf(1)),
            intArrayOf(1, 7, 1).valueToIndicesMap()
        )
    }

    @Test
    fun valueToIndexMap() {
        // No duplicates
        assertEquals(
            mapOf(4 to 0, 3 to 1, 1 to 2, 2 to 3),
            arrayOf(4, 3, 1, 2).valueToIndexMap()
        )
        assertEquals(
            mapOf(4 to 0, 3 to 1, 1 to 2, 2 to 3),
            intArrayOf(4, 3, 1, 2).valueToIndexMap()
        )

        // With duplicates
        assertEquals(
            mapOf(4 to 4, 3 to 1, 1 to 2, 2 to 3),
            arrayOf(4, 3, 1, 2, 4).valueToIndexMap()
        )
        assertEquals(
            mapOf(4 to 4, 3 to 1, 1 to 2, 2 to 3),
            intArrayOf(4, 3, 1, 2, 4).valueToIndexMap()
        )
    }

    @Test
    fun zipWithNext() {
        assertEquals(
            listOf("foo", "bar", "baz", "omega", "theta").zipWithNext(),
            arrayOf("foo", "bar", "baz", "omega", "theta").zipWithNext()
        )
        assertEquals(
            listOf(1, 2, 3, 4, 5, 6).zipWithNext(),
            intArrayOf(1, 2, 3, 4, 5, 6).zipWithNext()
        )
        assertEquals(
            listOf(1).zipWithNext(),
            intArrayOf(1).zipWithNext()
        )
        assertEquals(
            listOf<Int>().zipWithNext(),
            intArrayOf().zipWithNext()
        )
    }

    @Test
    fun swap() {
        val strArr = arrayOf("foo", "bar", "foo", "baz")
        strArr.swap(0, 2)
        assertArrayEquals(arrayOf("foo", "bar", "foo", "baz"), strArr)
        strArr.swap(1, 3)
        assertArrayEquals(arrayOf("foo", "baz", "foo", "bar"), strArr)

        val intArr = intArrayOf(1, 7, 1, 3, 6, 4)
        intArr.swap(1, 5)
        val expected = intArrayOf(1, 4, 1, 3, 6, 7)
        assertArrayEquals(expected, intArr)
    }

    @Test
    fun reverseElementsInRange() {
        val strArr = arrayOf("foo", "bar", "foo", "baz")
        strArr.reverseElementsInRange(1..3)
        assertArrayEquals(arrayOf("foo", "baz", "foo", "bar"), strArr)

        intArray.reverseElementsInRange(0..2)
        assertArrayEquals(intArrayOf(3, 2, 1), intArray)

        charArray.reverseElementsInRange(0..2)
        assertArrayEquals(charArrayOf('3', '2', '1'), charArray)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `reverseElementsInRange with invalid range`() {
        val strArr = arrayOf("foo", "bar", "foo", "baz")
        strArr.reverseElementsInRange(2..4)
    }

    @Test
    fun isSorted() {
        assertTrue(arrayOf("abc", "def", "ghi").isSorted())
        assertTrue(intArrayOf(1, 2, 3, 4).isSorted())
        assertFalse(intArrayOf(1, 2, 4, 3).isSorted())
        assertTrue(longArrayOf(1L, 2L, 3L, 4L).isSorted())
        assertFalse(longArrayOf(1L, 2L, 4L, 3L).isSorted())
        assertTrue(floatArrayOf(1F, 2F, 3F, 4F).isSorted())
        assertFalse(floatArrayOf(1F, 2F, 4F, 3F).isSorted())
        assertTrue(doubleArrayOf(1.0, 2.0, 3.0, 4.0).isSorted())
        assertFalse(doubleArrayOf(1.0, 2.0, 4.0, 3.0).isSorted())
        assertTrue(shortArrayOf(1, 2, 3, 4).isSorted())
        assertTrue(charArrayOf('1', '2', '3', '4').isSorted())
        assertFalse(charArrayOf('1', '2', '4', '3').isSorted())
        assertTrue(charArrayOf('1', '2', '3', 'A', 'a').isSorted())

        assertTrue(emptyArray<String>().isSorted())
        assertTrue(intArrayOf().isSorted())
        assertTrue(charArrayOf().isSorted())

        assertTrue(arrayOf("foo").isSorted())
        assertTrue(intArrayOf(1).isSorted())
        assertTrue(charArrayOf('A').isSorted())

        assertTrue(arrayOf("bar", "foo").isSorted())
        assertTrue(intArrayOf(1, 2).isSorted())
        assertTrue(charArrayOf('A', 'B').isSorted())
    }

    @Test
    fun isSortedDescending() {
        assertTrue(arrayOf("ghi", "def", "abc").isSortedDescending())
        assertTrue(intArrayOf(4, 3, 2, 1).isSortedDescending())
        assertFalse(intArrayOf(4, 3, 1, 2).isSortedDescending())
        assertTrue(longArrayOf(4L, 3L, 2L, 1L).isSortedDescending())
        assertFalse(longArrayOf(1L, 3L, 4L).isSortedDescending())
        assertTrue(floatArrayOf(4F, 3F, 2F, 1F).isSortedDescending())
        assertFalse(floatArrayOf(1F, 2F, 4F, 3F).isSortedDescending())
        assertTrue(doubleArrayOf(4.0, 3.0, 2.0, 1.0).isSortedDescending())
        assertFalse(doubleArrayOf(1.0, 2.0, 4.0, 3.0).isSortedDescending())
        assertTrue(shortArrayOf(4, 3, 2).isSortedDescending())
        assertTrue(charArrayOf('4', '3', '2').isSortedDescending())
        assertFalse(charArrayOf('1', '2', '4', '3').isSortedDescending())
        assertTrue(charArrayOf('a', 'A', '1').isSortedDescending())

        assertTrue(emptyArray<String>().isSortedDescending())
        assertTrue(intArrayOf().isSortedDescending())
        assertTrue(charArrayOf().isSortedDescending())

        assertTrue(arrayOf("foo").isSortedDescending())
        assertTrue(intArrayOf(1).isSortedDescending())
        assertTrue(charArrayOf('A').isSortedDescending())

        assertTrue(arrayOf("foo", "bar").isSortedDescending())
        assertTrue(intArrayOf(2, 1).isSortedDescending())
        assertTrue(charArrayOf('B', 'A').isSortedDescending())
    }
}