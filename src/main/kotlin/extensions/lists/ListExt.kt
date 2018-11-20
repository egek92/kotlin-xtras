package extensions.lists

/**
 * List extensions
 */

/**
 * Returns views of the first and second halves of the list.
 * If the list is odd-sized, the first half is larger.
 */
val <T> List<T>.halves: Pair<List<T>, List<T>>
    get() = when (size) {
        0 -> Pair(emptyList(), emptyList())
        else -> slice(0..lastIndex / 2) to slice((lastIndex / 2) + 1..lastIndex)
    }

/**
 * Return a map where for each entry, the key is an element in the list, and
 * its value is a list of the indices at which the element is found in the list.
 */
fun <T> List<T>.valueToIndicesMap(): Map<T, List<Int>> = withIndex()
    .groupBy(keySelector = { it.value }, valueTransform = { it.index })

/**
 * Return a map where for each entry the key is the `element: T` and
 * its value is the index at which it was found.
 *
 * **Warning**: This is intended for lists that do not contain duplicates.
 * If the list contains duplicates, use [valueToIndicesMap] instead.
 * If there are duplicates, the value for the element will be the last
 * index at which the key appears.
 *
 * @see valueToIndicesMap
 */
fun <T> List<T>.valueToIndexMap(): Map<T, Int> = withIndex()
    .associateBy(keySelector = { it.value }, valueTransform = { it.index })

fun <T : Comparable<T>> List<T>.isSorted(): Boolean = zipWithNext().all { (a, b) -> b >= a }
fun <T : Comparable<T>> List<T>.isSortedDescending(): Boolean = zipWithNext().all { (a, b) -> b <= a }

/* 2D Lists: List<List<T>> */
val <T> List<List<T>>.rows: Int get() = size
val <T> List<List<T>>.columns: Int get() = if (isEmpty()) 0 else this[0].size

val <T> List<List<T>>.rowRange: IntRange get() = 0..lastIndex
val <T> List<List<T>>.columnRange: IntRange get() = if (isEmpty()) 0..0 else 0..this[0].lastIndex

val <T> List<List<T>>.lastRow: Int get() = lastIndex
val <T> List<List<T>>.lastColumn: Int get() = if (isEmpty()) -1 else this[0].lastIndex

/* Tuples */
fun <T> List<T>.toPair(): Pair<T, T> {
    require(size == 2) { "List must have size 2 to convert to Pair" }
    return Pair(this[0], this[1])
}

fun <T> List<T>.toTriple(): Triple<T, T, T> {
    require(size == 3) { "List must have size 3 to convert to Triple" }
    return Triple(this[0], this[1], this[2])
}
