package algorithms.matrixmultiplication

internal class DivideAndConquerSquareMatrixMultiplyAlgorithmTest : SquareMatrixMultiplyAlgorithmTest() {

    override fun createAlgorithm(): SquareMatrixMultiplyAlgorithm<Int> {
        return DivideAndConquerSquareMatrixMultiplyAlgorithm(World.IntWorld)
    }
}
