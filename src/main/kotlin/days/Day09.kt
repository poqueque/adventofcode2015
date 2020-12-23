package days

import util.MathUtils
import util.splitrim

class Day09 : Day(9) {

    val places = mutableListOf<String>()
    val map = mutableMapOf<Pair<String,String>,Int>()

    override fun partOne(): Any {
        inputList.forEach {
            val (route, distance) = it.splitrim("=")
            val (from, to) = route.splitrim("to")
            map[Pair(from,to)] = distance.toInt()
            map[Pair(to,from)] = distance.toInt()
            if (!places.contains(from)) places.add(from)
            if (!places.contains(to)) places.add(to)
        }
        val permuts = MathUtils.permute(places)
        var minDistance = Int.MAX_VALUE
        permuts.forEach {
            var distance = 0
            var place = it[0]
            for (i in 1 until it.size) {
                distance += map[Pair(place,it[i])]!!
                place = it[i]
            }
            if (distance<minDistance) minDistance = distance
        }
        return minDistance
    }

    override fun partTwo(): Any {

        inputList.forEach {
            val (route, distance) = it.splitrim("=")
            val (from, to) = route.splitrim("to")
            map[Pair(from,to)] = distance.toInt()
            map[Pair(to,from)] = distance.toInt()
            if (!places.contains(from)) places.add(from)
            if (!places.contains(to)) places.add(to)
        }
        val permuts = MathUtils.permute(places)
        var maxDistance = 0
        permuts.forEach {
            var distance = 0
            var place = it[0]
            for (i in 1 until it.size) {
                distance += map[Pair(place,it[i])]!!
                place = it[i]
            }
            if (distance>maxDistance) maxDistance = distance
        }
        return maxDistance
    }
}
