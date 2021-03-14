package algorithms.matrixmultiplication

interface SquareMatrixMultiplyAlgorithm<T> {
    fun multiply(a: Matrix<T>, b: Matrix<T>) : Matrix<T>
}
