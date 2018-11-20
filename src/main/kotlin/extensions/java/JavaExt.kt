package extensions.java

import java.util.*

/**
 * Kotlin extensions for Java standard library methods.
 */

/**
 * Returns an immutable list consisting of [n] copies of the
 * specified object.
 *
 * See Java's [Collections.nCopies].
 */
fun <T> T.repeated(n: Int): List<T> = Collections.nCopies(n, this)