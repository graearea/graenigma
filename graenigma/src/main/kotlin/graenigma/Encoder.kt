package graenigma

import java.lang.RuntimeException

class Encoder{
    private var loadedProgramme: Programme? = null

    fun encode(input: String):String = loadedProgramme?.run { encode(input) } ?: throw RuntimeException("No Programme Loaded")
    fun load(prog:Programme): Encoder {
        loadedProgramme=prog
        return this
    }
}

sealed class Programme {
    abstract fun encode(input:String):String
    class TypeWriter: Programme() {
        override fun encode(input: String): String = input
    }

    class RunningKey : Programme() {
        val mapping= "DMTWSILRUYQNKFEJCAZBPGXOHV"
        override fun encode(input: String): String  {
            return input.toLowerCase().map { c -> mapping[c.toInt() - 'a'.toInt()] }.joinToString("")

        }

    }
}