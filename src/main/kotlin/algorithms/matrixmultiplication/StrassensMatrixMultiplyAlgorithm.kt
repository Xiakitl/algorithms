package algorithms.matrixmultiplication

class StrassensMatrixMultiplyAlgorithm<T>(private val world: World<T>) : SquareMatrixMultiplyAlgorithm<T> {

    override fun multiply(a: Matrix<T>, b: Matrix<T>): Matrix<T> {
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

        val s1 = b12 - b22
        val s2 = a11 + a12
        val s3 = a21 + a22
        val s4 = b21 - b11
        val s5 = a11 + a22
        val s6 = b11 + b22
        val s7 = a12 - a22
        val s8 = b21 + b22
        val s9 = a11 - a21
        val s10 = b11 + b12

        val p1 = a11 * s1
        val p2 = s2 * b22
        val p3 = s3 * b11
        val p4 = a22 * s4
        val p5 = s5 * s6
        val p6 = s7 * s8
        val p7 = s9 * s10

        val c11 = p5 + p4 - p2 + p6
        val c12 = p1 + p2
        val c21 = p3 + p4
        val c22 = p5 + p1 - p3 - p7

        return c11.withAppendedRows(c21).withAppendedColumns(c12.withAppendedRows(c22))
    }

    private operator fun T.times(other: T) = world.multiply(this, other)
    private operator fun Matrix<T>.plus(other: Matrix<T>) = this.combineWith(other, world::add)
    private operator fun Matrix<T>.minus(other: Matrix<T>) = this.combineWith(other, world::minus)
    private operator fun Matrix<T>.times(other: Matrix<T>) = multiply(this, other)
}
