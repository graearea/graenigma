package graenigma

import java.lang.RuntimeException

class Encoder{
    private var loadedProgramme: Programme? = null

    fun encode(input: String):String = loadedProgramme?.run { encode(input) } ?: throw RuntimeException("No Programme Loaded")
//
//        return when (loadedProgramme) {
//            null -> throw RuntimeException("No Program Loaded!")
//            else -> loadedProgramme!!.encode(input)
//        }
//
//    }
    fun load(prog:Programme) {
        loadedProgramme=prog
    }
}

sealed class Programme {
    abstract fun encode(input:String):String
    class TypeWriter: Programme() {
        override fun encode(input: String): String = input
    }
}