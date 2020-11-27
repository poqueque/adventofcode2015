package days

class Day02 : Day(2) {

    override fun partOne(): Any {
        return inputList.map {
            val dimensions = it.split("x").map{ it2 -> it2.toInt() }.sorted()
            3*dimensions[0]*dimensions[1] + 2*dimensions[1]*dimensions[2] + 2*dimensions[0]*dimensions[2]
        }.toList().sumBy { it }
    }

    override fun partTwo(): Any {
        return 0
    }
}
