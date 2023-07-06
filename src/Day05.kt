import java.util.*

fun main() {
    val input = readInput("Day05")
    val start = input.indexOf("") - 1
    lateinit var data: MutableList<Stack<Char>>
    fun collectData() {
        var i = start - 1
        data = MutableList(input[start].trim().substringAfterLast(" ").toInt()) { Stack<Char>() }
        while (i >= 0) {
            var j = 1
            val line = input[i]
            while (j < line.length) {
                if (line[j] != ' ') {
                    data[(j - 1) / 4].push(line[j])
                }
                j += 4
            }
            i -= 1
        }
    }
    collectData()
    var stacks = data
    fun applyOperations(moveMultipleAtOnce: Boolean): String {
        input.filter { it.startsWith("move") }
            .forEach { l ->
                l.split(" ")
                    .filter { it.toIntOrNull() != null }
                    .map { it.toInt() }
                    .let { array ->
                        val stack = mutableListOf<Char>()
                        repeat(array[0]) {
                            stack.add(stacks[array[1] - 1].pop())
                        }

                        stacks[array[2] - 1].addAll(
                            stack.apply {
                                if (moveMultipleAtOnce) {
                                    reverse()
                                }
                            }
                        )
                    }
            }
        return stacks.fold("") { final, element ->
            final + element.last()
        }
    }
    val part1 = applyOperations(moveMultipleAtOnce = false)
    collectData()
    stacks = data
    val part2 = applyOperations(moveMultipleAtOnce = true)
    part1.println()
    part2.println()
}