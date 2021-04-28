package uk.co.snowballconsultancy.draw.utils

import uk.co.snowballconsultancy.draw.domain.Canvas
import uk.co.snowballconsultancy.draw.exception.InvalidCommandException

class CommandParser {

    fun process(command: String) {
        var lineRenderer = LineRenderer()

        if (command == "") {
            throw InvalidCommandException("You must provide a command.")
        }

        handleCreateCanvasCommand(command)
        handleCreateLineCommand(command)
        handleCreateRectangleCommand(command)

        if (Canvas.height == 0 || Canvas.width == 0) {
            throw InvalidCommandException("You must create a canvas before drawing.")
        }

        var lines = lineRenderer.build()
        lines.forEach { line ->
            println(line)
        }

    }

    private fun handleCreateRectangleCommand(command: String) {
        val createRectangleRegex = Regex("(R) [0-9]* [0-9]* [0-9]* [0-9]*")
        if (command.matches(createRectangleRegex)) {
            val commandParts = command.split(" ")
            var x1 = commandParts[1].toInt()
            var y1 = commandParts[2].toInt()
            var x2 = commandParts[3].toInt()
            var y2 = commandParts[4].toInt()

            var top = LineBuilder.build(x1, y1, x2, y2)
            var leftSide = LineBuilder.build(x1, y1, x1, y2)
            var rightSide = LineBuilder.build(x2, y1, x2, y2)
            var bottom = LineBuilder.build(x1, y2, x2, y2)

            Canvas.lines.add(top)
            Canvas.lines.add(leftSide)
            Canvas.lines.add(rightSide)
            Canvas.lines.add(bottom)

        }
    }

    private fun handleCreateCanvasCommand(command: String) {
        val createCanvasRegex = Regex("(C) [0-9]* [0-9]*")
        if (command.matches(createCanvasRegex)) {
            if (Canvas.width != 0 || Canvas.height != 0) {
                throw InvalidCommandException("A canvas already exists and can't be modified.")
            }
            Canvas.width = extractWidth(command) + 2
            Canvas.height = extractHeight(command) + 2
        }
    }

    private fun extractHeight(command: String): Int {
        return command.split(" ")[2].toInt()
    }

    private fun extractWidth(command: String): Int {
        return command.split(" ")[1].toInt()
    }

    private fun handleCreateLineCommand(command: String) {
        val createLineRegex = Regex("(L) [0-9]* [0-9]* [0-9]* [0-9]*")
        if (command.matches(createLineRegex) && Canvas.width > 0 && Canvas.height > 0) {
            Canvas.lines.add(LineBuilder.build(command))
        }
    }
}