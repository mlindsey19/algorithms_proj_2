package sorts

fun insertionSort(a: IntArray) {
    for (index in 1 until a.size) {
        val value = a[index]
        var subIndex = index - 1
        while (subIndex >= 0 && a[subIndex] > value) {
            a[subIndex + 1] = a[subIndex]
            subIndex--
        }
        a[subIndex + 1] = value
    }
}