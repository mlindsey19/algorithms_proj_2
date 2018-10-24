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
const val N = 10_000

fun main(args: Array<String>) {

    val rand = Random()

    fun randomList( n: Int ) = IntArray( n ) {rand.nextInt( N ) }
    fun sortedList(n:Int) = randomList(n).sorted().toIntArray()
    fun nearlySortedList(n: Int):IntArray {
        val list: IntArray = sortedList(n)
        for (i in 5..n step 10) list[i] = rand.nextInt(N)
        return list
    }
    val sizes = listOf(10, 100, 1_000, 2_000, 5_000, 7_500, 10_000)
    val lists = listOf<(Int)->IntArray>( ::sortedList, ::nearlySortedList, ::randomList )
    val sorts = listOf<(IntArray) -> Unit>(
            ::bubbleSort, ::bubbleSortSwap, ::mergeSort, ::quickSort, ::selectionSort, ::insertionSort)

    val avgTimes = List(sorts.size) { List(lists.size) {LongArray(sizes.size) } }

    //to compile sorts before timing, to 10 so that both overloaded mergeSort is compiled
    for (sort in sorts) sort(IntArray(10){rand.nextInt(9)})

    for ((sortIndex, sort) in sorts.withIndex()) {
        for ((listIndex, list) in lists.withIndex()) {
            for ((sizeIndex, size) in sizes.withIndex()) {
                var temp = 0L; var temp2 = 0L; var temp3 = 0L //for avgs

                //avg over 1000 in chunks to avoid overflow
                repeat(10) {
                    repeat(10) {
                        repeat(10) {
                            //bind list before time starts
                            val tempList = list(size)
                            temp += measureNanoTime { sort( tempList ) }
                        }
                        temp2 += temp / 10
                        temp = 0
                    }
                    temp3 += temp2 / 10
                    temp2 = 0
                }
                avgTimes[sortIndex][listIndex][sizeIndex] = temp3 / 10
            }
        }
    }
    val sortNames = listOf("Bubble", "BubbleSwap", "Merge", "Quick", "Select", "Insert")

    val listNames = listOf("sorted:", "nearly_sorted:", "random:")

    print("Size:           -")
    for (size in sizes) print("%15d-".format(size))
    repeat(2) { println()}
    for ((sortIndex, sortName) in sortNames.withIndex()) {
        println("$sortName-")
        for ((listIndex, listName) in listNames.withIndex()) {
            print("%16s-".format(listName)) // "-" dash is for parsing into excel
            for (sizeIndex in 0 until sizes.size ){
                print("%14d-".format(avgTimes[sortIndex][listIndex][sizeIndex] / 1_000))
            }
            println()
        }
        println("\n")
    }

}



