package days

class Day05 : Day(5) {

    override fun partOne(): Any {
        return inputList.map {
            if (hasThreeVowels(it) && hasRepeatedLetter(it) && !hasForbidden(it)) 1 else 0
        }.toList().sumBy { it }
    }

    private fun hasForbidden(it: String): Boolean {
        if (it.contains("ab")) return true
        if (it.contains("cd")) return true
        if (it.contains("pq")) return true
        if (it.contains("xy")) return true
        return false
    }

    private fun hasRepeatedLetter(it: String): Boolean {
        var last = '-'
        it.toList().forEach {
            if (it == last) return true
            last = it
        }
        return false
    }

    private fun hasThreeVowels(it: String): Boolean {
        var vowels = 0
        it.toList().map {
            if (it == 'a' || it == 'e' || it == 'i' || it == 'o' || it == 'u') vowels++
            if (vowels >= 3) return true
        }
        return false
    }

    override fun partTwo(): Any {
        return inputList.map {
            if (hasTwoLettersAppearingTwice(it) && hasABA(it)) 1 else 0
        }.toList().sumBy { it }
    }

    private fun hasABA(it: String): Boolean {
        val list = it.toList()
        list.forEachIndexed { index, value ->
            if ((index > 2) && value == list[index-2]) return true
        }
        return false
    }

    private fun hasTwoLettersAppearingTwice(it: String): Boolean {
        val list = it.toList()
        list.forEachIndexed { index, _ ->
            if (index > 0){
                if (it.substring(index+1).contains(it.substring(index-1,index+1))) return true
            }
        }
        return false
    }
}
