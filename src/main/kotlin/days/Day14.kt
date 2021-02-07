package days

class Day14 : Day(14) {

    private val raceLength = 2503

    override fun partOne(): Any {
        var max = 0
        inputList.forEach {
            val words = it.split(" ")
            val name = words[0]
            val speed = words[3].toIntOrNull()!!
            val fly = words[6].toIntOrNull()!!
            val rest = words[13].toIntOrNull()!!
            var km = 0
            var remainingTime = raceLength
            while (remainingTime > 0) {
                if (fly < remainingTime) {
                    km += speed * fly
                    remainingTime -= fly
                } else {
                    km += speed * remainingTime
                    remainingTime = 0
                }
                if (rest < remainingTime) {
                    remainingTime -= rest
                } else {
                    remainingTime = 0
                }
            }
            println("$name -> $km")
            if (km > max) max = km
        }
        return max
    }

    override fun partTwo(): Any {
        val reinders = mutableListOf<Reinder>()
        var max = 0
        inputList.forEach {
            val words = it.split(" ")
            val name = words[0]
            val speed = words[3].toIntOrNull()!!
            val fly = words[6].toIntOrNull()!!
            val rest = words[13].toIntOrNull()!!
            reinders.add(Reinder(name, speed, fly, rest, fly, 0))
        }
        var remainingTime = raceLength
        while (remainingTime > 0) {
            reinders.forEach {
                if (it.flying > 0) {
                    it.flying --
                    it.km += it.speed
                    if (it.flying == 0) it.resting = it.rest
                } else {
                    it.resting --
                    if (it.resting == 0) it.flying = it.fly
                }
            }
            //Award points
            val leadKm = reinders.maxByOrNull { it.km }!!.km
            reinders.forEach {
                if (it.km == leadKm) it.points++
            }
            remainingTime--
        }

        reinders.sortedBy {it.points}.forEach {
            println("${it.name} -> ${it.points} ${it.km}")
            if (it.points > max) max = it.points
        }
        return max
    }

    data class Reinder(val name: String, val speed: Int, val fly: Int, val rest: Int, var flying: Int, var resting: Int, var km: Int = 0, var points: Int = 0)
}