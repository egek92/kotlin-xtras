package extensions.arrays


import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BooleanMatrixTest {
    /**
     * [
     *   [false, true, false, true],
     *   [false, true, false, true],
     *   [false, true, false, true]
     * ]
     */
    private val matrix3x4: BooleanMatrix = Array(3) {
        BooleanArray(4) { j -> j % 2 != 0 }
    }

    /**
     * [[false, true, false]]
     */
    private val matrix1x3: BooleanMatrix = Array(1) { booleanArrayOf(false, true, false) }

    private val list3x4: List<List<Boolean>> =
        listOf(listOf(false, true, false, true), listOf(false, true, false, true), listOf(false, true, false, true))
    private val list1x3: List<List<Boolean>> = listOf(listOf(false, true, false))

    /* Matrix */
    @Test
    fun matrixProperties() {
        assertEquals(3, matrix3x4.rows)
        assertEquals(4, matrix3x4.columns)
        assertEquals(2, matrix3x4.lastRowIndex)
        assertEquals(3, matrix3x4.lastColumnIndex)
        assertEquals((0 until 3), matrix3x4.rowIndices)
        assertEquals((0 until 4), matrix3x4.columnIndices)
        assertEquals(1, matrix1x3.rows)
        assertEquals(3, matrix1x3.columns)
        assertEquals(0, matrix1x3.lastRowIndex)
        assertEquals(2, matrix1x3.lastColumnIndex)
        assertEquals((0 until 1), matrix1x3.rowIndices)
        assertEquals((0 until 3), matrix1x3.columnIndices)
        assertEquals(IntRange.EMPTY, emptyArray<IntArray>().rowIndices)
        assertEquals(IntRange.EMPTY, emptyArray<IntArray>().columnIndices)
    }

    @Test
    fun isValidPosition() {
        assertTrue(matrix3x4.isValidPosition(0, 0))
        assertTrue(matrix3x4.isValidPosition(0, 1))
        assertTrue(matrix3x4.isValidPosition(0, 2))
        assertTrue(matrix3x4.isValidPosition(0, 3))
        assertFalse(matrix3x4.isValidPosition(0, 4))
        assertTrue(matrix3x4.isValidPosition(1, 0))
        assertTrue(matrix3x4.isValidPosition(1, 1))
        assertTrue(matrix3x4.isValidPosition(1, 2))
        assertTrue(matrix3x4.isValidPosition(1, 3))
        assertFalse(matrix3x4.isValidPosition(1, 4))
        assertTrue(matrix3x4.isValidPosition(2, 0))
        assertTrue(matrix3x4.isValidPosition(2, 1))
        assertTrue(matrix3x4.isValidPosition(2, 2))
        assertTrue(matrix3x4.isValidPosition(2, 3))
        assertFalse(matrix3x4.isValidPosition(2, 4))
        assertFalse(matrix3x4.isValidPosition(3, 0))
    }


    @Test
    fun booleanMatrixWithSize() {
        assertArrayEquals(
            arrayOf(booleanArrayOf(false, false), booleanArrayOf(false, false), booleanArrayOf(false, false)),
            booleanMatrixWithSize(3, 2)
        )

        assertArrayEquals(
            arrayOf(
                booleanArrayOf(false, false, false),
                booleanArrayOf(false, false, false),
                booleanArrayOf(false, false, false)
            ),
            booleanMatrixWithSize(3, 3)
        )

        assertArrayEquals(
            arrayOf(booleanArrayOf(false, false, false), booleanArrayOf(false, false, false)),
            booleanMatrixWithSize(2, 3)
        )
    }

    @Test
    fun buildBooleanMatrixTest() {
        assertArrayEquals(
            matrix1x3,
            buildBooleanMatrix(listOf(false, true, false))
        )

        assertArrayEquals(
            matrix3x4,
            buildBooleanMatrix(
                listOf(false, true, false, true),
                listOf(false, true, false, true),
                listOf(false, true, false, true)
            )
        )
    }


    @Test(expected = IllegalArgumentException::class)
    fun buildBooleanMatrixDifferingRowLengths() {
        extensions.arrays.buildBooleanMatrix(listOf(true), listOf(true, false))
    }

    @Test(expected = IllegalArgumentException::class)
    fun buildBooleanMatrixNoElements() {
        extensions.arrays.buildBooleanMatrix()
    }

    @Test(expected = IllegalArgumentException::class)
    fun buildBooleanMatrixNoElements2() {
        extensions.arrays.buildBooleanMatrix(emptyList())
    }


    @Test
    fun toList() {
        assertEquals(list3x4, matrix3x4.toList())
        assertEquals(list1x3, matrix1x3.toList())
    }

    @Test
    fun toMatrix() {
        assertArrayEquals(list1x3.toMatrix(), matrix1x3)
        assertArrayEquals(list3x4.toMatrix(), matrix3x4)
    }

}