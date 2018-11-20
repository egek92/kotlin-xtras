package extensions.comparables

import org.junit.Assert.*
import org.junit.Test

class ComparablesExtTest {

    @Test
    fun maxOf() {
        assertEquals(4, maxOf(1, 2, 3, 4))
        assertEquals(4L, maxOf(1L, 2L, 3L, 4L))
        assertEquals("foo", maxOf("foo", "bar", "baz"))
    }

    @Test
    fun minOf() {
        assertEquals(1, minOf(1, 2, 3, 4))
        assertEquals(1L, minOf(1L, 2L, 3L, 4L))
        assertEquals("bar", minOf("foo", "bar", "baz"))
    }
}