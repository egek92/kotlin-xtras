package extensions.math

import org.junit.Assert.*
import org.junit.Test

class LongExtTest {

    @Test
    fun coerceToIntRange() {
        assertEquals(0, 0L.coerceToIntRange())
        assertEquals(-999, (-999L).coerceToIntRange())
        assertEquals(Int.MIN_VALUE, (Int.MIN_VALUE).toLong().coerceToIntRange())
        assertEquals(Int.MAX_VALUE, (Int.MAX_VALUE).toLong().coerceToIntRange())
        assertEquals(Int.MAX_VALUE, 2147483648L.coerceToIntRange())
        assertEquals(Int.MIN_VALUE, (-91283472332L).coerceToIntRange())
    }
}