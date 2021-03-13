package algorithms.matrixmultiplication

interface SquareMatrixMultiplyAlgorithm<T> {
    fun multiply(a: SquareMatrix<T>, b: SquareMatrix<T>) : SquareMatrix<T>
}
