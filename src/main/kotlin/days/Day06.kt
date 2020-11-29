package days

import util.Coor

class Day06 : Day(6) {

    var lights = Array(1000) { BooleanArray(1000) { false } }
    var lightsInt = Array(1000) { IntArray(1000) { 0 } }

    override fun partOne(): Any {
        inputList.forEach {
            processInstruction(it)
        }
        var total = 0
        for (i in 0..999)
            for (j in 0..999)
                if (lights[i][j]) total++
        return total
    }

    private fun processInstruction(it: String) {
        val words = it.split(" ")
        var instruction = NONE
        var x0 = -1
        var y0 = -1
        var x1 = -1
        var y1 = -1
        words.forEach {
            if (it == "toggle") instruction = TOGGLE
            if (it == "on") instruction = ON
            if (it == "off") instruction = OFF
            if (it.contains(",")) {
                val c = it.split(",")
                if (x0 == -1) {
                    x0 = c[0].toInt()
                    y0 = c[1].toInt()
                } else {
                    x1 = c[0].toInt()
                    y1 = c[1].toInt()
                }
            }
        }
        for (x in x0..x1)
            for (y in y0..y1) {
                when (instruction) {
                    ON -> lights[x][y] = true
                    OFF -> lights[x][y] = false
                    TOGGLE -> lights[x][y] = !(lights[x][y])
                }
            }
    }

    private fun processInstructionInt(it: String) {
        val words = it.split(" ")
        var instruction = NONE
        var x0 = -1
        var y0 = -1
        var x1 = -1
        var y1 = -1
        words.forEach {
            if (it == "toggle") instruction = TOGGLE
            if (it == "on") instruction = ON
            if (it == "off") instruction = OFF
            if (it.contains(",")) {
                val c = it.split(",")
                if (x0 == -1) {
                    x0 = c[0].toInt()
                    y0 = c[1].toInt()
                } else {
                    x1 = c[0].toInt()
                    y1 = c[1].toInt()
                }
            }
        }
        for (x in x0..x1)
            for (y in y0..y1) {
                when (instruction) {
                    ON -> lightsInt[x][y] ++
                    OFF -> lightsInt[x][y] --
                    TOGGLE -> lightsInt[x][y] += 2
                }
                if (lightsInt[x][y]<0) lightsInt[x][y] = 0
            }
    }
    override fun partTwo(): Any {
        inputList.forEach {
            processInstructionInt(it)
        }
        var total = 0
        for (i in 0..999)
            for (j in 0..999)
                total += lightsInt[i][j]
        return total
    }

    companion object {
        private const val NONE: Int = 0
        private const val TOGGLE: Int = 1
        private const val ON: Int = 2
        private const val OFF: Int = 3
    }
}
