package extensions.lists

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MutableListExtTest {

    private lateinit var emptyList: MutableList<Int>
    private lateinit var mList: MutableList<Int>

    @Before
    fun setUp() {
        emptyList = arrayListOf()
        mList = arrayListOf(1, 2, 3)
    }

    @Test
    fun append() {
        assertEquals(arrayListOf(1, 2, 3), mList)
        assertEquals(3, mList.size)
        assertEquals(true, mList.append(4))
        assertEquals(arrayListOf(1, 2, 3, 4), mList)
        assertEquals(4, mList.size)
    }

    @Test
    fun appendWhenEmpty() {
        assertEquals(0, emptyList.size)
        assertTrue(emptyList.isEmpty())
        assertEquals(true, emptyList.append(1))
        assertFalse(emptyList.isEmpty())
        assertEquals(1, emptyList.size)
        assertEquals(1, emptyList.first())
    }

    @Test
    fun appendAll() {
        assertEquals(arrayListOf(1, 2, 3), mList)
        assertEquals(3, mList.size)
        assertEquals(true, mList.appendAll(listOf(4, 5, 6)))
        assertEquals(arrayListOf(1, 2, 3, 4, 5, 6), mList)
        assertEquals(6, mList.size)
    }

    @Test
    fun appendAllWhenEmpty() {
        assertEquals(0, emptyList.size)
        assertTrue(emptyList.isEmpty())
        assertEquals(true, emptyList.appendAll(listOf(1, 2, 3)))
        assertFalse(emptyList.isEmpty())
        assertEquals(3, emptyList.size)
        assertEquals(listOf(1, 2, 3), emptyList)
    }

    @Test
    fun prepend() {
        assertEquals(arrayListOf(1, 2, 3), mList)
        assertEquals(3, mList.size)
        mList.prepend(4)
        assertEquals(arrayListOf(4, 1, 2, 3), mList)
        assertEquals(4, mList.size)
    }

    @Test
    fun prependWhenEmpty() {
        assertEquals(0, emptyList.size)
        assertTrue(emptyList.isEmpty())
        emptyList.prepend(1)
        assertFalse(emptyList.isEmpty())
        assertEquals(1, emptyList.size)
        assertEquals(1, emptyList.first())
    }

    @Test
    fun prependAll() {
        assertEquals(arrayListOf(1, 2, 3), mList)
        assertEquals(3, mList.size)
        assertEquals(true, mList.prependAll(listOf(4, 5, 6)))
        assertEquals(arrayListOf(4, 5, 6, 1, 2, 3), mList)
        assertEquals(6, mList.size)
    }

    @Test
    fun prependAllWhenEmpty() {
        assertEquals(0, emptyList.size)
        assertTrue(emptyList.isEmpty())
        assertEquals(true, emptyList.prependAll(listOf(1, 2, 3)))
        assertFalse(emptyList.isEmpty())
        assertEquals(3, emptyList.size)
        assertEquals(listOf(1, 2, 3), emptyList)
    }

    @Test
    fun swap() {
        val strings = mutableListOf("foo", "bar", "foo", "baz")
        strings.swap(0, 2)
        assertEquals(mutableListOf("foo", "bar", "foo", "baz"), strings)
        strings.swap(1, 3)
        assertEquals(mutableListOf("foo", "baz", "foo", "bar"), strings)
    }

}
