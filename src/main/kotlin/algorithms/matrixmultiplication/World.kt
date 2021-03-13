package algorithms.matrixmultiplication

interface World<T> {
    fun add(first: T, other: T): T
    fun multiply(first: T, other: T): T
    fun zero(): T

    object IntWorld: World<Int> {
        override fun add(first: Int, other: Int): Int = first + other
        override fun multiply(first: Int, other: Int): Int = first * other
        override fun zero(): Int = 0
    }
}
