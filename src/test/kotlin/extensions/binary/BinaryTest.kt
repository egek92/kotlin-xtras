package extensions.binary

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.pow

@SuppressWarnings("NumericOverflow")
class BinaryTest {

    @Test
    fun isEvenNumber() {
        setOf(-10, -4, 0, 2, 4, 6, 20, 100, 128).forEach { Assert.assertTrue(it.isEvenNumber) }
        setOf(-9, -3, -1, 1, 3, 7, 21, 101, 127).forEach { Assert.assertFalse(it.isEvenNumber) }
    }

    @Test
    fun isOddNumber() {
        setOf(-9, -3, -1, 1, 3, 7, 21, 101, 127).forEach { Assert.assertTrue(it.isOddNumber) }
        setOf(-10, -4, 0, 2, 4, 6, 20, 100, 128).forEach { Assert.assertFalse(it.isOddNumber) }
    }

    @Test
    fun isPowerOfTwo() {
        val powersOfTwo = (0..20).map { n ->
            2.0.pow(n).toInt()
        }

        (0..1000).forEach { n ->
            if (n in powersOfTwo) {
                assertTrue(n.isPowerOfTwo)
            } else {
                assertFalse(n.isPowerOfTwo)
            }
        }

        assertFalse((-1).isPowerOfTwo)
        assertFalse((-8).isPowerOfTwo)
    }

    @Test
    fun hasKthBitSet() {
        (0..3).forEach { k ->
            assertTrue(15.hasKthBitSet(k))
        }
        (4..31).forEach { k ->
            assertFalse(15.hasKthBitSet(k))
        }

        (0..31).forEach { k ->
            assertFalse(0.hasKthBitSet(k))
        }

        (0..31).forEach { k ->
            assertTrue((-1).hasKthBitSet(k))
        }

        assertFalse(10.hasKthBitSet(0))
        assertTrue(10.hasKthBitSet(1))
        assertFalse(10.hasKthBitSet(2))
        assertTrue(10.hasKthBitSet(3))

        assertFalse((-5).hasKthBitSet(2))
        assertTrue((-5).hasKthBitSet(0))
        assertTrue((-5).hasKthBitSet(1))
        assertTrue((-5).hasKthBitSet(3))
    }

    @Test
    fun positionOfRightmost1Bit() {
        assertEquals(0, 15.positionOfRightmost1Bit())
        assertEquals(1, 10.positionOfRightmost1Bit())
        assertEquals(3, 8.positionOfRightmost1Bit())
        assertEquals(-1, 0.positionOfRightmost1Bit())
        assertEquals(0, (-5).positionOfRightmost1Bit())
        assertEquals(1, 234.positionOfRightmost1Bit())
    }


    @Test
    fun positionOfRightmost0Bit() {
        assertEquals(4, 15.positionOfRightmost0Bit())
        assertEquals(0, 10.positionOfRightmost0Bit())
        assertEquals(0, 8.positionOfRightmost0Bit())
        assertEquals(0, 0.positionOfRightmost0Bit())
        assertEquals(2, (-5).positionOfRightmost0Bit())
        assertEquals(-1, (-1).positionOfRightmost0Bit())
    }

    @Test
    fun valueOfKthBit() {
        (0..3).forEach { k ->
            assertEquals(1, 15.valueOfKthBit(k))
        }
        (4..31).forEach { k ->
            assertEquals(0, 15.valueOfKthBit(k))
        }

        (0..31).forEach { k ->
            assertEquals(0, 0.valueOfKthBit(k))
        }

        (0..31).forEach { k ->
            assertEquals(1, (-1).valueOfKthBit(k))
        }

        assertEquals(0, 10.valueOfKthBit(0))
        assertEquals(1, 10.valueOfKthBit(1))
        assertEquals(0, 10.valueOfKthBit(2))
        assertEquals(1, 10.valueOfKthBit(3))

        assertEquals(0, (-5).valueOfKthBit(2))
        assertEquals(1, (-5).valueOfKthBit(0))
        assertEquals(1, (-5).valueOfKthBit(1))
        assertEquals(1, (-5).valueOfKthBit(3))
    }

