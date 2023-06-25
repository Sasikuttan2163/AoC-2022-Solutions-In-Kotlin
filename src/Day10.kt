fun main() {
    val input = readInput("Day10")
    var cycleCount = 1
    val keyframes = mutableListOf(20, 60, 100, 140, 180, 220)
    var x = 1
    val instructionQueue = mutableListOf<Pair<Int, Int>>()
    input.forEach { line ->
        cycleCount += if (line.startsWith("addx")) {
            instructionQueue.add(Pair(cycleCount + 2, line.substringAfter(" ").toInt()))
            2
        } else {
            1
        }
    }
    val part1 = (2..220).toList().sumOf { cycle ->
        x += instructionQueue.firstOrNull { it.first == cycle }?.second ?: 0
        if (keyframes.contains(cycle)) {
            x * cycle
        } else {
            0
        }
    }
    part1.println()
    x = 1
    val part2 = (1..240).fold("") { crt, cycle ->
        x += instructionQueue.firstOrNull { it.first == cycle }?.second ?: 0
        crt + if ((x - 1..x + 1).toList().contains((cycle - 1) % 40)) {
            "#"
        } else {
            "."
        }
    }.chunked(40).joinToString(separator = "\n")
    part2.println()
}
