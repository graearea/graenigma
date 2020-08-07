package graenigma

class Encoder {
    private var loadedProgramme: Programme? = null

    fun encode(input: Char): Char =
        loadedProgramme?.run { encode(input) } ?: throw RuntimeException("No Programme Loaded")

    fun load(prog: Programme): Encoder {
        loadedProgramme = prog
        return this
    }

    fun decode(input: Char): Char = loadedProgramme?.run { decode(input) } ?: throw RuntimeException("No Programme Loaded")

}

sealed class Programme {
    val rotor1 = "DMTWSILRUYQNKFEJCAZBPGXOHV"
    val straight = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    abstract fun encode(input: Char): Char
    abstract fun decode(input: Char): Char
    class TypeWriter : Programme() {
        override fun encode(input: Char): Char = input
        override fun decode(input: Char): Char {
            return input
        }
    }

    class SingleRotor() : Programme() {
        var offset=0
        override fun encode(input: Char): Char {
                return rotor1[(input.toUpperCase().toInt() - 'A'.toInt()+(offset++))%26]
        }

        override fun decode(input: Char): Char {
            return (rotor1.indexOf(input.toUpperCase())+'A'.toInt()-(offset++)).toChar()
        }
    }


    class DoubleRotor() : Programme() {
        var offset=0
        override fun encode(input: Char): Char {
                return rotor1[(input.toUpperCase().toInt() - 'A'.toInt()+(offset++))%26]
        }

        override fun decode(input: Char): Char {
            return (rotor1.indexOf(input.toUpperCase())+'A'.toInt()-(offset++)%26).toChar()
        }
    }

}