    @Test
    fun numberOfOneBits() {
        assertEquals(0, 0.numberOfOneBits())
        assertEquals(1, 1.numberOfOneBits())
        assertEquals(1, 2.numberOfOneBits())
        assertEquals(2, 3.numberOfOneBits())
        assertEquals(1, 4.numberOfOneBits())
        assertEquals(3, 7.numberOfOneBits())
        assertEquals(4, 15.numberOfOneBits())

        assertEquals(INT_BITS - 1, Int.MAX_VALUE.numberOfOneBits())
        assertEquals(1, Int.MIN_VALUE.numberOfOneBits())

        assertEquals(INT_BITS, (-1).numberOfOneBits())
        assertEquals(INT_BITS - 1, (-2).numberOfOneBits())
        assertEquals(INT_BITS - 1, (-5).numberOfOneBits())
        assertEquals(INT_BITS - 2, (-10).numberOfOneBits())
    }

    @Test
    fun numberOfZeroBits() {
        assertEquals(INT_BITS, 0.numberOfZeroBits())
        assertEquals(INT_BITS - 1, 1.numberOfZeroBits())
        assertEquals(INT_BITS - 1, 2.numberOfZeroBits())
        assertEquals(INT_BITS - 2, 3.numberOfZeroBits())
        assertEquals(INT_BITS - 1, 4.numberOfZeroBits())
        assertEquals(INT_BITS - 3, 7.numberOfZeroBits())
        assertEquals(INT_BITS - 4, 15.numberOfZeroBits())

        assertEquals(1, Int.MAX_VALUE.numberOfZeroBits())
        assertEquals(INT_BITS - 1, Int.MIN_VALUE.numberOfZeroBits())

        assertEquals(0, (-1).numberOfZeroBits())
        assertEquals(1, (-2).numberOfZeroBits())
        assertEquals(1, (-5).numberOfZeroBits())
        assertEquals(2, (-10).numberOfZeroBits())
    }

    @Test
    fun numberOfOneBitsAlt() {
        assertEquals(0, 0.numberOfOneBitsAlt())
        assertEquals(1, 1.numberOfOneBitsAlt())
        assertEquals(1, 2.numberOfOneBitsAlt())
        assertEquals(2, 3.numberOfOneBitsAlt())
        assertEquals(1, 4.numberOfOneBitsAlt())
        assertEquals(3, 7.numberOfOneBitsAlt())
        assertEquals(4, 15.numberOfOneBitsAlt())

        assertEquals(INT_BITS - 1, Int.MAX_VALUE.numberOfOneBitsAlt())
        assertEquals(1, Int.MIN_VALUE.numberOfOneBitsAlt())

        assertEquals(INT_BITS, (-1).numberOfOneBitsAlt())
        assertEquals(INT_BITS - 1, (-2).numberOfOneBitsAlt())
        assertEquals(INT_BITS - 1, (-5).numberOfOneBitsAlt())
        assertEquals(INT_BITS - 2, (-10).numberOfOneBitsAlt())
    }

    @Test
    fun numberOfZeroBitsAlt() {
        assertEquals(INT_BITS, 0.numberOfZeroBitsAlt())
        assertEquals(INT_BITS - 1, 1.numberOfZeroBitsAlt())
        assertEquals(INT_BITS - 1, 2.numberOfZeroBitsAlt())
        assertEquals(INT_BITS - 2, 3.numberOfZeroBitsAlt())
        assertEquals(INT_BITS - 1, 4.numberOfZeroBitsAlt())
        assertEquals(INT_BITS - 3, 7.numberOfZeroBitsAlt())
        assertEquals(INT_BITS - 4, 15.numberOfZeroBitsAlt())

        assertEquals(1, Int.MAX_VALUE.numberOfZeroBitsAlt())
        assertEquals(INT_BITS - 1, Int.MIN_VALUE.numberOfZeroBitsAlt())

        assertEquals(0, (-1).numberOfZeroBitsAlt())
        assertEquals(1, (-2).numberOfZeroBitsAlt())
        assertEquals(1, (-5).numberOfZeroBitsAlt())
        assertEquals(2, (-10).numberOfZeroBitsAlt())
    }

