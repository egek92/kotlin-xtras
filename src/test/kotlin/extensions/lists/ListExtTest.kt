package extensions.lists

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ListExtTest {

    private val nestedList3x4 = listOf(
        listOf(1, 2, 3, 4),
        listOf(5, 6, 7, 8),
        listOf(9, 10, 11, 12)
    )

    private val nestedList1x3 = listOf(listOf(1, 2, 3))

    @Test
    fun halves() {
        assertEquals(Pair(emptyList<Int>(), emptyList<Int>()), emptyList<Int>().halves)
        assertEquals(Pair(listOf(1), emptyList<Int>()), listOf(1).halves)
        assertEquals(Pair(listOf(1), listOf(2)), listOf(1, 2).halves)
        assertEquals(Pair(listOf(1, 2), listOf(3)), listOf(1, 2, 3).halves)
        assertEquals(Pair(listOf(1, 2), listOf(3, 4)), listOf(1, 2, 3, 4).halves)
    }

    @Test
    fun valueToIndicesMap() {
        assertEquals(
            mapOf("foo" to listOf(0, 2), "bar" to listOf(1), "baz" to listOf(3)),
            listOf("foo", "bar", "foo", "baz").valueToIndicesMap()
        )

        assertEquals(
            mapOf(1 to listOf(0, 2), 7 to listOf(1)),
            listOf(1, 7, 1).valueToIndicesMap()
        )
    }

    @Test
    fun valueToIndexMap() {
        // No duplicates
        assertEquals(
            mapOf(4 to 0, 3 to 1, 1 to 2, 2 to 3),
            listOf(4, 3, 1, 2).valueToIndexMap()
        )

        // With duplicates
        assertEquals(
            mapOf(4 to 4, 3 to 1, 1 to 2, 2 to 3),
            listOf(4, 3, 1, 2, 4).valueToIndexMap()
        )
    }

    @Test
    fun toPair() {
        assertEquals(Pair(1, 2), listOf(1, 2).toPair())
    }

    @Test(expected = IllegalArgumentException::class)
    fun `toPair throws if size less than 2`() {
        listOf(1).toPair()

    }

    @Test(expected = IllegalArgumentException::class)
    fun `toPair throws if size greater than 2`() {
        listOf(1, 2, 3).toPair()
    }

    @Test
    fun toTriple() {
        assertEquals(Triple(1, 2, 3), listOf(1, 2, 3).toTriple())
    }

    @Test(expected = IllegalArgumentException::class)
    fun `toTriple throws if size less than 3`() {
        listOf(1, 2).toTriple()

    }

    @Test(expected = IllegalArgumentException::class)
    fun `toTriple throws if size greater than 3`() {
        listOf(1, 2, 3, 4).toTriple()
    }

    @Test
    fun isSorted() {
        assertTrue(emptyList<Int>().isSorted())
        assertTrue(listOf(1).isSorted())
        assertTrue(listOf(1, 99).isSorted())
        assertTrue(listOf(1, 2, 3, 4).isSorted())
        assertFalse(listOf(4, 3, 2, 1).isSorted())
        assertFalse(listOf(1, 2, 4, 3).isSorted())
        assertTrue(listOf("bar", "foo").isSorted())
    }

    @Test
    fun isSortedDescending() {
        assertTrue(emptyList<Int>().isSortedDescending())
        assertTrue(listOf(1).isSortedDescending())
        assertTrue(listOf(100, 99).isSortedDescending())
        assertFalse(listOf(1, 2, 3, 4).isSortedDescending())
        assertTrue(listOf(4, 3, 2, 1).isSortedDescending())
        assertFalse(listOf(1, 2, 4, 3).isSortedDescending())
    }

    @Test
    fun nestedListProperties() {
        assertEquals(3, nestedList3x4.rows)
        assertEquals(4, nestedList3x4.columns)
        assertEquals(2, nestedList3x4.lastRow)
        assertEquals(3, nestedList3x4.lastColumn)
        assertEquals((0 until 3), nestedList3x4.rowRange)
        assertEquals((0 until 4), nestedList3x4.columnRange)
        assertEquals(1, nestedList1x3.rows)
        assertEquals(3, nestedList1x3.columns)
        assertEquals(0, nestedList1x3.lastRow)
        assertEquals(2, nestedList1x3.lastColumn)
        assertEquals((0 until 1), nestedList1x3.rowRange)
        assertEquals((0 until 3), nestedList1x3.columnRange)
    }

}