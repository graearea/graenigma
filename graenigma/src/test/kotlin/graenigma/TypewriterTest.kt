package graenigma

import org.junit.jupiter.api.Assertions.assertEquals

internal class TypewriterTest {
    @org.junit.jupiter.api.Test
    internal fun `should return whatever you give it`() {
        assertEquals(Programme.TypeWriter().encode("abc123!"),"abc123!")
    }
}