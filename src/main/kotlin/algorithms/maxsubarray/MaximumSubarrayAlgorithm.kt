package algorithms.maxsubarray

interface MaximumSubarrayAlgorithm<T : Comparable<T>> {
    fun findMaximumSubarrayIn(array: Array<T>): Result<T>

    class Result<R: Comparable<R>>(val inclStartIdx: Int, val inclEndIdx: Int, val sum: R) : Comparable<Result<R>> {
        override fun compareTo(other: Result<R>) = sum.compareTo(other.sum)
        override fun equals(other: Any?): Boolean {
            return other is Result<*> && inclStartIdx == other.inclStartIdx && inclEndIdx == other.inclEndIdx && sum == other.sum
        }
        override fun toString() = "[$inclStartIdx,$inclEndIdx]=$sum"

        override fun hashCode(): Int {
            var result = inclStartIdx
            result = 31 * result + inclEndIdx
            result = 31 * result + sum.hashCode()
            return result
        }
    }
}
