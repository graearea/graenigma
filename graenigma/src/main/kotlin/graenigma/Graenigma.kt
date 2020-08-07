package greanigma

import graenigma.Encoder
import graenigma.Programme
import java.awt.BorderLayout
import java.awt.Rectangle
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea

fun main() {
    val frame = Graenigma()
    frame.isVisible=true
}

class Graenigma :JFrame(){

    val encoder = Encoder()
    init {
        createUI(title)
    }

    private fun createUI(title: String) {
        this.layout=null
            encoder.load(Programme.TypeWriter())
        setTitle(title)

        val input = JTextArea()

        val inputPane = JScrollPane(input)
        inputPane.bounds= Rectangle(200,20,400,120)

        val output = JTextArea()
        val outputPane = JScrollPane(output)
        outputPane.bounds= Rectangle(200,400,400,120)

        val inputChangedListener = object : KeyListener{
            override fun keyTyped(e: KeyEvent?) {
                output.text = encoder.encode(input.text)
            }
            override fun keyPressed(e: KeyEvent?) {}
            override fun keyReleased(e: KeyEvent?) {}
        }
        input.addKeyListener(inputChangedListener)


        this.contentPane.add(inputPane,BorderLayout.CENTER)
        this.contentPane.add(outputPane,BorderLayout.CENTER)
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(800, 600)
        setLocationRelativeTo(null)
    }

    fun run() {
        print("hello there")
        JFrame()
    }
}
