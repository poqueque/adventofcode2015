package days

class Day15 : Day(15) {

    val ingredients = mutableListOf<Ingredient>()
    var mixture = mutableListOf<Int>()

    override fun partOne(): Any {
        readData()
        var max = 0L
        for (i in 0..100) {
            for (j in 0..(100 - i))
                for (k in 0..(100 - i - j)) {
                    val l = 100 - i - j - k
                    val s = score(listOf(i, j, k, l))
                    if (s > max) {
                        max = s
                    }
                }
        }
        return max
    }

    private fun score(mixture: List<Int>, calories: Int? = null): Long {
        var cap = 0
        var dur = 0
        var fla = 0
        var tex = 0
        var cal = 0
        for (i in mixture.indices) {
            cap += mixture[i] * ingredients[i].capacity
            dur += mixture[i] * ingredients[i].durability
            fla += mixture[i] * ingredients[i].flavor
            tex += mixture[i] * ingredients[i].texture
            cal += mixture[i] * ingredients[i].calories
        }
        if (cap < 0) cap = 0
        if (dur < 0) dur = 0
        if (fla < 0) fla = 0
        if (tex < 0) tex = 0
        if (cal < 0) cal = 0
        if (calories != null && cal != calories) return -1
        return cap.toLong() * dur * fla * tex
    }

    private fun readData() {
        inputList.forEach {
            val words = it.replace(":", "").replace(",", "").split(" ")
            ingredients.add(
                Ingredient(
                    words[0],
                    words[2].toInt(),
                    words[4].toInt(),
                    words[6].toInt(),
                    words[8].toInt(),
                    words[10].toInt()
                )
            )
        }
    }

    override fun partTwo(): Any {
        readData()
        var max = 0L
        for (i in 0..100) {
            for (j in 0..(100 - i))
                for (k in 0..(100 - i - j)) {
                    val l = 100 - i - j - k
                    val s = score(listOf(i, j, k, l), 500)
                    if (s > max) {
                        max = s
                    }
                }
        }
        return max
    }

    data class Ingredient(
        val name: String,
        val capacity: Int,
        val durability: Int,
        val flavor: Int,
        val texture: Int,
        val calories: Int
    )
}
