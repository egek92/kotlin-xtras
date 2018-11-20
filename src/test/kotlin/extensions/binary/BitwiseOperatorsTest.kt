package extensions.binary

import org.junit.Assert
import org.junit.Test

class BitwiseOperatorsTest {

    @Test
    fun bitwiseOperators() {
        Assert.assertEquals(0 and 0, 0 `&` 0)
        Assert.assertEquals(0 and 1, 0 `&` 1)
        Assert.assertEquals(1 and 0, 1 `&` 0)
        Assert.assertEquals(1 and 1, 1 `&` 1)

        Assert.assertEquals(0 or 0, 0 `|` 0)
        Assert.assertEquals(0 or 1, 0 `|` 1)
        Assert.assertEquals(1 or 0, 1 `|` 0)
        Assert.assertEquals(1 or 1, 1 `|` 1)

        Assert.assertEquals(0 xor 0, 0 `^` 0)
        Assert.assertEquals(0 xor 1, 0 `^` 1)
        Assert.assertEquals(1 xor 0, 1 `^` 0)
        Assert.assertEquals(1 xor 1, 1 `^` 1)

        Assert.assertEquals(4.inv(), !4)
        Assert.assertEquals(20.inv(), !20)
        Assert.assertEquals((-50).inv(), !(-50))
        Assert.assertEquals(Int.MAX_VALUE.inv(), !Int.MAX_VALUE)

        Assert.assertEquals(1 shl 3, 1 shiftLeft 3)
        Assert.assertEquals(-100 shl 4, -100 shiftLeft 4)

        Assert.assertEquals(1 shr 3, 1 shiftRight 3)
        Assert.assertEquals(-100 shr 4, -100 shiftRight 4)

        Assert.assertEquals(1 ushr 3, 1 unsignedShiftRight 3)
        Assert.assertEquals(-8 ushr 3, -8 unsignedShiftRight 3)
    }


    @Test
    fun indexedAccessGet() {
        (0..3).forEach { k ->
            Assert.assertEquals(1, 15[k])
        }
        (4..31).forEach { k ->
            Assert.assertEquals(0, 15[k])
        }

        (0..31).forEach { k ->
            Assert.assertEquals(0, 0[k])
        }

        (0..31).forEach { k ->
            Assert.assertEquals(1, (-1)[k])
        }

        Assert.assertEquals(0, 10[0])
        Assert.assertEquals(1, 10[1])
        Assert.assertEquals(0, 10[2])
        Assert.assertEquals(1, 10[3])

        Assert.assertEquals(0, (-5)[2])
        Assert.assertEquals(1, (-5)[0])
        Assert.assertEquals(1, (-5)[1])
        Assert.assertEquals(1, (-5)[3])
    }

    @Test(expected = IllegalArgumentException::class)
    fun `indexedAccessGet - invalid index less than 0`() {
        15[-1]
    }

    @Test(expected = IllegalArgumentException::class)
    fun `indexedAccessGet - invalid index more than 31`() {
        15[INT_BITS]
    }

}