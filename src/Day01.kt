fun main() {
    val input = readInput("Day01")
    val calories = input.joinToString (separator = " ", transform = { it })
        .split("  ")
        .map { it.split(" ").sumOf { n -> n.toInt() } }
        .toMutableList()
    val part1 = calories.max()
    val part2 = calories.sorted()
        .takeLast(3)
        .sum()
    part1.println()
    part2.println()
}
