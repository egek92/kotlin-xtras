package extensions.math

import org.junit.Assert.*
import org.junit.Test
import java.math.BigInteger

class BigIntegerExtTest {

    @Test
    fun coerceToIntRange() {
        assertEquals(0, BigInteger.ZERO.coerceToIntRange())
        assertEquals(1, BigInteger.ONE.coerceToIntRange())
        assertEquals(10, BigInteger.TEN.coerceToIntRange())
        assertEquals(-999, (-999).toBigInteger().coerceToIntRange())
        assertEquals(Int.MIN_VALUE, (Int.MIN_VALUE).toBigInteger().coerceToIntRange())
        assertEquals(Int.MAX_VALUE, (Int.MAX_VALUE).toBigInteger().coerceToIntRange())
        assertEquals(Int.MAX_VALUE, 2147483648L.toBigInteger().coerceToIntRange())
        assertEquals(Int.MIN_VALUE, (-91283472332L).toBigInteger().coerceToIntRange())
    }
}