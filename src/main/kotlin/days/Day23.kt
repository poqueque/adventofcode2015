package days

class Day23 : Day(23) {

    fun run (program: List<String>, data: MutableMap<String,Int>): MutableMap<String,Int> {
        var p = 0
        while (p in program.indices) {
            val instruction = program[p]
            val parts = instruction.split(" ").map { e -> e.replace(",", "") }
            when (parts[0]) {
                "hlf" -> {
                    data[parts[1]] = (data[parts[1]] ?: 0) / 2
                    p++
                }
                "tpl" -> {
                    data[parts[1]] = (data[parts[1]] ?: 0) * 3
                    p++
                }
                "inc" -> {
                    data[parts[1]] = (data[parts[1]] ?: 0) + 1
                    p++
                }
                "jmp" -> {
                    p += parts[1].toInt()
                }
                "jie" -> {
                    if ((data[parts[1]] ?: 1) %2 == 0)
                        p += parts[2].toInt()
                    else
                        p++
                }
                "jio" -> {
                    if ((data[parts[1]] ?: 0) == 1)
                        p += parts[2].toInt()
                    else
                        p++
                }
            }
        }
        return data
    }

    override fun partOne(): Any {
        val program = inputList
        val data = run(program, mutableMapOf("a" to 0, "b" to 0))
        return data["b"] ?: 0
    }

    override fun partTwo(): Any {
        val program = inputList
        val data = run(program, mutableMapOf("a" to 1, "b" to 0))
        return data["b"] ?: 0
    }
}
