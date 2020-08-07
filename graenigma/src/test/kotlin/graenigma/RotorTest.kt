package graenigma

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RotorTest {
    val rotor = Encoder().load(Programme.SingleRotor())
    @org.junit.jupiter.api.Test
    internal fun `should return whatever you give it`() {
        //"DMTWSILRUYQNKFEJCAZBPGXOHV"
        val rotor = Encoder().load(Programme.SingleRotor())
        assertEquals('D',rotor.encode('a'))
        assertEquals('M',rotor.encode('a'))
        assertEquals('T',rotor.encode('a'))
    }

    @Test
    internal fun `rotor should reset`() {
        assertEquals('D',rotor.encode('a'))
        (1..25).forEach{

            rotor.encode('a')
        }

        assertEquals('D',rotor.encode('a'))
    }
}

