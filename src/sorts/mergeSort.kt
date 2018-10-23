package sorts
fun merge(A: IntArray, p: Int, q: Int, r: Int) {
    var left = A.copyOfRange(p, q + 1)
    var right = A.copyOfRange(q + 1, r + 1)
    var i = 0
    var j = 0

    for (k in p..r) {
        if ((i <= left.size - 1) && ((j >= right.size) || (left[i] <= right[j]))) {
            A[k] = left[i];
            i++;
        } else {
            A[k] = right[j];
            j++;
        }
    }
}

fun mergeSort(A: IntArray, p: Int, r: Int) {
    if (p < r) {
        var q = (p + r) / 2
        mergeSort(A, p, q)
        mergeSort(A, q + 1, r)
        merge(A, p, q, r)
    }
}

fun mergeSort(A: IntArray) {
    val p = 0
    val r = A.size -1
    if (p < r) {
        var q = (p + r) / 2
        mergeSort(A, p, q)
        mergeSort(A, q + 1, r)
        merge(A, p, q, r)
    }
}