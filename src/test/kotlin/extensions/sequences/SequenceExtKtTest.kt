package extensions.sequences

import org.junit.Test

import org.junit.Assert.*

class SequenceExtKtTest {

    @Test
    fun repeating() {
        val repeated1s = sequenceOf(1, 1, 1).repeating()
        assertEquals(
            100,
            repeated1s.take(100).sum()
        )
    }

    @Test
    fun repeated() {
        assertEquals(
            sequenceOf(1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3).toList(),
            sequenceOf(1, 2, 3).repeated(4).toList()
        )
    }
}