package extensions.ranges

import org.junit.Test

import org.junit.Assert.*

class IntRangeExtTest {

    @Test
    fun mid() {
        assertEquals(0, (0..0).mid)
        assertEquals(0, (0..1).mid)
        assertEquals(1, (0..2).mid)
        assertEquals(1, (0..3).mid)
        assertEquals(2, (0..4).mid)

        assertEquals(10, (10..10).mid)
        assertEquals(10, (10..11).mid)
        assertEquals(11, (10..12).mid)
        assertEquals(11, (10..13).mid)
        assertEquals(12, (10..14).mid)

        assertEquals(33, (33..33).mid)
        assertEquals(33, (33..34).mid)
        assertEquals(34, (33..35).mid)
        assertEquals(34, (33..36).mid)
        assertEquals(35, (33..37).mid)
    }

    @SuppressWarnings("NumericOverflow")
    @Test
    fun midOverflow() {
        assertEquals(Int.MAX_VALUE, (Int.MAX_VALUE..Int.MAX_VALUE).mid)
        assertEquals(Int.MAX_VALUE, (Int.MAX_VALUE..(Int.MAX_VALUE + 1)).mid)
    }

    @Test
    fun isNotEmpty() {
        assertTrue((0..10).isNotEmpty())
        assertTrue((0..1).isNotEmpty())
        assertTrue((0 until 1).isNotEmpty())
        assertTrue((0..0).isNotEmpty())
        assertFalse(IntRange.EMPTY.isNotEmpty())
    }
}