package extensions.arrays

/**
 * `BooleanMatrix` = `Array<BooleanArray>`
 */
typealias BooleanMatrix = Array<BooleanArray>

/** Returns the number of rows in the matrix. */
val BooleanMatrix.rows: Int get() = size
/** Returns the number of columns in the matrix. */
val BooleanMatrix.columns: Int get() = if (isEmpty()) 0 else this[0].size

/** Returns the range of valid row indices for this matrix. */
val BooleanMatrix.rowIndices: IntRange get() = 0..lastIndex
/** Returns the range of valid column indices for this matrix. */
val BooleanMatrix.columnIndices: IntRange get() = if (isEmpty()) IntRange.EMPTY else 0..this[0].lastIndex

/** Returns the index of the last row in the matrix or -1 if the matrix is empty. */
val BooleanMatrix.lastRowIndex: Int get() = lastIndex
/** Returns the index of the last column in the matrix or -1 if the matrix is empty. */
val BooleanMatrix.lastColumnIndex: Int get() = if (isEmpty()) -1 else this[0].lastIndex

/** Return `true` if i & j represent valid row & column indices in the matrix */
fun BooleanMatrix.isValidPosition(i: Int, j: Int): Boolean = isNotEmpty() && i in indices && j in this[0].indices

/**
 * Creates an [m] x [n] [BooleanMatrix] where all values are `false`.
 * @param m The number of rows
 * @param n The number of columns
 */
fun booleanMatrixWithSize(m: Int, n: Int): BooleanMatrix = Array(m) { BooleanArray(n) }

/**
 * Create an BooleanMatrix from the provided lists.
 * @param rows The rows that will be added to the BooleanMatrix
 * @throws IllegalArgumentException if [rows] is empty.
 * @throws IllegalArgumentException if any of the rows differ in length, or are empty.
 */
fun buildBooleanMatrix(vararg rows: List<Boolean>): BooleanMatrix {
    require(rows.isNotEmpty()) { "Cannot create BooleanMatrix with no elements." }
    require(rows.all { row -> row.size == rows[0].size && row.isNotEmpty() }) {
        "All rows must have the same length, and cannot be empty"
    }
    val m = rows.size
    val n = rows[0].size

    val matrix = booleanMatrixWithSize(m, n)
    rows.forEachIndexed { i, row ->
        row.forEachIndexed { j, value ->
            matrix[i][j] = value
        }
    }

    return matrix
}

/**
 * Convert the [BooleanMatrix] to a 2D [List].
 */
fun BooleanMatrix.toList(): List<List<Boolean>> = fold(mutableListOf()) { acc, intArr ->
    acc.apply {
        acc.add(intArr.toList())
    }
}

/**
 * Create an [BooleanMatrix] from a 2D [List].
 */
fun List<List<Boolean>>.toMatrix(): BooleanMatrix = Array(size = size, init = { i -> this[i].toBooleanArray() })

