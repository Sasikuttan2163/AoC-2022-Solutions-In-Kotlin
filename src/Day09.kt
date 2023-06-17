import java.awt.Point
import kotlin.math.sign
import kotlin.math.sqrt

fun main() {
    val input = readInput("Day09")
    val head = Point(0, 0)
    val tail = Point(0, 0)
    var visitedPoints = mutableListOf(Point(0, 0))
    input.forEach { line ->
        line.substringAfter(" ").toInt().let {
            repeat(it) {
                when (line[0]) {
                    'R' -> head.x++
                    'L' -> head.x--
                    'U' -> head.y++
                    'D' -> head.y--
                }
                if (head.distance(tail) > sqrt(2.0)) {
                    tail.translate(sign(head.x - tail.x.toDouble()).toInt(), sign(head.y - tail.y.toDouble()).toInt())
                    visitedPoints.add(tail.clone() as Point)
                }
            }
        }
    }
    val part1 = visitedPoints.toSet().count()
    part1.println()
    
    visitedPoints = mutableListOf(Point(0,0))
    val points = List(10) { Point(0, 0) } // points[0] is the head and points[9] is the tail
    input.forEach { line ->
        line.substringAfter(" ").toInt().let {
            repeat(it) {
                when (line[0]) {
                    'R' -> points[0].x++
                    'L' -> points[0].x--
                    'U' -> points[0].y++
                    'D' -> points[0].y--
                }
                for (i in 1..9) {
                    if (points[i - 1].distance(points[i]) > sqrt(2.0)) {
                        points[i].translate(
                            sign(points[i - 1].x - points[i].x.toDouble()).toInt(),
                            sign(points[i - 1].y - points[i].y.toDouble()).toInt()
                        )
                    }
                }
                visitedPoints.add(points[9].clone() as Point)
            }
        }
    }
    val part2 = visitedPoints.toSet().count()
    part2.println()
}
