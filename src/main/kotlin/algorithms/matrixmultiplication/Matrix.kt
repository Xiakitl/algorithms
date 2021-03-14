package algorithms.matrixmultiplication

import kotlin.math.max

class Matrix<T> private constructor(rows: List<Row<T>>) {

    init {
        if (rows.stream().map(Row<T>::size).distinct().count() != 1L) {
            throw IllegalArgumentException("Rows don't have the same size")
        }
    }

    private val rows: MutableList<Row<T>> = ArrayList(rows)

    val size: Int
        get() = height * width

    val height: Int
        get() = rows.size

    val width: Int
        get() = if (rows.size > 0) { rows[0].size } else { 0 }

    val isSquare: Boolean
        get() = height == width

    operator fun get(idx: Int) = rows[idx]

    fun withAdditionalRows(rows: List<Row<T>>): Matrix<T> {
        val newRows = ArrayList<Row<T>>(this.rows.size + rows.size)
        newRows.addAll(this.rows)
        newRows.addAll(rows)
        return Matrix(newRows)
    }

    fun subMatrix(firstColumn: Int, lastColumn: Int, firstRow: Int, lastRow: Int): Matrix<T> {
        val newRows = mutableListOf<Row<T>>()
        for (idx in firstRow .. lastRow) {
            newRows.add(newRows[idx].subRow(firstColumn, lastColumn))
        }
        return Matrix(newRows)
    }

    override fun equals(other: Any?) = (other is Matrix<*> && rows == other.rows)

    override fun hashCode() = rows.hashCode()

    override fun toString(): String {
        val maxLengths = IntArray(width)
        for (row in rows) {
            for (columnIdx in row.indices) {
                maxLengths[columnIdx] = max(maxLengths[columnIdx], row[columnIdx].toString().length)
            }
        }

        val result = StringBuilder()
        for (row in rows) {
            for (columnIdx in row.indices) {
                val element = row[columnIdx].toString()
                result.append(String.format("%-" + (maxLengths[columnIdx] + 2) + "s", element))
            }
            result.append("\n")
        }
        return result.toString()
    }

    companion object {
        fun <T> ofRows(vararg rows: Row<T>) = ofRows(listOf(*rows))
        fun <T> ofRows(rows: List<Row<T>>) = Matrix(rows)
        fun <T> withSize(width: Int, height: Int, defaultValue: T): Matrix<T> {
            val rows = ArrayList<Row<T>>(height)
            for (i in 0 until height) rows.add(Row.ofSize<T>(width, defaultValue))
            return ofRows(rows)
        }
    }

    class Row<T> private constructor(items: List<T>) {

        private val items: MutableList<T> = ArrayList(items)

        val indices: IntRange = IntRange(0, size-1)

        val size: Int
            get() = items.size


        operator fun get(idx: Int) = items[idx]

        operator fun set(idx: Int, value: T) {
            items[idx] = value
        }

        fun subRow(firstColumn: Int, lastColumn: Int) = Row(items.subList(firstColumn, lastColumn + 1))

        override fun equals(other: Any?) = (other is Row<*> && items == other.items)

        override fun hashCode() = items.hashCode()

        companion object {
            fun <T> of(vararg items: T) = of(listOf(*items))

            fun <T> of(items: List<T>) = Row<T>(items)

            fun <T> ofSize(size: Int, value: T): Row<T> {
                val elements = ArrayList<T>()
                for (i in 0 until size) elements.add(value)
                return of(elements)
            }
        }


    }

}
