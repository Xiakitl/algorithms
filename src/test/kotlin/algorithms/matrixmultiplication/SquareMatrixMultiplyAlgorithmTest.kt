package algorithms.matrixmultiplication

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal abstract class SquareMatrixMultiplyAlgorithmTest {

    private val world = World.IntWorld

    private lateinit var algorithm: SquareMatrixMultiplyAlgorithm<Int>

    @BeforeEach
    fun setUp() {
        algorithm = createAlgorithm()
    }

    abstract fun createAlgorithm(): SquareMatrixMultiplyAlgorithm<Int>

    @Nested
    inner class Multiply {

        @Nested
        inner class WithSameSizeMatrices {

            @Test
            fun shouldReturnMultiplicationResult() {
                val firstMatrix = Matrix.ofRows(Matrix.Row.of(5,8), Matrix.Row.of(3,8))
                val secondMatrix = Matrix.ofRows(Matrix.Row.of(3,8), Matrix.Row.of(8,9))

                val result = algorithm.multiply(firstMatrix, secondMatrix)

                println(result.toString())

                val expectedResult = Matrix.ofRows(Matrix.Row.of(79,112), Matrix.Row.of(73,96))
                assertEquals(expectedResult, result)
            }
        }
    }
}
