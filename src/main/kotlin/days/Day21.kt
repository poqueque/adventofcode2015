package days

import java.lang.Integer.max

class Day21 : Day(21) {

    data class RPGPlayer(var hitPoints: Int, var damage: Int, var defense: Int)

    val weapons = mutableMapOf(
        "Dagger" to listOf(8, 4, 0),
        "Shortsword" to listOf(10, 5, 0),
        "Warhammer" to listOf(25, 6, 0),
        "Longsword" to listOf(40, 7, 0),
        "Greataxe" to listOf(74, 8, 0),
    )
    val armor = mutableMapOf(
        "None" to listOf(0, 0, 0),
        "Leather" to listOf(13, 0, 1),
        "Chainmail" to listOf(31, 0, 2),
        "Splintmail" to listOf(53, 0, 3),
        "Bandedmail" to listOf(75, 0, 4),
        "Platemail" to listOf(102, 0, 5),
    )
    val rings = mutableMapOf(
        "None" to listOf(0, 0, 0),
        "Damage +1" to listOf(25, 1, 0),
        "Damage +2" to listOf(50, 2, 0),
        "Damage +3" to listOf(100, 3, 0),
        "Defense +1" to listOf(20, 0, 1),
        "Defense +2" to listOf(40, 0, 2),
        "Defense +3" to listOf(80, 0, 3),
    )

    var me = RPGPlayer(100, 0, 0)
    var boss = RPGPlayer(100, 0, 0)

    var weapon = -1

    private fun play(me: RPGPlayer): Boolean {
        var b = boss.copy()
        while (me.hitPoints > 0 && b.hitPoints > 0) {
            b.hitPoints -= max(me.damage - b.defense, 1)
            if (b.hitPoints <= 0) return true
            me.hitPoints -= max(b.damage - me.defense, 1)
            if (me.hitPoints <= 0) return false
        }
        return false
    }

    override fun partOne(): Any {
        boss.hitPoints = inputList[0].split(":")[1].trim().toInt()
        boss.damage = inputList[1].split(":")[1].trim().toInt()
        boss.defense = inputList[2].split(":")[1].trim().toInt()
        var cheapestOption = 10000000
        for (w in weapons)
            for (a in armor)
                for (r1 in rings)
                    for (r2 in rings) {
                        val price = w.value[0] + a.value[0] + r1.value[0] + r2.value[0]
                        if (price < cheapestOption) {
                            val player = RPGPlayer(
                                100,
                                w.value[1] + a.value[1] + r1.value[1] + r2.value[1],
                                w.value[2] + a.value[2] + r1.value[2] + r2.value[2]
                            )
                            if (play(player)) {
                                cheapestOption = price
                                println("RPGPlayer: $player => $cheapestOption")
                            }
                        }
                    }
        return cheapestOption
    }

    override fun partTwo(): Any {
        var expensiveOption = 0
        for (w in weapons)
            for (a in armor)
                for (r1 in rings)
                    for (r2 in rings) {
                        if (r1 != r2) {
                            val price = w.value[0] + a.value[0] + r1.value[0] + r2.value[0]
                            if (price > expensiveOption) {
                                val player = RPGPlayer(
                                    100,
                                    w.value[1] + a.value[1] + r1.value[1] + r2.value[1],
                                    w.value[2] + a.value[2] + r1.value[2] + r2.value[2]
                                )
                                if (!play(player)) {
                                    expensiveOption = price
                                    println("RPGPlayer: $player => $expensiveOption")
                                }
                            }
                        }
                    }
        return expensiveOption
    }
}
