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
import javax.swing.text.AbstractDocument
import javax.swing.text.AttributeSet
import javax.swing.text.DocumentFilter


fun main() {
    val frame = GraeNigma()
    frame.isVisible = true
}

class GraeNigma : JFrame() {

    val encoder = Encoder()

    init {
        createUI(title)
    }

    private fun createUI(title: String) {
        this.layout = null
        encoder.load(Programme.TypeWriter())
        setTitle(title)

        val input = JTextArea()
        (input.document as AbstractDocument).setDocumentFilter(MyFilter())
        val inputPane = JScrollPane(input)
        inputPane.bounds = Rectangle(200, 20, 400, 120)

        val output = JTextArea()
        val outputPane = JScrollPane(output)
        outputPane.bounds = Rectangle(200, 400, 400, 120)

        val inputChangedListener = object : KeyListener {
            override fun keyTyped(e: KeyEvent) {
                if (('a'.toInt()..'z'.toInt()).contains(e.keyChar.toInt())) {
                    output.text = output.text + encoder.encode(e.keyChar)
                }
            }

            override fun keyPressed(e: KeyEvent?) {}
            override fun keyReleased(e: KeyEvent?) {}
        }
        input.addKeyListener(inputChangedListener)


        this.contentPane.add(inputPane, BorderLayout.CENTER)
        this.contentPane.add(outputPane, BorderLayout.CENTER)
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(800, 600)
        setLocationRelativeTo(null)
    }

}

internal class MyFilter : DocumentFilter() {
    override fun replace(
        fb: FilterBypass, offset: Int, length: Int,
        text: String, attrs: AttributeSet?
    ) {
        if (offset < fb.document.length) {
            return
        }
        if (('a'.toInt()..'z'.toInt()).contains(text[0].toInt())) {
            super.replace(fb, offset, length, text, attrs)
        }
    }


    override fun insertString(fb: FilterBypass?, offset: Int, text: String, attr: AttributeSet?) {}


    override fun remove(fb: FilterBypass, offset: Int, length: Int) {}
}
