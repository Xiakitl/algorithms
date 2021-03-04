package algorithms.sorting

class InsertionSortAlgorithm : ComparingSortingAlgorithm {

    override fun <T> sortInPlace(list: MutableList<T>, comparator: Comparator<T>) {
        for (lastSortedIdx in 0 until list.size-1) {

            for (i in lastSortedIdx downTo 0) {
                val nextElementLessThanCurrent = comparator.compare(list[i+1], list[i]) < 0
                if (nextElementLessThanCurrent) {
                    list.swap(i, i+1)
                }
            }
        }
    }

    override fun <T> sort(list: List<T>, comparator: Comparator<T>): List<T> = ArrayList(list).also { sortInPlace(it, comparator) }
}
