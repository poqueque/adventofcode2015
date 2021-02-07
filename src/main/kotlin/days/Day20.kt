package days

import java.lang.Math.sqrt

class Day20 : Day(20) {

    override fun partOne(): Any {
        val presents = inputString.toLong()
        var house = 1
        while (presentsInHouse(house) < presents)
            house++
        return house
    }

    private fun presentsInHouse(house: Int): Long {
        val divisors = getAllDivisors(house)
        return divisors.sum() * 10L
    }

    private fun getAllDivisors(number: Int): List<Int> {
        val div = mutableListOf<Int>()
        div.add(1)
        div.add(number)
        for (i in 1..kotlin.math.sqrt(number.toDouble()).toInt()) {
            if (number % i == 0) {
                div.add(i)
                div.add(number / i)
            }
        }
        return div.distinct()
    }

    override fun partTwo(): Any {
        val presentTarget = inputString.toLong()
        val presents = mutableMapOf<Int, Int>()
        for (elf in 1..1000000)
            for (house in 1..50)
                presents[elf * house] = (presents[elf * house] ?: 0) + (11 * elf)
        return presents.filter { it.value >= presentTarget }.keys.minOrNull()!!
    }
}
