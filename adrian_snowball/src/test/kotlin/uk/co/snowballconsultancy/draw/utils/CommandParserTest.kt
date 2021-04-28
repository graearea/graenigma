package uk.co.snowballconsultancy.draw.utils


import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import uk.co.snowballconsultancy.draw.domain.Canvas
import uk.co.snowballconsultancy.draw.exception.InvalidCommandException
import uk.co.snowballconsultancy.draw.utils.CommandParser

internal class CommandParserTest {

    private lateinit var commandParser: CommandParser

    @BeforeEach
    fun setup() {
        commandParser = CommandParser()
        Canvas.height = 0
        Canvas.width = 0
        Canvas.lines = mutableListOf()
    }

    @Test
    fun weThrowInvalidCommandExceptionWhenEmptyString() {

        val exception = assertThrows<InvalidCommandException> {
            commandParser.process("")
        }
        assertThat(exception.message, equalTo("You must provide a command."))
    }

    @Test
    fun weThrowExceptionWhenTryToDrawWithoutCanvas() {

        val exception = assertThrows<InvalidCommandException> {
            commandParser.process("L 1 2 3 4")
        }
        assertThat(exception.message, equalTo("You must create a canvas before drawing."))

    }

    @Test
    fun weThrowExceptionWhenTryToCreateCanvasWhenAlreadyPresent() {

        commandParser.process("C 5 10")

        val exception = assertThrows<InvalidCommandException> {
            commandParser.process("C 5 10")
        }
        assertThat(exception.message, equalTo("A canvas already exists and can't be modified."))

    }

    @Test
    fun weInitializeCanvas() {

        commandParser.process("C 5 10")

        assertThat(Canvas.width, equalTo(7))
        assertThat(Canvas.height, equalTo(12))
    }

    @Test
    fun weInitializeCanvasOfDifferentSizes() {

        commandParser.process("C 20 30")

        assertThat(Canvas.width, equalTo(22))
        assertThat(Canvas.height, equalTo(32))
    }

    @Test
    fun weCanAddHorizontalLineToCanvas() {

        commandParser.process("C 30 30")
        commandParser.process("L 1 2 6 2")

        assertThat(Canvas.lines.size, equalTo(1))
        assertThat(Canvas.lines[0].points.size, equalTo(6))
    }

    @Test
    fun weCanAddVerticalLineToCanvas() {

        commandParser.process("C 5 10")
        commandParser.process("L 1 2 1 6")

        assertThat(Canvas.lines.size, equalTo(1))
        assertThat(Canvas.lines[0].points.size, equalTo(5))
    }

    @Test
    fun weCanAddRectangleToCanvas() {

        commandParser.process("C 5 10")
        commandParser.process("R 1 1 5 4")

        assertThat(Canvas.lines.size, equalTo(4))
    }
}