fun main() {
    val input = readInput("Day06")
    fun packetFinder(packetLength: Int): Int {
        return input[0]
            .withIndex()
            .windowed(packetLength, 1).first { w ->
                w.map { it.value }.toSet().size == packetLength
            }
            .last()
            .index + 1
    }
    val part1 = packetFinder(4)
    val part2 = packetFinder(14)
    part1.println()
    part2.println()
}