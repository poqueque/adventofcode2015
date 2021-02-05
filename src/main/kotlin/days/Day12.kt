package days

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser

class Day12 : Day(12) {

    override fun partOne(): Any {
        val parser: Parser = Parser.default()
        val stringBuilder: StringBuilder = StringBuilder(inputString)
        val json: JsonObject = parser.parse(stringBuilder) as JsonObject
        return getValueNum(json)
    }

    private fun getValueNum(json: Any, ignore: String? = null): Int {
        if (json is Int) return json
        if (json is String) return 0
        if (json is Map<*, *>) {
            var total = 0
            json.keys.forEach {
                if (ignore != null && json[it] == ignore) return 0
                total += getValueNum(json[it]!!, ignore)
            }
            return total
        }
        if (json is List<*>) {
            var total = 0
            json.forEach {
                total += getValueNum(it!!, ignore)
            }
            return total
        }
        println ("ERROR: $json")
        return 0
    }

    override fun partTwo(): Any {
        val parser: Parser = Parser.default()
        val stringBuilder: StringBuilder = StringBuilder(inputString)
        val json: JsonObject = parser.parse(stringBuilder) as JsonObject
        return getValueNum(json, "red")
    }
}
