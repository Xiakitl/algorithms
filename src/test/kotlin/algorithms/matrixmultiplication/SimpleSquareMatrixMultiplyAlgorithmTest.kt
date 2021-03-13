package algorithms.matrixmultiplication

internal class SimpleSquareMatrixMultiplyAlgorithmTest : SquareMatrixMultiplyAlgorithmTest() {

    override fun createAlgorithm(): SquareMatrixMultiplyAlgorithm<Int> {
        return SimpleSquareMatrixMultiplyAlgorithm(World.IntWorld)
    }
}
