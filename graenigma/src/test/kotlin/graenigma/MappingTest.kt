package graenigma

import org.junit.jupiter.api.Assertions.assertEquals

internal class MappingTest {
    @org.junit.jupiter.api.Test
    internal fun `should return whatever you give it`() {
        //"DMTWSILRUYQNKFEJCAZBPGXOHV"
        assertEquals(Encoder().load(Programme.RunningKey()).encode('a'),'D')
        assertEquals(Encoder().load(Programme.RunningKey()).encode('b'),'M')
        assertEquals(Encoder().load(Programme.RunningKey()).encode('c'),'T')
    }
}

