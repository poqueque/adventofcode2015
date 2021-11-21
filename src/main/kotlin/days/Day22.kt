package days

import java.lang.Integer.max
import kotlin.random.Random

data class Spell(val name: String, val mana: Int, val damage: Int, val heal: Int, val effect: Effect?)
data class Effect(
    val name: String,
    val armorIncrease: Int,
    val damageIncrease: Int,
    val manaIncrease: Int,
    var turns: Int
)

class Day22 : Day(22) {

    private val none = Spell("None", 0, 0, 0, null)
    private val magicMissile = Spell("Magic Missile", 53, 4, 0, null)
    private val drain = Spell("Drain", 73, 2, 2, null)
    private val shield = Spell("Shield", 113, 0, 0, Effect("Shield", 7, 0, 0, 6))
    private val poison = Spell("Poison", 173, 0, 0, Effect("Poison", 0, 3, 0, 6))
    private val recharge = Spell("Recharge", 229, 0, 0, Effect("Recharge", 0, 0, 101, 5))

    private var me = RPGWizard(50, 0, 0, 500)
    private var boss = RPGPlayer(71, 10, 0)
    //private var me = RPGWizard(10, 0, 0, 250)
    //private var boss = RPGPlayer(14, 8, 0)

    private fun play(me: RPGWizard, print: Boolean = false, hard: Boolean = false): Pair<Int, List<Spell>> {
        val b = boss.copy()
        var turn = 0
        val effects = mutableListOf<Effect>()
        var manaSpent = 0
        val spellsCasted = mutableListOf<Spell>()
        while (me.hitPoints > 0 && b.hitPoints > 0) {
            //val s = spells[turn % spells.size]
            val validSpells = mutableListOf(magicMissile, drain, shield, poison, recharge)
            if (spellsCasted.size > 0 && spellsCasted[spellsCasted.size - 1] == shield) validSpells.remove(shield)
            if (spellsCasted.size > 0 && spellsCasted[spellsCasted.size - 1] == poison) validSpells.remove(poison)
            if (spellsCasted.size > 0 && spellsCasted[spellsCasted.size - 1] == recharge) validSpells.remove(recharge)
            if (spellsCasted.size > 1 && spellsCasted[spellsCasted.size - 2] == shield) validSpells.remove(shield)
            if (spellsCasted.size > 1 && spellsCasted[spellsCasted.size - 2] == poison) validSpells.remove(poison)
            if (spellsCasted.size > 1 && spellsCasted[spellsCasted.size - 2] == recharge) validSpells.remove(recharge)
            if (me.mana < magicMissile.mana) validSpells.remove(magicMissile)
            if (me.mana < drain.mana) validSpells.remove(drain)
            if (me.mana < shield.mana) validSpells.remove(shield)
            if (me.mana < poison.mana) validSpells.remove(poison)
            if (me.mana < recharge.mana) validSpells.remove(recharge)
            var s: Spell = if (validSpells.size > 0) validSpells[Random.nextInt(validSpells.size)] else none
            //if (me.mana >= recharge.mana && validSpells.contains(recharge)) s = recharge
/*            when (spellsCasted.size) {
                0 -> s = poison
                1 -> s = recharge
                2 -> s = shield
                3 -> s = poison
                4 -> s = recharge
            }*/
            spellsCasted.add(s)

            if (print) println(spellsCasted.map{ s-> s.name})

            if (hard) {
                me.hitPoints--
                if (me.hitPoints <= 0) return Pair(-b.hitPoints,spellsCasted)
            }
            var armorEffect = getArmorEffect(effects)
            var damageEffect = getDamageEffect(effects)
            var manaEffect = getManaEffect(effects)
            for (e in effects) e.turns--
            if (print) {
                println("-- Player turn --")
                println("- Player has ${me.hitPoints} hit points, $armorEffect armor, ${me.mana} mana")
                println("- Boss has ${b.hitPoints} hit points")
                println("- Effects does $damageEffect damage: ${effects.map { e -> "${e.name} (${e.turns})" }}")
                println("Player casts ${s.name} -> ${manaSpent + s.mana}")
                println()
            }
//            if (effects.find { e -> e.name == s.name } != null) return Pair(-1, spellsCasted)
            effects.removeIf { e -> e.turns == 0 }
            b.hitPoints -= s.damage + damageEffect
            me.mana += manaEffect
            me.hitPoints += s.heal
            me.mana -= s.mana
            manaSpent += s.mana
            if (me.mana < 0) return Pair(-b.hitPoints,spellsCasted)
            if (s.effect != null) {
                effects.add(s.effect!!.copy())
            }
            if (b.hitPoints <= 0) return Pair(manaSpent,spellsCasted)

/*            if (hard) {
                me.hitPoints--
                if (me.hitPoints <= 0) return Pair(-b.hitPoints,spellsCasted)
            }*/
            armorEffect = getArmorEffect(effects)
            damageEffect = getDamageEffect(effects)
            manaEffect = getManaEffect(effects)
            for (e in effects) e.turns--
            if (print) {
                println("-- Boss turn --")
                println("- Player has ${me.hitPoints} hit points, $armorEffect armor, ${me.mana} mana")
                println("- Boss has ${b.hitPoints} hit points")
                println("- Effects does $damageEffect damage: ${effects.map { e -> "${e.name} (${e.turns})" }}")
                println("Boss attacks for ${max(b.damage - (me.defense + armorEffect), 1)} damage.")
                println()
            }
            me.mana += manaEffect
            b.hitPoints -= damageEffect
            if (b.hitPoints <= 0) {
                if (print) println("- Boss has been killed ${b.hitPoints}")
                return Pair(manaSpent,spellsCasted)
            }
            me.hitPoints -= max(b.damage - (me.defense + armorEffect), 1)
            if (me.hitPoints <= 0) return Pair(-b.hitPoints,spellsCasted)
            effects.removeIf { e -> e.turns == 0 }
            turn++
        }
        return Pair(-b.hitPoints,spellsCasted)
    }

