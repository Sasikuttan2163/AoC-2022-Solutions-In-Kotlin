fun main() {
    val input = readInput("Day02")
    val part1 = input.fold(0) { sum, l ->
        sum + when ((l[2] - 'X') - (l[0] - 'A')) {
            0 -> 3
            1, -2 -> 6
            else -> 0
        } + (l[2] - 'W')
    }
    val part2 = input.fold(0) { sum, l ->
        val res = l[2] - 'X'
        sum + res * 3 + when (res) {
            0 -> if (l[0] != 'A') l[0] - 'A' else 3
            1 -> l[0] - 'A' + 1
            2 -> if (l[0] != 'C') l[0] - 'A' + 2 else 1
            else -> 0
        }
    }
    part1.println()
    part2.println()
}
