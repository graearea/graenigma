package uk.co.snowballconsultancy.draw

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import uk.co.snowballconsultancy.draw.exception.InvalidCommandException
import uk.co.snowballconsultancy.draw.utils.CommandParser
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.system.exitProcess


@SpringBootApplication
class DrawApplication

fun main(args: Array<String>) {

    runApplication<DrawApplication>(*args)

    val commandParser = CommandParser()

    inputCommand@ while (true) {
        val commandSuffix = "enter command: "
        print(commandSuffix)

        val reader = BufferedReader(
                InputStreamReader(System.`in`))

        val command = reader.readLine().removeSuffix(commandSuffix)

        if (command == "Q") {
            exitProcess(0)
        }
        try {
            commandParser.process(command)
        } catch (e: InvalidCommandException) {
            println(e.message)
        }
        continue@inputCommand
    }
}
