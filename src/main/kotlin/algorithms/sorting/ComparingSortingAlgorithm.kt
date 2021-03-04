package algorithms.sorting

/**
 * Sorting algorithm, which uses comparisons for algorithms.sorting.
 */
interface ComparingSortingAlgorithm {

    /**
     * Sorts the given [list] in-place, using [comparator] for determining the size relation between two values.
     *
     * @param list mutable list of elements which should be sorted in-place
     * @param comparator comparison strategy to determine the size relation between two values from [list]
     */
    fun <T> sortInPlace(list: MutableList<T>, comparator: Comparator<T>)

    /**
     * Convenience function for lists, whose items are [Comparables][Comparable]
     */
    fun <T : Comparable<T>> sortInPlace(list: MutableList<T>): Unit = sortInPlace(list) { a, b -> a.compareTo(b)}

    /**
     * Sorts the given [list] out-of-place, using [comparator] for determining the size relation between two values.
     *
     * @param list mutable list of elements which should be sorted out-of-place
     * @param comparator comparison strategy to determine the size relation between two values from [list]
     */
    fun <T> sort(list: List<T>, comparator: Comparator<T>): List<T>

    /**
     * Convenience function for lists, whose items are [Comparables][Comparable]
     */
    fun <T : Comparable<T>> sort(list: List<T>): List<T> = sort(list) { a, b -> a.compareTo(b) }
}