    private fun getArmorEffect(effects: MutableList<Effect>): Int {
        var armorEffect = 0
        for (e in effects) armorEffect += e.armorIncrease
        return armorEffect
    }

    private fun getDamageEffect(effects: MutableList<Effect>): Int {
        var damageEffect = 0
        for (e in effects) damageEffect += e.damageIncrease
        return damageEffect
    }

    private fun getManaEffect(effects: MutableList<Effect>): Int {
        var manaEffect = 0
        for (e in effects) manaEffect += e.manaIncrease
        return manaEffect
    }

    override fun partOne(): Any {
//        val manaSpent1 = play(me, listOf(spells[3],spells[0]))
//        println("First: $manaSpent1")
//        val manaSpent2 = play(me.copy(), listOf(recharge, shield, drain, poison, magicMissile), true)
//        println("First: $manaSpent2")

        var best = 1000000000

        repeat(1000000) {
            // println(list.map { s -> s.name })
            val data = play(me.copy(), false)
            val spellsCasted = data.second
            if (data.first in 0 until best) {
                best = data.first
                println("Improved: $best")
                println("${spellsCasted.map{s->s.name}}")
            }
        }

        return best
    }

    override fun partTwo(): Any {

        var best = 1000000000
        var bestLost = -100000000

        repeat(10000000) {
            // println(list.map { s -> s.name })
            val data = play(me.copy(), hard = true, print = false)
            val spellsCasted = data.second
            if (data.first in 0 until best) {
                best = data.first
                println("Improved: $best")
                println("${spellsCasted.map{s->s.name}}")
            }
            if (data.first in bestLost+1 until 0) {
                bestLost = data.first
//                println("Lost: $bestLost")
//                println("${spellsCasted.map{s->s.name}}")
            }
        }
        return best
    }
}

data class RPGWizard(var hitPoints: Int, var damage: Int, var defense: Int, var mana: Int)
