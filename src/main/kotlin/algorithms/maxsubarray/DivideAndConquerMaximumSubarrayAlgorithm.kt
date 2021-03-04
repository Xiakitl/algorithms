package algorithms.maxsubarray

import java.util.function.BinaryOperator

/**
 * Algorithm that will find a maximum-subarray in a given array.
 * The algorithm uses the divide-and-conquer approach. The overall running time is O(n log n), where n is the size of the array.
 *
 * (p.68ff)
 */
class DivideAndConquerMaximumSubarrayAlgorithm<T: Comparable<T>>(private val sumFunction: BinaryOperator<T>) {

    fun findMaximumSubarrayIn(array: Array<T>): Result {
        if (array.isEmpty()) {
            throw IllegalArgumentException("Array must contain at least one element")
        }

        return findMaximumSubarray(array, 0, array.size-1)
    }

    private fun findMaximumSubarray(array: Array<T>, low: Int, high: Int): Result {
        if (high == low) {
            return Result(low, high, array[low])
        }

        val mid = (low+high)/2
        val leftResult = findMaximumSubarray(array, low, mid)
        val rightResult = findMaximumSubarray(array, mid+1, high)
        val crossResult = findMaximumCrossingSubarray(array, low, mid, high)

        return maxOf(leftResult, rightResult, crossResult)
    }

    private fun findMaximumCrossingSubarray(array: Array<T>, low: Int, mid: Int, high: Int): Result {
        var maxLeftSum: T? = null
        var maxLeftIdx = 0

        var currentLeftSum: T? = null
        for (i in mid downTo low) {
            currentLeftSum = currentLeftSum?.plus(array[i]) ?: array[i]

            if (maxLeftSum == null || currentLeftSum > maxLeftSum) {
                maxLeftSum = currentLeftSum
                maxLeftIdx = i
            }
        }

        var maxRightSum: T? = null
        var maxRightIdx = 0

        var currentRightSum: T? = null
        for (j in mid+1 .. high) {
            currentRightSum = currentRightSum?.plus(array[j]) ?: array[j]

            if (maxRightSum == null || currentRightSum > maxRightSum) {
                maxRightSum = currentRightSum
                maxRightIdx = j
            }
        }

        return Result(maxLeftIdx, maxRightIdx, maxLeftSum!! + maxRightSum!!)
    }

    inner class Result(val inclStartIdx: Int, val inclEndIdx: Int, val sum: T) : Comparable<Result> {
        override fun compareTo(other: Result) = sum.compareTo(other.sum)
        override fun equals(other: Any?): Boolean {
            return other is DivideAndConquerMaximumSubarrayAlgorithm<*>.Result && inclStartIdx == other.inclStartIdx && inclEndIdx == other.inclEndIdx && sum == other.sum
        }
        override fun toString() = "[$inclStartIdx,$inclEndIdx]=$sum"

        override fun hashCode(): Int {
            var result = inclStartIdx
            result = 31 * result + inclEndIdx
            result = 31 * result + sum.hashCode()
            return result
        }
    }

    private operator fun T.plus(t: T): T {
        return sumFunction.apply(this, t)
    }
}
