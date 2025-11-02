package org.github.clnnn

fun quickSort(arr: IntArray): IntArray {
    if (arr.size <= 1) return arr

    val pivot = arr[arr.size / 2]
    val left = arr.filter { it < pivot }.toIntArray()
    val middle = arr.filter { it == pivot }.toIntArray()
    val right = arr.filter { it > pivot }.toIntArray()

    // Recursively apply quickSort to left and right sub-arrays and concatenate results
    return quickSort(left) + middle + quickSort(right)
}