package algorithms.matrixmultiplication

class DivideAndConquerSquareMatrixMultiplyAlgorithm<T>(private val world: World<T>) : SquareMatrixMultiplyAlgorithm<T> {

    override fun multiply(a: Matrix<T>, b: Matrix<T>) : Matrix<T> {
        if (!(a.isSquare && b.isSquare)) {
            throw IllegalArgumentException("At least one of the matrices are not of square size")
        }

        if (a.size != b.size) {
            throw IllegalArgumentException("Matrices are not of equal size")
        }
        val width = a.width

        if (width == 1) {
            return Matrix.withSize<T>(1,1, a[0][0] * b[0][0])
        }
        if (width % 2 == 1) {
            throw IllegalArgumentException("Matrix width is not a power of two")
        }
        val halfWidth = width / 2

        val a11 = a.subMatrix(0, halfWidth-1, 0, halfWidth-1)
        val a12 = a.subMatrix(halfWidth, width-1, 0, halfWidth-1)
        val a21 = a.subMatrix(0, halfWidth-1, halfWidth, width-1)
        val a22 = a.subMatrix(halfWidth, width-1, halfWidth, width-1)

        val b11 = b.subMatrix(0, halfWidth-1, 0, halfWidth-1)
        val b12 = b.subMatrix(halfWidth, width-1, 0, halfWidth-1)
        val b21 = b.subMatrix(0, halfWidth-1, halfWidth, width-1)
        val b22 = b.subMatrix(halfWidth, width-1, halfWidth, width-1)

        val c11 = multiply(a11, b11) + multiply(a12, b21)
        val c12 = multiply(a11, b12) + multiply(a12, b22)
        val c21 = multiply(a21, b11) + multiply(a22, b21)
        val c22 = multiply(a21, b12) + multiply(a22, b22)

        return c11.withAppendedRows(c21).withAppendedColumns(c12.withAppendedRows(c22))
    }

    private operator fun T.times(other: T) = world.multiply(this, other)
    private operator fun T.plus(other: T) = world.add(this, other)

    private operator fun Matrix<T>.plus(other: Matrix<T>) = combineWith(other, world::add)

}
