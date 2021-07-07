/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package games

class TicTacToe(
        val player1: Player = HumanPlayer(name = "Lianne", id = 1, TextInputController()),
        val player2: Player = ComputerPlayer(name = "Paul", id = 2, AnyOpenSquare())
) {
    private val greeting = "Welcome to the Tic Tac Toe game!"
    internal val board = Board()
    private val displayer = TextDisplayer()
    private var winner:Player? = null

    //    /// Print the updated board after every move
    fun printBoard() {
        return displayer.display(board)
    }

    //
    fun playGame() {
        val moveHistory = mutableListOf<MoveCommand>()
        println(greeting) /// greeting
        printBoard()
        var moves = 0
        val players = listOf(player1, player2)
        while(moves < 9) {
            val curPlayer = players[moves % 2]
            val validMoves = board.getValidMoves()
            try {
                val move = curPlayer.makeMove(validMoves)
                val command = MoveCommand(board, curPlayer, move)
                command.apply()
                moveHistory.add(command)
                moves++
                winner = WinnerDetector().detectWinner(board, player1, player2)
                if (winner != null) {
                    print("${winner?.name} has Won!")
                    break
                }
            } catch (u: UndoException) {
                if (moveHistory.isNullOrEmpty()) {
                    print("Cannot undo with no moves. \n")
                }
                else {
                    val lastCommand = moveHistory.removeLast()
                    lastCommand.undo()
                    moves--
                }
            }
            printBoard()
        }
    }

    fun takeTurn(p: Player, move: Move) {
        board.takeTurn(p, move)
        winner = WinnerDetector().detectWinner(board, player1, player2)
    }

    fun getWinner(): Player? {
        return winner
    }

    internal fun setState(gameState: String) {
        board.setState(gameState)
    }
}


fun main() {
    val game = TicTacToe().playGame()
}
