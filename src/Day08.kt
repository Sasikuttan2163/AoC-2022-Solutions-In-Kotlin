import kotlin.math.abs

fun main() {
    val input = readInput("Day08")
    var matrix = mutableListOf<MutableList<Int>>()
    var part1 = 0
    matrix = input.foldIndexed(mutableListOf<MutableList<Int>>()) { index, matrix, line ->
        matrix.add(line.map { it -> it.digitToInt() }.toMutableList())
        matrix
    }
    matrix.forEachIndexed { rowIndex, row ->
        if (rowIndex == 0 || rowIndex == input.size - 1) {
            part1 += row.size
        } else {
            row.forEachIndexed { index, height ->
                if (index == 0 || index == row.size - 1) {
                    part1++
                } else {
                    val isVisibleHorizontally = row.subList(0, index).max() < height || row.subList(index + 1, row.size).max() < height
                    val isVisibleVertically = matrix.map { it[index] }
                        .let { column ->
                            column.subList(0, rowIndex).max() < height || column.subList(rowIndex + 1, column.size).max() < height
                        }
                    if (isVisibleHorizontally || isVisibleVertically) {
                        part1++
                    }
                }
            }
        }
    }
    part1.println()

    fun vertical(rowIndex: Int, index: Int, height: Int, isUp: Boolean): Int {
        return matrix
            .map {
                it[index]
            }
            .withIndex()
            .filter {
                it.value >= height && if (isUp) it.index < rowIndex else it.index > rowIndex
            }
            .minOfOrNull {
                abs(it.index - rowIndex)
            }.let {
                if (isUp) {
                    it ?: rowIndex
                } else {
                    it ?: (matrix.size - rowIndex - 1)
                }
            }
    }

    fun horizontal(rowIndex: Int, index: Int, height: Int, isRight: Boolean): Int {
        return matrix[rowIndex]
            .withIndex()
            .filter {
                it.value >= height && if (isRight) it.index > index else it.index < index
            }
            .minOfOrNull {
                abs(it.index - index)
            }.let {
                if (isRight) {
                    it ?: (matrix[rowIndex].size - index - 1)
                } else {
                    it ?: index
                }
            }
    }
    val part2 = matrix
        .mapIndexed { rowIndex, row ->
            row.mapIndexed { index, height ->
                if (rowIndex != 0 && index != 0 && rowIndex != matrix.size - 1 && index != row.size - 1) {
                    horizontal(rowIndex, index, height, true) * horizontal(rowIndex, index, height, false) * vertical(rowIndex, index, height, true) * vertical(rowIndex, index, height,false)
                } else 0
            }
        }
        .flatMap {
            it.toList()
        }
        .max()
    part2.println()
}