    @Test
    fun reverseBits() {
        assertEquals(-1, (-1).reverseBits())
        assertEquals(0, 0.reverseBits())
        assertEquals(Int.MIN_VALUE, 1.reverseBits())
        assertEquals(1, Int.MIN_VALUE.reverseBits())

        assertEquals(-2, Int.MAX_VALUE.reverseBits())
        assertEquals(Int.MAX_VALUE, (-2).reverseBits())
        assertEquals(-536870912, 7.reverseBits())

        assertEquals(964176192, 43261596.reverseBits())
        assertEquals(43261596, 964176192.reverseBits())
    }

    @Test
    fun reverseBytes() {
        assertEquals(-1, (-1).reverseBytes())
        assertEquals(0, 0.reverseBytes())
        assertEquals(16777216, 1.reverseBytes())

        assertEquals(128, Int.MIN_VALUE.reverseBytes())
        assertEquals(-129, Int.MAX_VALUE.reverseBytes())
    }

    @Test
    fun withKthBitSet() {
        assertEquals(11, 10.withKthBitSet(0))
        assertEquals(10, 10.withKthBitSet(1))
        assertEquals(14, 10.withKthBitSet(2))
        assertEquals(10, 10.withKthBitSet(3))
        assertEquals(42, 10.withKthBitSet(5))

        // -99 = 11111111111111111111111110011101
        assertEquals(-99, (-99).withKthBitSet(0))
        assertEquals(-97, (-99).withKthBitSet(1))
        assertEquals(-99, (-99).withKthBitSet(2))
        assertEquals(-99, (-99).withKthBitSet(3))
        assertEquals(-99, (-99).withKthBitSet(4))
        assertEquals(-67, (-99).withKthBitSet(5))
    }

    @Test
    fun withKthBitUnset() {
        var x = -1
        (0 until INT_BITS).forEach { k ->
            x = x.withKthBitUnset(k)
        }

        assertEquals(0, x)

        assertEquals(14, 15.withKthBitUnset(0))
        assertEquals(13, 15.withKthBitUnset(1))
        assertEquals(11, 15.withKthBitUnset(2))
        assertEquals(7, 15.withKthBitUnset(3))
    }


    @Test
    fun withLastBitSet() {
        assertEquals(1, 0.withLastBitSet())
        assertEquals(1, 1.withLastBitSet())
        assertEquals(11, 10.withLastBitSet())
        assertEquals(7, 7.withLastBitSet())
        assertEquals(-1, (-1).withLastBitSet())
        assertEquals(-7, (-7).withLastBitSet())
    }

    @Test
    fun withLastBitUnset() {
        assertEquals(0, 0.withLastBitUnset())
        assertEquals(0, 1.withLastBitUnset())
        assertEquals(10, 10.withLastBitUnset())
        assertEquals(6, 7.withLastBitUnset())
        assertEquals(-2, (-1).withLastBitUnset())
        assertEquals(-8, (-7).withLastBitUnset())
    }

    @Test
    fun toggleKthBit() {
        assertEquals(14, 15.toggleKthBit(0))
        assertEquals(13, 15.toggleKthBit(1))
        assertEquals(11, 15.toggleKthBit(2))
        assertEquals(7, 15.toggleKthBit(3))

        assertEquals(11, 10.toggleKthBit(0))
        assertEquals(8, 10.toggleKthBit(1))
        assertEquals(14, 10.toggleKthBit(2))
        assertEquals(2, 10.toggleKthBit(3))

        assertEquals(0, 1.toggleKthBit(0))
        assertEquals(1, 0.toggleKthBit(0))
    }

    @Test
    fun unsetRightmost1Bit() {
        assertEquals(14, 15.unsetRightmost1Bit())
        assertEquals(12, 14.unsetRightmost1Bit())
        assertEquals(8, 12.unsetRightmost1Bit())
        assertEquals(0, 8.unsetRightmost1Bit())

        assertEquals(0, 0.unsetRightmost1Bit())
        assertEquals(0, 1.unsetRightmost1Bit())

        assertEquals(-2, (-1).unsetRightmost1Bit())
        assertEquals(-4, (-2).unsetRightmost1Bit())
    }

