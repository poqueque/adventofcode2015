package days

import util.MathUtils.variations

class Day24 : Day(24) {

    override fun partOne(): Any {
        val packages = inputList.map { s -> s.toInt()}
        val obj = packages.sum() / 3

        for (pick in 2..8) {
            val vars = variations(packages, pick)
            for (group in vars) {
                if (group.sum() == obj) {
                    return group.fold(1L) { total, next -> total * next }
                }
            }
        }
        return 0
   }

    override fun partTwo(): Any {

        val packages = inputList.map { s -> s.toInt()}
        val obj = packages.sum() / 4

        for (pick in 2..8) {
            val vars = variations(packages, pick)
            for (group in vars) {
                if (group.sum() == obj) {
                    return group.fold(1L) { total, next -> total * next }
                }
            }
        }
        return 0
    }
}
