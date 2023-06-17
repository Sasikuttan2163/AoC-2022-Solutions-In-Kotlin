fun main() {
    val calories = readInput("Day01")
        .joinToString (separator = " ", transform = { it })
        .split("  ")
        .map { it.split(" ").sumOf { n -> n.toInt() } }
    val part1 = calories.max()
    val part2 = calories.sorted()
        .takeLast(3)
        .sum()
    part1.println()
    part2.println()
}
