fun main() {
    val input = readInput("Day03")
    val part1 = input.sumOf { l ->
        l.substring(0, l.length / 2).toSet().intersect(l.substring(l.length / 2).toSet()).first().let {
            if (it.isLowerCase()) it - 'a' + 1 else it - 'A' + 27
        }
    }
    val part2 = input.chunked(3).sumOf { list ->
        list[0].toSet().intersect(list[1].toSet()).intersect(list[2].toSet()).first().let {
            if (it.isLowerCase()) it - 'a' + 1 else it - 'A' + 27
        }
    }
    part1.println()
    part2.println()
}