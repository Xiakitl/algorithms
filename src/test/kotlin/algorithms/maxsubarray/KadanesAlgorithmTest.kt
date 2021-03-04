package algorithms.maxsubarray

internal class KadanesAlgorithmTest : MaximumSubarrayAlgorithmTest() {

    override fun createAlgorithm(): MaximumSubarrayAlgorithm<Int> = KadanesAlgorithm(object: KadanesAlgorithm.Monoid<Int> {
        override fun add(first: Int, second: Int): Int = first + second
        override fun zero(): Int = 0

    })
}
