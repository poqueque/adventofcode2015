package days

import util.Instruction
import util.Operator
import util.Value

class Day07 : Day(7) {

    val wires = mutableMapOf<String, Int>()
    val instructions = mutableMapOf<String, Instruction>()

    override fun partOne(): Any {
        inputList.forEach {
            processInstructions(it)
        }
        return evaluate(Value("a"))
    }

    private fun evaluate(value: Value): Int {
        if (value.isNumber) return value.integer!!
        if (wires[value.string] != null) return wires[value.string]!!

        val instruction = instructions[value.string]!!
        if (instruction.op == null) {
            val retVal = evaluate(instruction.value1!!)
            wires[value.string!!] = retVal
            return retVal
        }
        val retVal =  when (instruction.op.op) {
            "AND" -> evaluate(instruction.value1!!) and evaluate(instruction.value2!!)
            "OR" -> evaluate(instruction.value1!!) or evaluate(instruction.value2!!)
            "LSHIFT" -> evaluate(instruction.value1!!) shl evaluate(instruction.value2!!)
            "RSHIFT" -> evaluate(instruction.value1!!) shr evaluate(instruction.value2!!)
            "NOT" -> evaluate(instruction.value1!!).inv() + 65536
            else -> Int.MIN_VALUE
        }
        wires[value.string!!] = retVal
        return retVal
    }

    private fun processInstructions(it: String) {
        val (s, p) = it.split("->")
        val leftSide = s.trim().split(" ").map { it.trim()}
        //Process left side
        var value1: Value? = null
        var value2: Value? = null
        var operator: Operator? = null
        var hasSetVal1 = false
        leftSide.forEach {
            when (it) {
                "AND","OR","LSHIFT","RSHIFT","NOT" -> operator = Operator(it)
                else -> if (hasSetVal1) value2 = Value(it) else {
                    value1 = Value(it)
                    hasSetVal1 = true
                }
            }
            instructions[p.trim()] = Instruction(value1 = value1, value2 = value2, op = operator)
        }
    }

    override fun partTwo(): Any {
        instructions.clear()
        wires.clear()
        inputList.forEach {
            processInstructions(it)
        }
        wires["b"] = 46065
        return evaluate(Value("a"))
    }
}
