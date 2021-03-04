package algorithms.maxsubarray

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class DivideAndConquerMaximumSubarrayAlgorithmTest {

    private lateinit var algorithm: DivideAndConquerMaximumSubarrayAlgorithm<Int>

    @BeforeEach
    fun setUp() {
        algorithm = DivideAndConquerMaximumSubarrayAlgorithm(Int::plus)
    }

    @Nested
    inner class GivenOnlyZeros {

        @Test
        fun shouldReturnFirstElementResult() {
            val actualResult = algorithm.findMaximumSubarrayIn(arrayOf(0,0,0,0,0))

            val expectedResult = algorithm.Result(0, 0, 0)
            assertEquals(expectedResult, actualResult)
        }
    }

    @Nested
    inner class GivenBookTestCase {

        @Test
        fun shouldReturnBookResult() {
            val list: Array<Int> = arrayOf(13,-3,-25,-20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7)

            val actualResult = algorithm.findMaximumSubarrayIn(list)

            val expectedResult = algorithm.Result(7,10,43)
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
