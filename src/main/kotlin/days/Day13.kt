package days

import util.MathUtils.permute

class Day13 : Day(13) {

    private val map = mutableMapOf<Pair<String, String>, Int>()
    val list = mutableListOf<String>()

    override fun partOne(): Any {
        inputList.forEach {
            val words = it.split(" ")
            var value = words[3].toInt()
            if (words[2] == "lose") value = -value
            val subject = words[0]
            val predicate = words[10].replace(".", "")
            map[Pair(subject, predicate)] = value
            if (!list.contains(subject)) list.add(subject)
        }
        val p = permute(list)
        var max = 0
        p.forEach {
            var total = 0
            it.forEach{ person ->
                val pos = it.indexOf(person)
                val v1 = (pos+1) % list.size
                val v2 = (pos+list.size-1) % list.size
                total += map[Pair(person,it[v1])]!!
                total += map[Pair(person,it[v2])]!!
            }
            if (total > max) max = total
        }
        return max
    }

    override fun partTwo(): Any {
        list.forEach{
            map[Pair(it,"Me")] = 0
            map[Pair("Me",it)] = 0
        }
        list.add("Me")
        val p = permute(list)
        var max = 0
        p.forEach {
            var total = 0
            it.forEach{ person ->
                val pos = it.indexOf(person)
                val v1 = (pos+1) % list.size
                val v2 = (pos+list.size-1) % list.size
                total += map[Pair(person,it[v1])]!!
                total += map[Pair(person,it[v2])]!!
            }
            if (total > max) max = total
        }
        return max
    }
}
