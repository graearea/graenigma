package uk.co.snowballconsultancy.draw.utils

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import uk.co.snowballconsultancy.draw.domain.Canvas
import uk.co.snowballconsultancy.draw.domain.Point
import uk.co.snowballconsultancy.draw.exception.InvalidCommandException
import uk.co.snowballconsultancy.draw.utils.LineBuilder

internal class LineBuilderTest {


    @Test
    fun weCanAddHorizontalLineToCanvas() {

        Canvas.width = 30
        Canvas.height = 30

        var line = LineBuilder.build("L 1 2 6 2")

        MatcherAssert.assertThat(line.points.size, equalTo(6))
        MatcherAssert.assertThat(line.points[0], equalTo(Point(1, 2)))
        MatcherAssert.assertThat(line.points[1], equalTo(Point(2, 2)))
        MatcherAssert.assertThat(line.points[2], equalTo(Point(3, 2)))
        MatcherAssert.assertThat(line.points[3], equalTo(Point(4, 2)))
        MatcherAssert.assertThat(line.points[4], equalTo(Point(5, 2)))
        MatcherAssert.assertThat(line.points[5], equalTo(Point(6, 2)))
    }

    @Test
    fun weCanAddVerticalLineToCanvas() {

        Canvas.width = 30
        Canvas.height = 30

        var line = LineBuilder.build("L 1 2 1 6")

        MatcherAssert.assertThat(line.points.size, equalTo(5))
        MatcherAssert.assertThat(line.points[0], equalTo(Point(1, 2)))
        MatcherAssert.assertThat(line.points[1], equalTo(Point(1, 3)))
        MatcherAssert.assertThat(line.points[2], equalTo(Point(1, 4)))
        MatcherAssert.assertThat(line.points[3], equalTo(Point(1, 5)))
        MatcherAssert.assertThat(line.points[4], equalTo(Point(1, 6)))
    }

    @Test
    fun weThrowExceptionWhenLineWidthExceedsCanvas() {
        Canvas.width = 3

        val exception = assertThrows<InvalidCommandException> {
            LineBuilder.build("L 1 1 3 1")
        }
        MatcherAssert.assertThat(exception.message, equalTo("Line exceeds canvas drawable area."))
    }


    @Test
    fun weThrowExceptionWhenLineHeightExceedsCanvas() {
        Canvas.height = 3

        val exception = assertThrows<InvalidCommandException> {
            LineBuilder.build("L 1 1 1 3")
        }
        MatcherAssert.assertThat(exception.message, equalTo("Line exceeds canvas drawable area."))
    }

    @Test
    fun weThrowExceptionWhenLineStartExceedsCanvasLeftBoundary() {
        Canvas.width = 30

        val exception = assertThrows<InvalidCommandException> {
            LineBuilder.build("L 0 1 3 1")
        }
        MatcherAssert.assertThat(exception.message, equalTo("Line exceeds canvas drawable area."))
    }


    @Test
    fun weThrowExceptionWhenLineStartExceedsCanvasTopBoundary() {
        Canvas.height = 30

        val exception = assertThrows<InvalidCommandException> {
            LineBuilder.build("L 3 0 3 10")
        }
        MatcherAssert.assertThat(exception.message, equalTo("Line exceeds canvas drawable area."))
    }

}