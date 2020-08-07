import java.awt.BorderLayout
import java.awt.Rectangle
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea

fun main() {
    val frame = Graenigma()
    frame.isVisible=true
}

class Graenigma :JFrame(){

    init {
        createUI(title)
    }

    private fun createUI(title: String) {
        this.layout=null

        setTitle(title)

        val input = JTextArea()
        val inputPane = JScrollPane(input)
        inputPane.bounds= Rectangle(200,20,400,120)

        val output = JTextArea()
        val outputPane = JScrollPane(output)
        outputPane.bounds= Rectangle(200,400,400,120)

        this.contentPane.add(inputPane,BorderLayout.CENTER)
        this.contentPane.add(outputPane,BorderLayout.CENTER)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setSize(800, 600)
        setLocationRelativeTo(null)
    }

    fun run() {
        print("hello there")
        JFrame()
    }
}
