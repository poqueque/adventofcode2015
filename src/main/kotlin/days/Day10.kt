package days

class Day10 : Day(10) {

    override fun partOne(): Any {
        var string = inputString
        repeat(40) {
            string = lookAndSay(string)
        }
        return string.length
    }

    private fun lookAndSay(startString: String): String {
        var lastChar = startString[0]
        var counter = 0
        val builder = StringBuilder()
        for (i in startString.indices) {
            if (startString[i] == lastChar) counter++
            else {
                builder.append(counter)
                builder.append(lastChar)
                counter = 1
                lastChar = startString[i]
            }
        }
        builder.append(counter)
        builder.append(lastChar)
        return builder.toString()
    }

    override fun partTwo(): Any {
        var string = inputString
        repeat(50) {
            string = lookAndSay(string)
        }
        return string.length
    }
}
