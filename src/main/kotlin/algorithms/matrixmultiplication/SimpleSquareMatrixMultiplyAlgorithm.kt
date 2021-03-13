package algorithms.matrixmultiplication

class SimpleSquareMatrixMultiplyAlgorithm<T>(private val world: World<T>) : SquareMatrixMultiplyAlgorithm<T> {

    override fun multiply(a: SquareMatrix<T>, b: SquareMatrix<T>) : SquareMatrix<T> {
        if (a.size != b.size) {
            throw IllegalArgumentException("Matrices are not of equal size")
        }
        val n = a.size

        val c = SquareMatrix.ofSize(n, world.zero())
        for (i in 0 until n) {
            for (j in 0 until n) {
                for (k in 0 until n) {
                    c.set(i, j, c[i][j] + a[i][k] * b[k][j])
                }
            }
        }
        return c
    }

    private operator fun T.times(other: T) = world.multiply(this, other)
    private operator fun T.plus(other: T) = world.add(this, other)

}
