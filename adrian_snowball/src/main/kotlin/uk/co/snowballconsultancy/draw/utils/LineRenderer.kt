package uk.co.snowballconsultancy.draw.utils

import uk.co.snowballconsultancy.draw.domain.Canvas
import uk.co.snowballconsultancy.draw.domain.Point

class LineRenderer {

    fun build(): List<String> {
        var lines = mutableListOf<String>()
        for (rowNumber in 1..Canvas.height) {
            if (rowNumber == 1 || rowNumber == Canvas.height) {
                lines.add(buildTopOrBottomBorder())
            } else {
                lines.add(buildInternalRow(rowNumber))
            }
        }
        return lines
    }

    private fun buildInternalRow(rowNumber: Int): String {
        var rowNumberWithMargin = rowNumber - 1
        var result = ""
        for (columnNumber in 1..Canvas.width) {
            var columnNumberWithMargin = columnNumber - 1
            if (columnNumber == 1 || columnNumber == Canvas.width) {
                result = "$result|"
            } else {
                result += renderPointOrSpace(columnNumberWithMargin, rowNumberWithMargin)
            }
        }
        return result
    }

    private fun renderPointOrSpace(columnNumberWithMargin: Int, rowNumberWithMargin: Int): String {
        var allPoints = mutableListOf<Point>()
        Canvas.lines.forEach { line -> allPoints.addAll(line.points) }
        var currentPoint = Point(columnNumberWithMargin, rowNumberWithMargin)
        return if(allPoints.contains(currentPoint)) "x" else " "
    }

    private fun buildTopOrBottomBorder(): String {
        var result = ""
        for (columnNumber in 1..Canvas.width) {
            result += "-"
        }
        return result
    }

}