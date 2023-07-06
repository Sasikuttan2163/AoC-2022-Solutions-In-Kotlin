fun main() {
    val input = readInput("Day11").map { it.trim() }
    var inspectCount = mutableListOf<Int>()
    fun originalWorryList(): MutableList<MutableList<Long>> = input.let { monkeys ->
        monkeys.filter { it.startsWith("Starting") }
            .fold(mutableListOf()) { monkeys, line ->
                inspectCount.add(0)
                monkeys.apply {
                    add(
                        line.substringAfter(":")
                            .trim()
                            .split(",")
                            .map { it.trim().toLong() }
                            .toMutableList()
                    )
                }
            }

    }

    var worryList = originalWorryList()
    val superModulo = input
        .filter { it.startsWith("Test") }
        .map { it.substringAfterLast(" ").toInt() }
        .reduce { acc, elem ->
            acc * elem
        }

    fun reduce(part: Int) {
        input.map { it.trim() }
            .filter {
                it != "" && !(it.startsWith("Monkey") || it.startsWith("Start"))
            }
            .map {
                if (it.startsWith("Operation")) {
                    it.substringAfter("old")
                } else {
                    it.substringAfterLast(" ")
                }
            }
            .chunked(4)
            .forEachIndexed { index, monkey ->
                worryList[index] = worryList[index]
                    .map { worry ->
                        inspectCount[index]++
                        (worry operate monkey[0]).let {
                            if (part == 1) it / 3 else it % superModulo
                        }
                    }
                    .toMutableList()
                worryList[index].forEach { worry ->
                    worryList[monkey[if (worry % monkey[1].toInt() == 0L) 2 else 3].toInt()].add(worry)
                }
                worryList[index] = mutableListOf()
            }
    }

    repeat(20) {
        reduce(1)
    }

    val part1 = inspectCount.sorted().takeLast(2).fold(1) { product, i -> product * i }
    worryList = originalWorryList()
    inspectCount = inspectCount.map { 0 }.toMutableList()
    repeat(10_000) {
        reduce(2)
    }
    val part2 = inspectCount.sorted().takeLast(2).fold(1L) { acc, elem -> acc * elem }

    part1.println()
    part2.println()
}

infix fun Long.operate(operation: String): Long {
    return operation
        .trim()
        .let {
            if (it.contains("old")) {
                this * this
            } else {
                when (it[0]) {
                    '+' -> this + it.substringAfter(" ").toInt()
                    '-' -> this - it.substringAfter(" ").toInt()
                    else -> this * it.substringAfter(" ").toInt()
                }
            }
        }
}