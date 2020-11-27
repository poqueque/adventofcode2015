package days

import util.Pos

class Day03 : Day(3) {

    override fun partOne(): Any {
        val data = mutableMapOf<Pos,Int>()
        var x = 0
        var y = 0
        data[Pos(x,y)] = 1
        inputList[0].toList().map{
            if (it == '^') y++;
            if (it == 'v') y--;
            if (it == '<') x--;
            if (it == '>') x++;
            data[Pos(x,y)] = data[Pos(x,y)] ?: 0 + 1
        }
        return data.keys.size
    }

    override fun partTwo(): Any {
        val data = mutableMapOf<Pos,Int>()
        var xS = 0
        var yS = 0
        var xR = 0
        var yR = 0
        data[Pos(xS,yS)] = 2
        inputList[0].toList().filterIndexed { index, c -> index %2 == 0 }.map{
            if (it == '^') yS++;
            if (it == 'v') yS--;
            if (it == '<') xS--;
            if (it == '>') xS++;
            data[Pos(xS,yS)] = data[Pos(xS,yS)] ?: 0 + 1
        }
        inputList[0].toList().filterIndexed { index, c -> index %2 == 1 }.map{
            if (it == '^') yR++;
            if (it == 'v') yR--;
            if (it == '<') xR--;
            if (it == '>') xR++;
            data[Pos(xR,yR)] = data[Pos(xR,yR)] ?: 0 + 1
        }
        return data.keys.size
    }
}
