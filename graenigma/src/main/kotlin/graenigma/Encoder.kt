package graenigma

class Encoder {
    private var loadedProgramme: Programme? = null

    fun encode(input: Char): Char =
        loadedProgramme?.run { encode(input) } ?: throw RuntimeException("No Programme Loaded")

    fun load(prog: Programme): Encoder {
        loadedProgramme = prog
        return this
    }
}

sealed class Programme {
    val rotor1 = "DMTWSILRUYQNKFEJCAZBPGXOHV"
    abstract fun encode(input: Char): Char
    class TypeWriter : Programme() {
        override fun encode(input: Char): Char = input
    }

    class RunningKey : Programme() {
        override fun encode(input: Char): Char {
            return rotor1[input.toInt() - 'a'.toInt()]
        }

    }

    class SingleRotor : Programme() {
        var offset=0
        override fun encode(input: Char): Char {
            return rotor1[input.toInt() - 'a'.toInt()+(offset++)%26]
        }

    }

}