    @Test
    fun setRightmost0Bit() {
        assertEquals(31, 15.setRightmost0Bit())
        assertEquals(15, 14.setRightmost0Bit())
        assertEquals(15, 13.setRightmost0Bit())
        assertEquals(9, 8.setRightmost0Bit())

        assertEquals(1, 0.setRightmost0Bit())
        assertEquals(3, 1.setRightmost0Bit())

        assertEquals(-1, (-2).setRightmost0Bit())
        assertEquals(-1, (-1).setRightmost0Bit())
    }

    @Test
    fun swapBits() {
        assertEquals(15, 15.swapBits(0, 0))
        assertEquals(15, 15.swapBits(0, 1))
        assertEquals(15, 15.swapBits(0, 2))
        assertEquals(15, 15.swapBits(0, 3))
        assertEquals(30, 15.swapBits(0, 4))
        assertEquals(46, 15.swapBits(0, 5))

        assertEquals(7, 7.swapBits(0, 1))
        assertEquals(7, 7.swapBits(0, 2))
        assertEquals(14, 7.swapBits(0, 3))
        assertEquals(22, 7.swapBits(0, 4))

        assertEquals(9, 3.swapBits(1, 3))
    }


    @Test
    fun `Int toBinaryString`() {
        assertEquals("00000000000000000000000000000000", 0.toBinaryString())
        assertEquals("0000000000000000", 0.toBinaryString(16))
        assertEquals("00000000", 0.toBinaryString(8))

        assertEquals("00000000000000000000000000000001", 1.toBinaryString())
        assertEquals("00000000000000000000000000000010", 2.toBinaryString())
        assertEquals("00000000000000000000000000001010", 10.toBinaryString())

        assertEquals("00000000000000000000000001100001", 97.toBinaryString())

        assertEquals("00000000000000000000000001000011", 67.toBinaryString())
        assertEquals("11111111111111111111111110111101", (-67).toBinaryString())

        assertEquals("11111111111111111111111111111111", (-1).toBinaryString())
        assertEquals("11111111111111111111111111111110", (-2).toBinaryString())

        assertEquals("01111111111111111111111111111110", (Int.MAX_VALUE - 1).toBinaryString())
        assertEquals("01111111111111111111111111111111", Int.MAX_VALUE.toBinaryString())        // 2147483647

        assertEquals("10000000000000000000000000000001", (Int.MIN_VALUE + 1).toBinaryString())
        assertEquals("10000000000000000000000000000000", Int.MIN_VALUE.toBinaryString())        // -2147483648

        // Overflow
        assertEquals(Int.MIN_VALUE.toBinaryString(), (Int.MAX_VALUE + 1).toBinaryString())
        assertEquals(Int.MAX_VALUE.toBinaryString(), (Int.MIN_VALUE - 1).toBinaryString())
    }

    @Test
    fun `Long toBinaryString`() {
        assertEquals("0111111111111111111111111111111111111111111111111111111111111111", Long.MAX_VALUE.toBinaryString())
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000", 0L.toBinaryString())
        assertEquals("0000000000000000", 0L.toBinaryString(16))
        assertEquals("00000000", 0L.toBinaryString(8))
    }

    @Test
    fun parseNonNegativeBinaryString() {
        assertEquals(0, "0".parseNonNegativeBinaryString())
        assertEquals(1, "1".parseNonNegativeBinaryString())
        assertEquals(1, "0001".parseNonNegativeBinaryString())
        assertEquals(1, "00000000000000000000000000000001".parseNonNegativeBinaryString())
        assertEquals(974321, "00000000000011101101110111110001".parseNonNegativeBinaryString())
        assertEquals(Int.MAX_VALUE, "01111111111111111111111111111111".parseNonNegativeBinaryString())
    }

    @Test(expected = NumberFormatException::class)
    fun `parseNonNegativeBinaryString throws exception when called on negative Int MIN_VALUE`() {
        "10000000000000000000000000000001".parseNonNegativeBinaryString()
    }

    @Test(expected = NumberFormatException::class)
    fun `parseNonNegativeBinaryString throws exception when called on -1`() {
        "11111111111111111111111111111111".parseNonNegativeBinaryString()
    }
}