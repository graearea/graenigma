package greanigma

import graenigma.Encoder
import graenigma.Programme.SingleRotor
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Rectangle
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.JTextField
import javax.swing.text.AbstractDocument
import javax.swing.text.AttributeSet
import javax.swing.text.DocumentFilter

enum class Mode{ENCODE,DECODE}

fun main() {
    val encoder = GraeNigma(Mode.ENCODE)
    encoder.isVisible = true
    encoder.bounds=Rectangle(500, 100, 500, 600)
    val decoder = GraeNigma(Mode.DECODE)
    decoder.isVisible = true
    decoder.bounds=Rectangle(500, 800, 500, 600)
}

class GraeNigma(mode: Mode) : JFrame() {

    val encoder = Encoder()

    init {
        createUI(mode)
    }

    private fun createUI(mode: Mode) {
        layout = null
        encoder.load(SingleRotor())
        setTitle(mode.toString())

        var input = JTextArea()
        (input.document as AbstractDocument).setDocumentFilter(MyFilter())
        input.lineWrap=true
        val inputPane = JScrollPane(input)
        inputPane.bounds = Rectangle(50, 20, 400, 120)

        val output = JTextArea()
        output.lineWrap=true
        val outputPane = JScrollPane(output)
        outputPane.bounds = Rectangle(50, 400, 400, 120)

        val top = sequenceOf('Q','W','E','R','T','Y','U','I','O','P')
        val mid = sequenceOf('A','S','D','F','G','H','J','K','L')
        val bot = sequenceOf('Z','X','C','V','B','N','M')

        val buttons= listOf(top,mid,bot)

        val buttonToText= mutableMapOf<Char,JTextField>()

        buttons.forEachIndexed { row_idx, row->
            row.forEachIndexed { idx, it ->
                val bob = JTextField(it.toString())
                bob.setBounds(Rectangle(idx * 22 + 150 +row_idx*10, 200 + (row_idx*40), 22, 20))
                this.contentPane.add(bob, BorderLayout.CENTER)
                buttonToText.put(it, bob)
            }
        }


        val inputChangedListener = object : KeyListener {
            override fun keyTyped(e: KeyEvent) {
                buttonToText.values.forEach { it.background=Color.WHITE }
                if (('A'.toInt()..'Z'.toInt()).contains(e.keyChar.toUpperCase().toInt())) {
                    val encoded = encoder.encode(e.keyChar.toUpperCase())
                    buttonToText[encoded]!!.background= Color.RED
                    output.text = output.text + encoded
                }
            }

            override fun keyPressed(e: KeyEvent?) {}
            override fun keyReleased(e: KeyEvent?) {}
        }
        input.addKeyListener(inputChangedListener)


        this.contentPane.add(inputPane, BorderLayout.CENTER)
        this.contentPane.add(outputPane, BorderLayout.CENTER)
        defaultCloseOperation = EXIT_ON_CLOSE

//        setSize(500, 600)
        setLocationRelativeTo(null)
    }

}

internal class MyFilter : DocumentFilter() {
    override fun replace(
        fb: FilterBypass, offset: Int, length: Int,
        text: String, attrs: AttributeSet?
    ) {
        if (offset >= fb.document.length && ('A'.toInt()..'Z'.toInt()).contains(text[0].toUpperCase().toInt())) {
            super.replace(fb, offset, length, text.toUpperCase(), attrs)
        }
    }


    override fun insertString(fb: FilterBypass?, offset: Int, text: String, attr: AttributeSet?) {}


    override fun remove(fb: FilterBypass, offset: Int, length: Int) {}
}
