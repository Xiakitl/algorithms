package algorithms.matrixmultiplication

class SimpleSquareMatrixMultiplyAlgorithm<T>(private val world: World<T>) : SquareMatrixMultiplyAlgorithm<T> {

    override fun multiply(a: Matrix<T>, b: Matrix<T>) : Matrix<T> {
        if (!(a.isSquare && b.isSquare)) {
            throw IllegalArgumentException("At least one of the matrices are not of square size")
        }

        if (a.size != b.size) {
            throw IllegalArgumentException("Matrices are not of equal size")
        }
        val n = a.width

        val c = Matrix.withSize<T>(n,n, world.zero())
        for (i in 0 until n) {
            for (j in 0 until n) {
                for (k in 0 until n) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j]
                }
            }
        }
        return c
    }

    private operator fun T.times(other: T) = world.multiply(this, other)
    private operator fun T.plus(other: T) = world.add(this, other)

}
