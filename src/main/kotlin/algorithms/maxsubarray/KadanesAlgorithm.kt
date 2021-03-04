package algorithms.maxsubarray

/**
 * Implementation of Kadane's algorithm for the Maximum Subarray Problem.
 * The running time is O(n), where n is the size of the array.
 *
 * Normally the Maximum Subarray Problem is limited to numbers.
 * But I was able to extend it to other structures too (for this algorithm).
 *
 * The structure must satisfy following conditions:
 * - There is a closed binary operation "plus" on the underlying set.
 * - There is a designated neutral element "zero" in the underlying set.
 * - There is a total order on the underlying set.
 *
 * A structure with a closed binary operation and a neutral element is called Monoid.
 * Additionally there must be a total order over the underlying set. => Comparable
 */
class KadanesAlgorithm<T: Comparable<T>>(private val monoid: Monoid<T>) : MaximumSubarrayAlgorithm<T> {

    private val zero: T
        get() = monoid.zero()

    private operator fun T.plus(t: T): T = monoid.add(this, t)

    override fun findMaximumSubarrayIn(array: Array<T>): MaximumSubarrayAlgorithm.Result<T> {
        if (array.isEmpty()) {
            throw IllegalArgumentException("Array must contain at least one element")
        }

        var maxSum = zero
        var maxStartIdx = 0
        var maxEndIdx = 0
        var currentSum = zero
        var currentStartIdx = 0
        array.forEachIndexed { idx, element ->
            if (currentSum <= zero) {
                currentStartIdx = idx
                currentSum = zero
            }
            currentSum += element

            if (currentSum > maxSum) {
                maxSum = currentSum
                maxStartIdx = currentStartIdx
                maxEndIdx = idx
            }
        }

        return MaximumSubarrayAlgorithm.Result(maxStartIdx, maxEndIdx, maxSum)
    }

    interface Monoid<T> {
        fun add(first: T, second: T): T
        fun zero(): T
    }
}
