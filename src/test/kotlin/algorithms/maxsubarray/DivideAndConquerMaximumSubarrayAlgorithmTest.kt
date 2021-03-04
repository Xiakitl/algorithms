package algorithms.maxsubarray

internal class DivideAndConquerMaximumSubarrayAlgorithmTest : MaximumSubarrayAlgorithmTest() {

    override fun createAlgorithm(): MaximumSubarrayAlgorithm<Int> = DivideAndConquerMaximumSubarrayAlgorithm(Int::plus)
}
