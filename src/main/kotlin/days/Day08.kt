package days

class Day08 : Day(8) {

    override fun partOne(): Any {
        var total = 0
        var memTotal = 0
        inputList.forEach {
            val inMem = it.substring(1,it.length-1)
                .replace("\\\\", "a")
                .replace("\\\"","b")
                .replace("\\\\x[0-9a-f][0-9a-f]".toRegex(),"-")
            total += it.length
            memTotal += inMem.length
        }
        return (total - memTotal)
    }

    override fun partTwo(): Any {
        var memtotal = 0
        var origTotal = 0
        inputList.forEach {
            val inMem = "\"" + it.replace("\\","\\\\").replace("\"","\\\"")+ "\""
            origTotal += it.length
            memtotal += inMem.length
        }
        return (memtotal - origTotal)
    }
}
