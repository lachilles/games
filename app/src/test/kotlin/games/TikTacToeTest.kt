package games

import kotlin.test.Test
import kotlin.test.assertEquals

class TikTacToeTest {

    @Test
    fun `test no winner`() {
        val p1 = HumanPlayer(name = "Lianne", id = 1, TextInputController())
        val p2 = HumanPlayer(name = "Paul", id = 2, TextInputController())
        val g = TicTacToe(p1, p2)
        val moveHistory = mutableListOf<MoveCommand>()
        g.makeMove(p1, Move(0, 0), moveHistory)
        g.makeMove(p2, Move(1, 1), moveHistory)
        g.makeMove(p1, Move(2, 0), moveHistory)
        g.makeMove(p2, Move(1, 0), moveHistory)
        g.makeMove(p1, Move(1, 2), moveHistory)
        g.makeMove(p2, Move(2, 2), moveHistory)
        g.makeMove(p1, Move(0, 2), moveHistory)
        g.makeMove(p2, Move(0, 1), moveHistory)
        g.makeMove(p1, Move(2, 1), moveHistory)
        assertEquals(Move(2, 1), moveHistory.last().move)
        assertEquals(9, moveHistory.size)
        assertEquals(null, g.getWinner())
    }

    @Test
    fun `test undoing move`() {
        val p1 = HumanPlayer(name = "Lianne", id = 1, TextInputController())
        val p2 = HumanPlayer(name = "Paul", id = 2, TextInputController())
        val g = TicTacToe(p1, p2)
        val moveHistory = mutableListOf<MoveCommand>()
        g.makeMove(p1, Move(0, 0), moveHistory)
        g.undoLastMove(moveHistory)
        assertEquals(0, moveHistory.size)
        assertEquals(0, g.board.getElement(0, 0).getValue())
    }

    @Test
    fun `test p1 winning`() {
        val p1 = HumanPlayer(name = "Lianne", id = 1, TextInputController())
        val p2 = HumanPlayer(name = "Paul", id = 2, TextInputController())
        val g = TicTacToe(p1, p2)
        val moveHistory = mutableListOf<MoveCommand>()
        val gameState = """
            X O X
            O X .
            . O .
        """.trimIndent()
        g.setState(gameState)
        g.makeMove(p1, Move(2, 2), moveHistory)
        assertEquals(p1, g.getWinner())
    }

    @Test
    fun `test p2 winning`() {
        val p1 = HumanPlayer(name = "Lianne", id = 1, TextInputController())
        val p2 = HumanPlayer(name = "Paul", id = 2, TextInputController())
        val g = TicTacToe(p1, p2)
        val moveHistory = mutableListOf<MoveCommand>()
        val gameState = """
            X O X
            . . X
            . O .
        """.trimIndent()
        g.setState(gameState)
        g.makeMove(p2, Move(1, 1), moveHistory)
        assertEquals(p2, g.getWinner())
    }

    @Test
    fun `test AnyOpenSquare strategy`() {
        val p1 = HumanPlayer(name = "Lianne", id = 1, TextInputController())
        val p2 = ComputerPlayer(name = "robot", id = 2, AnyOpenSquare())
        val g = TicTacToe(p1, p2)
        val moveHistory = mutableListOf<MoveCommand>()
        // val get valid moves list. make board available
        val gameState = """
                X O X
                O X .
                . O .
            """.trimIndent()
        g.setState(gameState)
        g.makeMove(p1, Move(1, 2), moveHistory)
        g.makeMove(p2, p2.makeMove(g.board, g.board.getValidMoves()), moveHistory)
        assertEquals(1, g.board.getValidMoves().size)
    }

    @Test
    fun `test Defensive strategy`() {
        val p1 = HumanPlayer(name = "Lianne", id = 1, TextInputController())
        val p2 = ComputerPlayer(name = "robot", id = 2, Defensive())
        val g = TicTacToe(p1, p2)
        val moveHistory = mutableListOf<MoveCommand>()
        // val get valid moves list. make board available
        val gameState = """
                O X .
                . X .
                . O .
            """.trimIndent()
        g.setState(gameState)
        g.makeMove(p1, Move(1, 0), moveHistory)
        assertEquals(p2.makeMove(g.board, g.board.getValidMoves()), Move(1, 2))
    }

    @Test
    fun `test Offensive strategy`() {
        val p1 = HumanPlayer(name = "Lianne", id = 1, TextInputController())
        val p2 = ComputerPlayer(name = "robot", id = 2, Offensive())
        val g = TicTacToe(p1, p2)
        val moveHistory = mutableListOf<MoveCommand>()
        // val get valid moves list. make board available
        val gameState = """
                    O . O
                    X X .
                    . . X
                """.trimIndent()
        g.setState(gameState)
        assertEquals(p2.makeMove(g.board, g.board.getValidMoves()), Move(0, 1))
    }

    @Test
    fun `test two in a row strategy`() {
        val p1 = ComputerPlayer(name = "robot", id = 2, Offensive())
        val p2 = HumanPlayer(name = "Lianne", id = 1, TextInputController())
        val g = TicTacToe(p1, p2)
        // val get valid moves list. make board available
        val gameState = """
                        . . .
                        . X .
                        . O .
                    """.trimIndent()
        g.setState(gameState)
        assertEquals(p1.makeMove(g.board, g.board.getValidMoves()).row, 2)
    }

    @Test
    fun `test play middle strategy`() {
        val p1 = ComputerPlayer(name = "robot", id = 2, Offensive())
        val p2 = HumanPlayer(name = "Lianne", id = 1, TextInputController())
        val g = TicTacToe(p1, p2)
        // val get valid moves list. make board available
        val gameState = """
                        . . .
                        . . .
                        . . .
                    """.trimIndent()
        g.setState(gameState)
        assertEquals(p1.makeMove(g.board, g.board.getValidMoves()), Move(1, 1))
    }
}
