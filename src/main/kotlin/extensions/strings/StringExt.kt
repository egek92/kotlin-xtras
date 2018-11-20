package extensions.strings

/**
 * String extensions.
 */

/** True if every character in the String is an uppercase letter. */
val String.isAllCaps: Boolean get() = all { it.isUpperCase() }

/** True if every character in the String is a lowercase letter. */
val String.isAllLowercase: Boolean get() = all { it.isLowerCase() }

val String.head: Char? get() = firstOrNull()
val String.tail: String get() = drop(1)
val String.headAndTail: Pair<Char?, String> get() = (head to tail)

/**
 * Return `true` if the string is a palindrome (same forwards and backwards).
 * Does not ignore non-letter or digit chars.
 */
fun String.isPalindrome(): Boolean {
    var i = 0
    var j = lastIndex
    while (i < j) {
        if (this[i++] != this[j--]) return false
    }

    return true
}

/**
 * Returns true if the [String] is an anagram of [other] (i.e., a permutation of the letters in this
 * String could be rearranged to form the [other] string).
 */
fun String.isAnagramOf(other: String): Boolean = toSortedChars() == other.toSortedChars()

/**
 * Return a String with the elements in the specified range reversed.
 * @throws StringIndexOutOfBoundsException if [indexRange] not in `0..lastIndex`
 */
fun String.reverseCharsInRange(indexRange: IntRange): String =
    replaceRange(indexRange, substring(indexRange).reversed())

/**
 * Return a string with the Chars at the given indices swapped.
 */
fun String.swappingCharsAt(i: Int, j: Int): String =
    if (i == j) this
    else substring(0, i) + this[j] + substring(i + 1, j) + this[i] + substring(j + 1)

/**
 * Returns a map of each character to its frequency.
 * If `caseSensitive`, all keys will be lowercase.
 */
fun String.characterFrequencies(caseSensitive: Boolean = true): Map<Char, Int> =
    groupingBy { if (caseSensitive) it else it.toLowerCase() }.eachCount()

/**
 * Return a map where the entries are (Char -> List of indices containing Char)
 */
fun String.characterIndices(): Map<Char, List<Int>> = withIndex()
    .groupBy(keySelector = { it.value }, valueTransform = { it.index })

/**
 * Return a [List] of the distinct chars in the string (retaining order).
 */
fun String.distinctChars(): List<Char> = toCharArray().distinct()

/**
 * Returns a sorted list of all chars in the string (including chars that appear more than once).
 * Sorts by the Char's Int value (ASCII value).
 * If a string contains whitespace or punctuation characters, the sorted order
 * will be by increasing [ASCII value](https://www.rapidtables.com/code/text/ascii-table.html).
 *
 * @see [toSortedSet] for similar function that returns only unique chars.
 */
fun String.toSortedChars(): List<Char> = toCharArray().sorted()

/**
 * Returns a string containing all the chars in the original string (including duplicates) arranged in
 * sorted order.
 * Sorts by the Char's Int value (ASCII value).
 * If a string contains whitespace or punctuation characters, the sorted order
 * will be by increasing [ASCII value](https://www.rapidtables.com/code/text/ascii-table.html).
 */
fun String.toSortedString(): String = toSortedChars().joinToString("")

/**
 * Returns all (contiguous) substrings of length `0..n`.
 * May contain duplicates.
 *
 * **Time**: `O(n^2)`
 *
 * **Space**: `O(n)`
 */
fun String.substrings(): List<String> {
    val substrings = arrayListOf("")
    for (i in indices) {
        for (j in (i..lastIndex)) {
            substrings += substring(i..j)
        }
    }
    return substrings
}

/**
 * Generates all permutations of the String.
 * Will generate all distinct permutations if the string has duplicate characters.
 *
 * ```
 * "abc".permutations() => ["abc", "acb", "bac", "bca", "cab", "cba"]
 * ```
 */
fun String.permutations(): Set<String> = when (length) {
    0 -> emptySet()
    1 -> setOf(this)
    2 -> setOf(this, reversed())
    else -> {
        val permutations: MutableSet<String> = HashSet()
        for (i in indices) {
            val otherChars = take(i) + drop(i + 1)
            val otherPermutations = otherChars.permutations()
            val newPermutations = otherPermutations.map { this[i] + it }
            permutations.addAll(newPermutations)
        }
        permutations
    }
}

/**
 * Returns a new string with the last occurrence of [oldChar] replaced with [newChar].
 * @param oldChar The Char to replace
 * @param newChar The Char to replace [oldChar] with
 * @param startIndex The index to search backwards from (default: lastIndex)
 * @param ignoreCase if true, either lowercase or uppercase instances of [oldChar] will be replaced (default: false).
 * @return a new string with the last occurrence of [oldChar] replaced with [newChar], or the same String if
 * [oldChar] was not found.
 */
