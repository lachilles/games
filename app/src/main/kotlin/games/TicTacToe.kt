/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package games

class TicTacToe {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main() {
    println(TicTacToe().greeting)
    val board = Board()
    TextDisplayer().display(board)
}
