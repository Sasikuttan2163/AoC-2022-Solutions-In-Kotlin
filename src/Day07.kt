fun main() {
    val input = readInput("Day07")
    val root = Node(
        type = "dir",
        name = "/",
        parent = null
    )
    var current = root
    var inls = false
    input.withIndex()
        .forEach { line ->
            if (line.value.startsWith("$ ls")) {
                inls = true
            } else if (line.value.startsWith("$ cd")) {
                inls = false
                current = line.value.substringAfterLast(" ").trim().let {
                    if (it == "/") root
                    else if (it == "..") current.parent
                    else current.sub.first { node ->
                        node.name == it && node.type == "dir"
                    }
                }!!
            } else if (inls) {
                current.add(
                    Node(parent = current).apply {
                        name = line.value.substringAfter(" ")
                        if (line.value.startsWith("dir")) {
                            type = "dir"
                        } else {
                            type = "file"
                            size = line.value.substringBefore(" ").toInt()
                        }
                    }
                )
            }
        }
    val part1 = root.computeSum()
    part1.println()
    val sizeRequired = 30000000 - (70000000 - root.computeSize())
    sizeRequired.println()
    val part2 = root.smallestDirWithSizeMoreThan(sizeRequired, root.size)
    part2.println()
}
data class Node (var type: String = "file", var size: Int = 0, var sub: MutableList<Node> = mutableListOf<Node>(), var name: String = "", var parent: Node?) {
    fun computeSize(): Int = if (type == "dir") {
        size = sub.sumOf {
            it.computeSize()
        }
        size
    } else size
    fun add(node: Node) {
        sub.add(node)
    }
    fun add(type: String = "file", size: Int = 0, name: String = "", parent: Node) {
        sub.add(Node(
            type = type,
            size = size,
            name = name,
            parent = parent
        ))
    }
    var part1 = 0
    fun computeSum(): Int {
        var sum = 0
        val size = this.computeSize()
        if (type == "dir" && size <= 100000) {
            sum += size
        }
        for (node in sub) {
            sum += node.computeSum()
        }
        return sum
    }
    fun smallestDirWithSizeMoreThan(min: Int, currentSmallest: Int): Int {
        if (size < min || type != "dir") {
            return currentSmallest
        }
        var smallest = this.size
        for (node in sub) {
            var small = node.smallestDirWithSizeMoreThan(min, smallest)
            if (small < smallest) {
                smallest = small
            }
        }
        return smallest
    }
}