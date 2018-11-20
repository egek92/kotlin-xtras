package extensions.iterable

/** Return the first item in the collection (or null if empty) */
val <T> Iterable<T>.head: T? get() = firstOrNull()
/** Return all items in the collection except for the first (empty if size < 2) */
val <T> Iterable<T>.tail: List<T> get() = drop(1)
/**
 * @see head
 * @see tail
 */
val <T> Iterable<T>.headAndTail: Pair<T?, List<T>> get() = (head to tail)

/**
 * Return a map where for each entry, the key is an element in the collection, and
 * its value is the number of occurrences of the element in the collection.
 */
fun <T> Iterable<T>.frequencyMap(): Map<T, Int> = groupingBy { it }.eachCount()
