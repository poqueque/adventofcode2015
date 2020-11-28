package days

class Day01 : Day(1) {

    override fun partOne(): Any {
        return inputString.toList().filter { it -> it == '(' }.size - inputString.toList().filter { it -> it == ')' }.size
    }

    override fun partTwo(): Any {
        var floor = 0
        inputString.toList().forEachIndexed { index, it ->
            if (it == '(') floor++
            if (it == ')') floor--
            if (floor == -1) return index +1
        }
        return -1
    }
}
