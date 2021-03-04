package algorithms.sorting

class SelectionSortAlgorithm : ComparingSortingAlgorithm {

    override fun <T> sortInPlace(list: MutableList<T>, comparator: Comparator<T>) {
        val lastElementIdx = list.size - 1
        for(firstUnsortedIdx in 0 until lastElementIdx) {
            val idxOfSmallestRemainingUnsortedElement = searchIdxOfSmallestElementInListStartingAtIdx(list, firstUnsortedIdx, comparator)

            list.swap(firstUnsortedIdx, idxOfSmallestRemainingUnsortedElement)
        }
    }

    override fun <T> sort(list: List<T>, comparator: Comparator<T>): List<T> = ArrayList(list).also { sortInPlace(it, comparator) }

    private fun <T> searchIdxOfSmallestElementInListStartingAtIdx(
        list: List<T>,
        startingIdx: Int,
        comparator: Comparator<T>
    ): Int {
        var smallestElementIdx = startingIdx
        var smallestElement = list[smallestElementIdx]

        for (idx in smallestElementIdx + 1 until list.size) {
            if (comparator.compare(list[idx], smallestElement) < 0) {
                smallestElementIdx = idx
                smallestElement = list[smallestElementIdx]
            }
        }
        return smallestElementIdx
    }
}
