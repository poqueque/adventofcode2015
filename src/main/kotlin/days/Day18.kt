package days

import util.Coor
import util.CoorMap

class Day18 : Day(18) {
    var initialMap = CoorMap(inputList)

    override fun partOne(): Any {
        var map = initialMap
        for (i in 1..100) {
            map = nextMap(map)
        }
        return map.map.count { it.value == '#' }
    }

    override fun partTwo(): Any {
        var map = initialMap
        map.map[Coor(0,0)]= '#'
        map.map[Coor(map.maxX-1,0)]= '#'
        map.map[Coor(0,map.maxY-1)]= '#'
        map.map[Coor(map.maxX-1,map.maxY-1)]= '#'
        for (i in 1..100) {
            map = nextMap(map)
            map.map[Coor(0,0)]= '#'
            map.map[Coor(map.maxX-1,0)]= '#'
            map.map[Coor(0,map.maxY-1)]= '#'
            map.map[Coor(map.maxX-1,map.maxY-1)]= '#'
        }
        return map.map.count { it.value == '#' }
    }

    private fun nextMap(map: CoorMap): CoorMap {
        val nextMap = CoorMap(inputList)
        for (k in map.map.keys) {
            val n = map.getNeigbours(k, '#')
            if (map.map[k] == '#')
                if (n == 2 || n == 3) nextMap.map[k] = '#'
                else nextMap.map[k] = '.'
            else
                if (n == 3) nextMap.map[k] = '#'
                else nextMap.map[k] = '.'
        }
        return nextMap
    }
}