fun String.replaceLast(
    oldChar: Char,
    newChar: Char,
    startIndex: Int = lastIndex,
    ignoreCase: Boolean = false
): String {
    val index = lastIndexOf(char = oldChar, startIndex = startIndex, ignoreCase = ignoreCase)
    return if (index < 0) this else this.replaceRange(index, index + 1, newChar.toString())
}

/**
 * Returns a new string with the last occurrence of [oldValue] replaced with [newValue].
 * @param oldValue The String to replace
 * @param newValue The String to replace [oldValue] with
 * @param startIndex The index to search backwards from (default: lastIndex)
 * @param ignoreCase if true, will replace the last instance of [oldValue] with any combination of casing (default: false).
 * @return a new string with the last occurrence of [oldValue] replaced with [newValue], or the same String if
 * [oldValue] was not found.
 */
fun String.replaceLast(
    oldValue: String,
    newValue: String,
    startIndex: Int = lastIndex,
    ignoreCase: Boolean = false
): String {
    val index = lastIndexOf(string = oldValue, startIndex = startIndex, ignoreCase = ignoreCase)
    return if (index < 0) this else this.replaceRange(index, index + oldValue.length, newValue)
}


/**
 * Returns the [Longest Common Substring](https://en.wikipedia.org/wiki/Longest_common_substring_problem) of
 * [a] and [b].
 *
 * **Note**: there may be multiple substrings with the same length - this returns *one* of the longest substrings from
 * the solution set
 * @return longest common substring
 * @see lcs
 */
fun longestCommonSubstring(a: String, b: String): String {
    val dp = Array(a.length + 1) { IntArray(b.length + 1) }
    var maxLength = 0
    var endIndex = 0

    (1..a.length).forEach { i ->
        (1..b.length).forEach { j ->
            if (a[i - 1] == b[j - 1]) {
                dp[i][j] = 1 + dp[i - 1][j - 1]

                if (dp[i][j] > maxLength) {
                    maxLength = dp[i][j]
                    endIndex = i
                }
            }
        }
    }

    return a.substring((endIndex - maxLength) until endIndex)
}


/**
 * Returns the [Longest Common Subsequence (LCS)](https://en.wikipedia.org/wiki/Longest_common_subsequence_problem) of
 * [a] and [b].
 *
 * **Note**: there may be multiple LCS solutions. This returns *one* of the longest subsequences from
 * the solution set.
 * @return longest common subsequence (LCS)
 * @see longestCommonSubstring
 */
fun lcs(a: String, b: String): String {
    val dp: Array<Array<String>> = Array(a.length + 1) { Array(b.length + 1) { "" } }

    (1..a.length).forEach { i ->
        (1..b.length).forEach { j ->
            if (a[i - 1] == b[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + a[i - 1]
            } else {
                dp[i][j] = setOf(dp[i][j - 1], dp[i - 1][j]).maxBy { it.length }!!
            }
        }
    }

    return dp[a.length][b.length]
}

/**
 * Finds the Levenshtein Distance between [s1] and [s2].
 * [Wikipedia: Edit Distance](https://en.wikipedia.org/wiki/Edit_distance)
 * [Wikipedia: Levenshtein Distance](https://en.wikipedia.org/wiki/Levenshtein_distance)
 * [TutorialHorizon: Edit Distance Problem](https://algorithms.tutorialhorizon.com/dynamic-programming-edit-distance-problem)
 *
 * **Time**: `O(m * n)`
 *
 * **Space**: `O(m * n)`
 */
fun editDistance(s1: String, s2: String): Int {
    val dp = Array(s1.length + 1) { IntArray(s2.length + 1) }

    // If either s1 or s2 is empty, the # of operations is the length of
    // the other string
    for (i in 0..s1.length) dp[i][0] = i
    for (j in 0..s2.length) dp[0][j] = j

    val m = s1.length
    val n = s2.length

    for (i in 1..m) {
        for (j in 1..n) {
            // If last chars match, no additional operations needed vs. s1[:m - 1] & s2[:n - 1]
            // Note: i - 1 & j - 1 because we added rows for 0-length strings in dp table
            if (s1[i - 1] == s2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1]
            } else {
                dp[i][j] = 1 + minOf(
                    dp[i][j - 1],       // insertion
                    dp[i - 1][j],       // deletion
                    dp[i - 1][j - 1]    // substitution
                )
            }
        }
    }

    return dp[m][n]
}