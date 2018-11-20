package extensions.chars

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CharExtTest {

    @Test
    fun asciiValue() {
        assertEquals(48, '0'.asciiValue)
        assertEquals(56, '8'.asciiValue)
        assertEquals(58, ':'.asciiValue)
        assertEquals(65, 'A'.asciiValue)
        assertEquals(90, 'Z'.asciiValue)
        assertEquals(97, 'a'.asciiValue)
        assertEquals(122, 'z'.asciiValue)
    }

    @Test
    fun numericValue() {
        assertEquals(0, '0'.numericValue)
        assertEquals(8, '8'.numericValue)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `numericValue when not digit`() {
        'C'.numericValue
    }

    @Test
    fun isVowel() {
        setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U').forEach {
            assertTrue(it.isVowel)
        }
    }

    @Test
    fun invertCase() {
        assertEquals('A', 'a'.invertCase())
        assertEquals('a', 'A'.invertCase())
        assertEquals('!', '!'.invertCase())
        assertEquals('7', '7'.invertCase())
    }
}