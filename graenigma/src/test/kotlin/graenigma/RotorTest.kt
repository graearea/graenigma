package graenigma

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RotorTest {
    val enigma = Encoder().load(Programme.SingleRotor())

    @org.junit.jupiter.api.Test
    internal fun `should return whatever you give it`() {
        //"DMTWSILRUYQNKFEJCAZBPGXOHV"
        assertEquals('D',enigma.encode('a'))
        assertEquals('M',enigma.encode('a'))
        assertEquals('T',enigma.encode('a'))
    }
//
//    @org.junit.jupiter.api.Test
//    internal fun `straight rotor increments`() {
//        val enigma = Encoder().load(Programme.SingleRotor())
//        //"DMTWSILRUYQNKFEJCAZBPGXOHV"
//        assertEquals('A',enigma.encode('a'))
//        assertEquals('B',enigma.encode('a'))
//        assertEquals('C',enigma.encode('a'))
//    }

    @Test
    internal fun `rotor should reset`() {
        assertEquals('D',enigma.encode('a'))
        (1..25).forEach{

            enigma.encode('a')
        }

        assertEquals('D',enigma.encode('a'))
    }

    @Test
    internal fun `decode should reset`() {
        assertEquals('A',enigma.decode('D'))
        (1..25).forEach{
            enigma.decode('a')
        }

        assertEquals('A',enigma.decode('D'))
    }

    @Test
    internal fun `rotor can decode`() {

        assertEquals('A',enigma.decode('D'))
        assertEquals('A',enigma.decode('M'))
        assertEquals('A',enigma.decode('T'))
    }

}

