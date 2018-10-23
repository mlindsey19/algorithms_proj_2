package sorts

fun quickSort(a: IntArray) {
    fun sorter(first: Int, last: Int) {
        if (last - first < 1) return
        val pivot = a[first + (last - first) / 2]
        var left = first
        var right = last
        while (left <= right) {
            while (a[left] < pivot) left++
            while (a[right] > pivot) right--
            if (left <= right) {
                val tmp = a[left]
                a[left] = a[right]
                a[right] = tmp
                left++
                right--
            }
        }
        if (first < right) sorter(first, right)
        if (left < last) sorter(left, last)
    }
    sorter(0, a.lastIndex)
}