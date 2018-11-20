package extensions.chars

/**
 * Char extensions
 */

/**
 * Returns the [Char]'s ASCII (American Standard Code for Information Interchange) value.
 * This is equivalent to calling `Char.toInt()`
 */
val Char.asciiValue: Int get() = toInt()

/**
 * Returns the decimal value (rather than the ASCII value) if this is a digit.
 * Note: `'0'.toInt()` returns 48; `'0'.numericValue` returns 0.
 * Equivalent of Java's [Character.getNumericValue] if the [Char] is
 * a digit, otherwise throws an [IllegalArgumentException].
 * @throws IllegalArgumentException if the receiver is not a digit ('0'..'9').
 */
val Char.numericValue: Int
    get() {
        require(isDigit()) { "Cannot get decimal value of non-digit Char." }
        return toString().toInt()
    }

/**
 * Checks whether the Char is a vowel.
 * Note: 'y' isn't not considered a vowel here.
 */
val Char.isVowel: Boolean get() = toLowerCase() in "aeiou"

/**
 * Returns the Char with its case inverted.
 * If not a letter, behavior undefined (but will likely return the same Char)
 */
fun Char.invertCase(): Char = if (isLowerCase()) toUpperCase() else toLowerCase()
