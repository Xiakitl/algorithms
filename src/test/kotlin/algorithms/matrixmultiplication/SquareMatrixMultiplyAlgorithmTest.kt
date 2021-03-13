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
                val firstMatrix = SquareMatrix.of(5,8,3,8)
                val secondMatrix = SquareMatrix.of(3,8,8,9)

                val result = algorithm.multiply(firstMatrix, secondMatrix)

                println(result.toString())

                val expectedResult = SquareMatrix.of(79,112,73,96)
                assertEquals(expectedResult, result)
            }
        }
    }
}
