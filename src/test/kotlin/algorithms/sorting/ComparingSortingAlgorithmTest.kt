package algorithms.sorting

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.Duration

internal abstract class ComparingSortingAlgorithmTest {

    private lateinit var algorithm: ComparingSortingAlgorithm

    @BeforeEach
    fun setUp() {
        algorithm = createAlgorithm()
    }

    abstract fun createAlgorithm(): ComparingSortingAlgorithm

    @Nested
    inner class SortInPlace {

        @Test
        fun simpleTest() {
            val list = mutableListOf(1, 9, 4, -99, Int.MAX_VALUE, Int.MIN_VALUE, 0, 42)

            algorithm.sortInPlace(list)

            assertThat(list, contains(Int.MIN_VALUE, -99, 0, 1, 4, 9, 42, Int.MAX_VALUE))
        }
    }

    @Nested
    inner class Sort {

        @Nested
        inner class GivenEmptyList {

            @Test
            fun shouldReturnEmptyList() {
                val result = algorithm.sort(emptyList<Int>())

                assertThat(result, hasSize(0))
            }
        }

        @Nested
        inner class GivenListWithOneElement {

            @Test
            fun shouldReturnListWithSameElement() {
                val element = Duration.ofSeconds(123)

                val result = algorithm.sort(listOf(element))

                assertThat(result, contains(result[0]))
            }
        }

        @Nested
        inner class GivenListWithMultipleElement {

            @Test
            fun shouldReturnSortedList() {
                val originalList = listOf(1, 9, 4, -99, Int.MAX_VALUE, Int.MIN_VALUE, 0, 42)

                val sortedList = algorithm.sort(originalList)

                assertThat(sortedList, contains(Int.MIN_VALUE, -99, 0, 1, 4, 9, 42, Int.MAX_VALUE))
            }

            @Test
            fun shouldNotChangeOriginalList() {
                val originalList = listOf(1, 9, 4, -99, Int.MAX_VALUE, Int.MIN_VALUE, 0, 42)

                algorithm.sort(originalList)

                assertThat(originalList, contains(1, 9, 4, -99, Int.MAX_VALUE, Int.MIN_VALUE, 0, 42))
            }
        }
    }
}
