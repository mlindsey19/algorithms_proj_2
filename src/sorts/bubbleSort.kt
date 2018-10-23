package sorts


fun bubbleSort(a: IntArray) {
    var n = a.size
    do {
        var n2 = 0
        for (i in 1 until n) {
            if (a[i - 1] > a[i]) {
                val tmp = a[i]
                a[i] = a[i - 1]
                a[i - 1] = tmp
                n2 = i
            }
        }
        n = n2
    } while (n != 0)
}

fun bubbleSortSwap(a: IntArray) {
    var n = a.size
    var s =0
    do {
        var n2 = 0
        for (i in 1 until n) {
            s = 0
            if (a[i - 1] > a[i]) {
                val tmp = a[i]
                a[i] = a[i - 1]
                a[i - 1] = tmp
                n2 = i
                s++
            }
        }
        if (s == 0) break
        n = n2
    } while (n != 0)
}