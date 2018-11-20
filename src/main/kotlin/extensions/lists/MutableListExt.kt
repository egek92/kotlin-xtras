package extensions.lists

import java.util.Collections

/**
 * MutableList extensions
 */

/**
 * Add an element to the beginning of the list.
 */
fun <E> MutableList<E>.prepend(element: E) = add(0, element)

/**
 * Inserts all of the elements in the specified [Collection] [elements] into this list
 * at the start of the list.
 *
 * @return `true` if the list was changed as the result of the operation.
 */
fun <E> MutableList<E>.prependAll(elements: Collection<E>): Boolean = addAll(0, elements)

/**
 * Add an element to the end of the list.
 */
fun <E> MutableList<E>.append(element: E): Boolean = add(element)

/**
 * Inserts all of the elements in the specified collection [elements] into this list at the end of the list.
 * @return `true` if the list was changed as the result of the operation.
 */
fun <E> MutableList<E>.appendAll(elements: Collection<E>): Boolean = addAll(elements)

/**
 * **Mutating** - Swap the elements at indices [i] and [j].
 */
fun <T> MutableList<T>.swap(i: Int, j: Int) = Collections.swap(this, i, j)