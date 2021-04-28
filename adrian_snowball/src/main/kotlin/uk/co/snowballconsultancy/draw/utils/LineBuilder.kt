package uk.co.snowballconsultancy.draw.utils

import uk.co.snowballconsultancy.draw.domain.Canvas
import uk.co.snowballconsultancy.draw.domain.Line
import uk.co.snowballconsultancy.draw.domain.Point
import uk.co.snowballconsultancy.draw.exception.InvalidCommandException

class LineBuilder {

    companion object {

        fun build(command: String): Line {

            var drawableWidth = Canvas.width - 2
            var drawableHeight = Canvas.height - 2

            val commandParts = command.split(" ")
            var x1 = commandParts[1].toInt()
            var y1 = commandParts[2].toInt()
            var x2 = commandParts[3].toInt()
            var y2 = commandParts[4].toInt()

            if (x1 < 1 || x2 < 1 || x1 > drawableWidth || x2 > drawableWidth) {
                throw InvalidCommandException("Line exceeds canvas drawable area.")
            }

            if (y1 < 1 || y2 < 1 || y1 > drawableHeight || y2 > drawableHeight) {
                throw InvalidCommandException("Line exceeds canvas drawable area.")
            }

            return build(x1, y1, x2, y2)
        }

        fun build(x1: Int, y1: Int, x2: Int, y2: Int): Line {

            var points = mutableListOf<Point>()

            if (x2 > x1) {
                for (x in x1..x2) {
                    points.add(Point(x, y1))
                }
            } else {
                for (y in y1..y2) {
                    points.add(Point(x1, y))
                }
            }

            return Line(points)

        }
    }
}