package algorithms.matrixmultiplication

import kotlin.math.floor
import kotlin.math.max
import kotlin.math.sqrt

class SquareMatrix<T>(private val content: MutableList<MutableList<T>>) {

    init {
        val size = content.size
        for (row in content) {
            if (row.size != size) {
                throw IllegalArgumentException("Given array is not a square matrix")
            }
        }
    }

    val size: Int = content.size

    operator fun get(idx: Int): List<T> = content[idx]

    fun set(row: Int, column: Int, value: T) {
        content[row][column] = value
    }

    companion object Factory {

        fun <T> of(vararg elements: T): SquareMatrix<T> {
            val sqrtOfSizeDouble = sqrt(elements.size.toDouble())
            if (sqrtOfSizeDouble != floor(sqrtOfSizeDouble)) {
                throw IllegalArgumentException("Number of given elements is not a square number")
            }
            val sqrtOfSize = sqrtOfSizeDouble.toInt()


            val content = mutableListOf<MutableList<T>>()
            for (i in 0 until sqrtOfSize) {
                val row = mutableListOf<T>()
                for (j in 0 until sqrtOfSize) {
                    row.add(elements[i * sqrtOfSize + j])
                }
                content.add(row)
            }
            return SquareMatrix(content)
        }

        fun <T> ofSize(size: Int, initValue: T): SquareMatrix<T> {
            val rows = mutableListOf<List<T>>()
            for (i in 0 until size) {
                val row = ArrayList<T>()
                for (j in 0 until size) {
                    row.add(initValue)
                }
                rows.add(row)
            }
            return SquareMatrix(rows.map(List<T>::toMutableList).toMutableList())
        }
    }

    override fun toString(): String {
        val maxLengths = IntArray(content[0].size)
        for (row in content) {
            for (columnIdx in row.indices) {
                maxLengths[columnIdx] = max(maxLengths[columnIdx], row[columnIdx].toString().length)
            }
        }

        val result = StringBuilder()
        for (row in content) {
            for (columnIdx in row.indices) {
                val element = row[columnIdx].toString()
                result.append(String.format("%-" + (maxLengths[columnIdx] + 2) + "s", element))
            }
            result.append("\n")
        }
        return result.toString()
    }

    override fun equals(other: Any?) = other is SquareMatrix<*> && other.content == content

    override fun hashCode() = content.hashCode()
}
