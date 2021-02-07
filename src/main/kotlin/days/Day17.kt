package days

import java.lang.Math.pow
import kotlin.math.pow

class Day17 : Day(17) {

    private val containers = mutableListOf<Int>()

    override fun partOne(): Any {
        readData()
        val value = 150
        var ok = 0
        for (i in 0..2.0.pow(containers.size).toInt()){
            val combination = i.toString(2).padStart(containers.size,'0')
            var fill = 0
            for (j in combination.indices) {
                if (combination[j] == '1') {
                    fill += containers[j]
                }
            }
            if (fill == value) {
                ok++
            }
        }
        return ok
    }

    override fun partTwo(): Any {
        val value = 150
        var ok = 0
        var min = 150
        for (i in 0..2.0.pow(containers.size).toInt()){
            val combination = i.toString(2).padStart(containers.size,'0')
            var fill = 0
            var used = 0
            for (j in combination.indices) {
                if (combination[j] == '1') {
                    fill += containers[j]
                    used ++
                }
            }
            if (fill == value) {
                if (used < min) {
                    min = used
                    ok = 0
                }
                if (used == min) ok++
            }
        }
        return ok
    }

    fun readData() {
        inputList.forEach {
            containers.add(it.toInt())
        }
    }
}
