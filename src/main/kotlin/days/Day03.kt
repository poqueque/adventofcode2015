package days

import util.Coor

class Day03 : Day(3) {

    override fun partOne(): Any {
        val data = mutableMapOf<Coor,Int>()
        var x = 0
        var y = 0
        data[Coor(x,y)] = 1
        inputList[0].toList().map{
            if (it == '^') y++;
            if (it == 'v') y--;
            if (it == '<') x--;
            if (it == '>') x++;
            data[Coor(x,y)] = data[Coor(x,y)] ?: 0 + 1
        }
        return data.keys.size
    }

    override fun partTwo(): Any {
        val data = mutableMapOf<Coor,Int>()
        var xS = 0
        var yS = 0
        var xR = 0
        var yR = 0
        data[Coor(xS,yS)] = 2
        inputList[0].toList().filterIndexed { index, c -> index %2 == 0 }.map{
            if (it == '^') yS++;
            if (it == 'v') yS--;
            if (it == '<') xS--;
            if (it == '>') xS++;
            data[Coor(xS,yS)] = data[Coor(xS,yS)] ?: 0 + 1
        }
        inputList[0].toList().filterIndexed { index, c -> index %2 == 1 }.map{
            if (it == '^') yR++;
            if (it == 'v') yR--;
            if (it == '<') xR--;
            if (it == '>') xR++;
            data[Coor(xR,yR)] = data[Coor(xR,yR)] ?: 0 + 1
        }
        return data.keys.size
    }
}
