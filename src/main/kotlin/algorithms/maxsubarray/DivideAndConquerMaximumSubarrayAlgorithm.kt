package algorithms.maxsubarray

import algorithms.maxsubarray.MaximumSubarrayAlgorithm.Result
import java.util.function.BinaryOperator

/**
 * Algorithm that will find a maximum-subarray in a given array.
 * The algorithm uses the divide-and-conquer approach. The overall running time is O(n log n), where n is the size of the array.
 *
 * (p.68ff)
 */
class DivideAndConquerMaximumSubarrayAlgorithm<T: Comparable<T>>(private val sumFunction: BinaryOperator<T>) :
    MaximumSubarrayAlgorithm<T> {

    override fun findMaximumSubarrayIn(array: Array<T>): Result<T> {
        if (array.isEmpty()) {
            throw IllegalArgumentException("Array must contain at least one element")
        }

        return findMaximumSubarray(array, 0, array.size-1)
    }

    private fun findMaximumSubarray(array: Array<T>, low: Int, high: Int): Result<T> {
        if (high == low) {
            return Result(low, high, array[low])
        }

        val mid = (low+high)/2
        val leftResult = findMaximumSubarray(array, low, mid)
        val rightResult = findMaximumSubarray(array, mid+1, high)
        val crossResult = findMaximumCrossingSubarray(array, low, mid, high)

        return maxOf(leftResult, rightResult, crossResult)
    }

    private fun findMaximumCrossingSubarray(array: Array<T>, low: Int, mid: Int, high: Int): Result<T> {
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

    private operator fun T.plus(t: T): T {
        return sumFunction.apply(this, t)
    }
}
