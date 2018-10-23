/*
* David Lindsey
*
*
*
*
* */

package sorts

import java.util.Random
import kotlin.system.measureNanoTime

//100, 1_000, 10_000
//random, sorted, almost sorted

val rand = Random()
const val N = 10_000


val sortedList10_000 = IntArray(10_000){ rand.nextInt( N ) }.sortedArray()
val sortedList1_000 = IntArray(1_000){ rand.nextInt( N ) }.sortedArray()
val sortedList100:IntArray = IntArray(100){ rand.nextInt( N ) }.sortedArray()
val sortedList10:IntArray = IntArray(10){ rand.nextInt( N ) }.sortedArray()

val randList10_000:IntArray = IntArray(10_000){ rand.nextInt( N ) }
val randList1_000 = IntArray(1_000){ rand.nextInt( N ) }
val randList100 = IntArray(100){ rand.nextInt( N ) }
val randList10 = IntArray(10){ rand.nextInt( N ) }

val nSList10_000: IntArray = sortedList10_000
val nSList1_000: IntArray = sortedList1_000
var nSList100:IntArray = sortedList100
var nSList10:IntArray = sortedList10




fun main(args: Array<String>) {

    nSList100[5] = rand.nextInt(N)
    for (i in 5..95 step 10) nSList100[i] = rand.nextInt(N)
    for (i in 5..995 step 10) nSList1_000[i] = rand.nextInt(N)
    for (i in 5..9995 step 10) nSList10_000[i] = rand.nextInt(N)

    val seqs = listOf(sortedList10, sortedList100, sortedList1_000, sortedList10_000,
            randList10, randList100, randList1_000, randList10_000,
            nSList10, nSList100, nSList1_000, nSList10_000)

    val sorts = listOf<(IntArray) -> Unit>(
            ::bubbleSort, ::bubbleSortSwap, ::mergeSort, ::quickSort, ::selectionSort, ::insertionSort)

    val avgTimes = List(sorts.size) { LongArray(seqs.size) }

    for ((i, seq) in seqs.withIndex()) {
        for ((k, sort) in sorts.withIndex()) {
            var temp = 0L; var temp2 = 0L; var temp3 = 0L

            repeat(10) {
                repeat(10) {
                    repeat(10) {
                         temp += measureNanoTime { sort(seq.copyOf()) }
                    }
                    temp2 += temp/10
                }
                temp3 += temp2 / 10
            }
            avgTimes[k][i] = temp3 / 10

        }
    }
    val sortNames = listOf("Bubble", "BubbleSwap", "Merge", "Quick", "Select", "Insert")

    val arrayNames = listOf("sorted:", "random:", "nearly sorted:")

    print("Size:           ")
    val sizes = listOf(10, 100, 1_000, 10_000)
    for (size in sizes) print("%15d".format(size))
    repeat(2) { println()}
    for ((k, sortName) in sortNames.withIndex()) {
        println("${sortName}")
        for (i in 0..11) {
            if  ( i % 4 == 0 )  print("%16s".format(arrayNames[i/4]))
            print("%14d".format(avgTimes[k][i]/1000))
            if ( i % 4 == 3 ) println()
        }
        println("\n")
    }

}



