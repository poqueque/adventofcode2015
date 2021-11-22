package days

class Day25 : Day(25) {

    private var position =  9087234 //calculated on hand and paper
    private val firstCode = 20151125L

    override fun partOne(): Any {

        val row = inputString.substringBeforeLast(",").substringAfterLast(" ").toInt()
        val column = inputString.substringBeforeLast(".").substringAfterLast(" ").toInt()
        println ("Row: $row, Column: $column")
        position = getPosition(row,column)
        var code = firstCode
        for (i in 1 until position) {
            code = (code * 252533) % 33554393
        }
        return code
    }

    private fun getPosition(row: Int, column: Int): Int {
        val triangle = row + column - 2
        val triangleSum = triangle*(triangle+1)/2
        return triangleSum+column
    }

    override fun partTwo(): Any {
        return 0
    }
}
