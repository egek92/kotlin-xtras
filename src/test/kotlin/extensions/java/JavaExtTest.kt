package extensions.java

import org.junit.Test

import org.junit.Assert.*

class JavaExtTest {

    @Test
    fun repeated() {
        assertEquals(listOf(7, 7, 7), 7.repeated(3))
    }
}