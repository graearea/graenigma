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
    val enigmaRotor =   Rotor("DMTWSILRUYQNKFEJCAZBPGXOHV")
    val straight = Rotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ")

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
                return enigmaRotor.wiring[(input.toUpperCase().toInt() - 'A'.toInt()+(offset++))%26]
        }

        override fun decode(input: Char): Char {
            offset=offset%26
            val thing= (enigmaRotor.wiring.indexOf(input.toUpperCase())-(offset++)+26)%26
            return ('A'.toInt()+thing).toChar()
        }
    }


    class DoubleRotor(val rotor1: Rotor,val rotor2 : Rotor) : Programme() {
        var offset=0
        override fun encode(input: Char): Char {
            val firstEncode = rotor1.wiring[(input.toUpperCase().toInt() - 'A'.toInt() + (offset++)) % 26]
            return rotor2.wiring[(firstEncode.toInt() - 'A'.toInt() + (offset++)) % 26]
        }

        override fun decode(input: Char): Char {
            offset=offset%26
            val interim = (rotor1.wiring.indexOf(input.toUpperCase())+'A'.toInt()-(offset++)).toChar()
            return  (rotor2.wiring.indexOf(interim)+'A'.toInt()-(offset++)).toChar()
        }
    }
data class Rotor(val wiring:String)
}
