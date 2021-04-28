package uk.co.snowballconsultancy.draw.utils

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import uk.co.snowballconsultancy.draw.domain.Canvas
import uk.co.snowballconsultancy.draw.domain.Line
import uk.co.snowballconsultancy.draw.domain.Point

internal class LineRendererTest{

    private lateinit var lineRenderer: LineRenderer

    @BeforeEach
    fun setup(){
        lineRenderer = LineRenderer()
        Canvas.width = 0
        Canvas.height = 0
        Canvas.lines = mutableListOf()
    }

    @Test
    fun firstAndLastLineIsHyphens(){

        Canvas.width = 20
        Canvas.height = 6

        var lines = lineRenderer.build()

        assertThat(lines.size, equalTo(6))
        assertThat(lines[0], equalTo("--------------------"))
        assertThat(lines[5], equalTo("--------------------"))
    }

    @Test
    fun blankCanvasRenders(){

        Canvas.width = 20
        Canvas.height = 6

        var lines = lineRenderer.build()

        assertThat(lines.size, equalTo(6))
        assertThat(lines[0], equalTo("--------------------"))
        assertThat(lines[1], equalTo("|                  |"))
        assertThat(lines[2], equalTo("|                  |"))
        assertThat(lines[3], equalTo("|                  |"))
        assertThat(lines[4], equalTo("|                  |"))
        assertThat(lines[5], equalTo("--------------------"))
    }

    @Test
    fun blankCanvasRendersDifferentSizes(){

        Canvas.width = 10
        Canvas.height = 5

        var lines = lineRenderer.build()

        assertThat(lines.size, equalTo(5))
        assertThat(lines[0], equalTo("----------"))
        assertThat(lines[1], equalTo("|        |"))
        assertThat(lines[2], equalTo("|        |"))
        assertThat(lines[3], equalTo("|        |"))
        assertThat(lines[4], equalTo("----------"))
    }

    @Test
    fun horizontalLineRenders(){

        var linePoints = mutableListOf(
                Point(1,2),
                Point(2,2),
                Point(3,2),
                Point(4,2),
                Point(5,2),
                Point(6,2)
        )

        Canvas.width = 20
        Canvas.height = 6
        Canvas.lines = mutableListOf(Line(linePoints))

        var lines = lineRenderer.build()

        assertThat(lines.size, equalTo(6))
        assertThat(lines[0], equalTo("--------------------"))
        assertThat(lines[1], equalTo("|                  |"))
        assertThat(lines[2], equalTo("|xxxxxx            |"))
        assertThat(lines[3], equalTo("|                  |"))
        assertThat(lines[4], equalTo("|                  |"))
        assertThat(lines[5], equalTo("--------------------"))
    }

    @Test
    fun verticalLineRenders(){

        var linePoints = mutableListOf(
                Point(6,3),
                Point(6,4)
        )

        Canvas.width = 20
        Canvas.height = 6
        Canvas.lines = mutableListOf(Line(linePoints))

        var lines = lineRenderer.build()

        assertThat(lines.size, equalTo(6))
        assertThat(lines[0], equalTo("--------------------"))
        assertThat(lines[1], equalTo("|                  |"))
        assertThat(lines[2], equalTo("|                  |"))
        assertThat(lines[3], equalTo("|     x            |"))
        assertThat(lines[4], equalTo("|     x            |"))
        assertThat(lines[5], equalTo("--------------------"))
    }
}