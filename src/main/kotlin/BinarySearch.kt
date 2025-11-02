package org.github.clnnn

fun binarySearch(arr: IntArray, searchValue: Int): Int {
    var low = 0
    var high = arr.lastIndex

    while (low <= high) {
        val mid = (low + high) / 2
        when {
            searchValue == arr[mid] -> return mid
            searchValue < arr[mid] -> high = mid - 1
            searchValue > arr[mid] -> low = mid + 1
        }
    }

    return -1
}