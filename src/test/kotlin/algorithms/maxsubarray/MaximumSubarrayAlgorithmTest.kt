package algorithms.maxsubarray

import algorithms.maxsubarray.MaximumSubarrayAlgorithm.Result
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal abstract class MaximumSubarrayAlgorithmTest {

    private lateinit var algorithm: MaximumSubarrayAlgorithm<Int>

    abstract fun createAlgorithm(): MaximumSubarrayAlgorithm<Int>

    @BeforeEach
    fun setUp() {
        algorithm = createAlgorithm()
    }

    @Nested
    inner class GivenOnlyZeros {

        @Test
        fun shouldReturnFirstElementResult() {
            val actualResult = algorithm.findMaximumSubarrayIn(arrayOf(0,0,0,0,0))

            val expectedResult = Result(0, 0, 0)
            assertEquals(expectedResult, actualResult)
        }
    }

    @Nested
    inner class GivenBookTestCase {

        @Test
        fun shouldReturnBookResult() {
            val list: Array<Int> = arrayOf(13,-3,-25,-20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7)

            val actualResult = algorithm.findMaximumSubarrayIn(list)

            val expectedResult = Result(7,10,43)
            assertEquals(expectedResult, actualResult)
        }
    }


    @Nested
    inner class GivenEmptyArray {

        @Test
        fun shouldThrowIllegalArgumentException() {
            assertThrows<IllegalArgumentException> { algorithm.findMaximumSubarrayIn(arrayOf()) }
        }
    }
}
