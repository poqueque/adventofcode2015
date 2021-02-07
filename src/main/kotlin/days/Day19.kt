package days

class Day19 : Day(19) {

    var replacements = mutableListOf<Replacement>()
    var molecule = ""
    val cache = mutableMapOf("e" to 0)

    fun readData() {
        inputList.forEach {
            if (it.contains("=>")){
                val words = it.split(" => ")
                replacements.add(Replacement(words[0],words[1]))
            } else if (it.length > 2) {
                molecule = it
            }
        }
    }

    override fun partOne(): Any {
        readData()
        val molecules = mutableListOf<String>()

        replacements.forEach {
            var pos = molecule.indexOf(it.from)
            while (pos >= 0) {
                molecules.add(molecule.replaceRange(pos until (pos+it.from.length),it.to))
                pos = molecule.indexOf(it.from, pos+1)
            }
        }
        return molecules.distinct().size
    }

    override fun partTwo(): Any {
        var mol = molecule
        var times = 0

        var prevLen = mol.length
        while (mol.length > 1) {
            replacements.shuffle()
            for (rule in replacements) {
                while (mol.contains(rule.to)) {
                    mol = mol.replaceFirst(rule.to, rule.from)
                    times++
                }
//                println("$times -> $mol (${mol.length})")
                if (mol == "e") return times
            }
            if (mol.length == prevLen) {
                //Reset
//                println("RESET")
                mol = molecule
                times = 0
            }
            prevLen = mol.length
        }
        return times
    }
    data class Replacement(val from: String, val to: String)
}