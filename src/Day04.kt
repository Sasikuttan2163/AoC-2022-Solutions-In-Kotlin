fun main() {
    val input = readInput("Day04")
    val part1 = input.count { l ->
        val list = splitInTwo(l)
        (list[0][0] <= list[1][0] && list[0][1] >= list[1][1]) || (list[0][0] >= list[1][0] && list[0][1] <= list[1][1])
    }
    val part2 = input.count { l ->
        val list = splitInTwo(l)
        (list[0][0]..list[0][1]).intersect(list[1][0]..list[1][1]).isNotEmpty()
    }
    part1.println()
    part2.println()
}
fun splitInTwo(line: String): List<List<Int>> {
    line.split(",").let { it ->
        val first = it[0].trim().split("-").map { it.toInt() }.toList()
        val second = it[1].trim().split("-").map { it.toInt() }.toList()
        return listOf(first, second)
    }
}