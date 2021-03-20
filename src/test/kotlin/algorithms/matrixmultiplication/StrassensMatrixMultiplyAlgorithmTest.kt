package algorithms.matrixmultiplication

internal class StrassensMatrixMultiplyAlgorithmTest : SquareMatrixMultiplyAlgorithmTest() {

    override fun createAlgorithm(): SquareMatrixMultiplyAlgorithm<Int> {
        return StrassensMatrixMultiplyAlgorithm(World.IntWorld)
    }
}
