package algorithms.sorting

internal fun <T> MutableList<T>.swap(idxOne: Int, idxTwo: Int) {
    val temp = get(idxOne)
    set(idxOne, get(idxTwo))
    set(idxTwo, temp)
}
