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
    abstract fun encode(input: Char): Char
    class TypeWriter : Programme() {
        override fun encode(input: Char): Char = input
    }

    class RunningKey : Programme() {
        val mapping = "DMTWSILRUYQNKFEJCAZBPGXOHV"
        override fun encode(input: Char): Char {
            return mapping[input.toInt() - 'a'.toInt()]
        }

    }

}
