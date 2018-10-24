package sorts


fun bubbleSort(a: IntArray){
    var n = a.size - 1
    for(i in 0 until n ){
        for (j in (n) downTo (i+1)){
            if (a[j] < a[j-1]) {
                a[j] = a[j - 1].also { a[j -1] = a[j] }
            }
        }
    }

}

fun bubbleSortSwap(a: IntArray) {
    var n = a.size - 1

    for(i in 0 until n ){
       var swaps = 0
        for (j in (n) downTo (i+1)){
            if (a[j] < a[j-1]) {
                a[j] = a[j - 1].also { a[j -1] = a[j] }
                swaps++
            }
        }
        if (swaps == 0) break
    }

}