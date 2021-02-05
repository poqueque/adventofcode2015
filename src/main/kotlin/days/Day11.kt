package days

class Day11 : Day(11) {

    override fun partOne(): Any {
        println(inputString)
        return getNextValidPassword(inputString)
    }

    private fun getNextValidPassword(previousPassword: String): String {
        var nextPassword = inc(previousPassword)
        while (!isValid(nextPassword))
            nextPassword = inc(nextPassword)
        return nextPassword
    }

    private fun isValid(password: String): Boolean {
        if (password.contains("i")) return false
        if (password.contains("o")) return false
        if (password.contains("l")) return false

        var hasConsecutive = 0
        var consecutiveFound = ""
        for (i in 0..password.length-2)
            if (password[i] == password[i+1] && !consecutiveFound.contains(password[i])) {
                consecutiveFound += password[i]
                hasConsecutive++
            }
        if (hasConsecutive<2) return false

        var has3Letters = false
        for (i in 0..23)
            if (password.contains(letters.substring(i,i+3))) has3Letters = true
        if (!has3Letters) return false

        return true
    }

    override fun partTwo(): Any {
        return getNextValidPassword(getNextValidPassword(inputString)
        )
    }

    var letters =      "abcdefghijklmnopqrstuvwxyz"
    var basedLetters = "0123456789abcdefghijklmnop"
    private fun inc(string: String): String {
        var b23Str = ""
        string.forEach {
            val pos = letters.indexOf(it)
            b23Str+= basedLetters[pos]
        }
        val value = b23Str.toLong(26) +1
        var nextStr = ""
        value.toString(26).forEach {
            val pos = basedLetters.indexOf(it)
            nextStr+= letters[pos]
        }
        while (nextStr.length<string.length) nextStr = "a$nextStr"
        return nextStr
    }

}
