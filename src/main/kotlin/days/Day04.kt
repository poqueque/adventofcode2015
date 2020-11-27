package days

import util.MathUtils.md5Hash

class Day04 : Day(4) {

    override fun partOne(): Any {
        val input = inputList[0]
        var fiveZeroes = false
        var i = 0
        while (!fiveZeroes){
            i++
            fiveZeroes = md5Hash("$input$i").startsWith("00000")
        }
        return i
    }

    override fun partTwo(): Any {
        val input = inputList[0]
        var fiveZeroes = false
        var i = 0
        while (!fiveZeroes){
            i++
            fiveZeroes = md5Hash("$input$i").startsWith("000000")
        }
        return i
    }
}
