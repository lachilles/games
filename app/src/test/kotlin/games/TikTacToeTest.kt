package games

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull

class TikTacToeTest {

    @Test
    fun `test no winner`() {
        val p1 = HumanPlayer(name = "Lianne", id = 1, TextInputController())
        val p2 = HumanPlayer(name = "Paul", id = 2, TextInputController())
        val g = TicTacToe(p1, p2)
        g.takeTurn(p1, Move(0, 0))
        g.takeTurn(p2, Move(1, 1))
        g.takeTurn(p1, Move(2, 0))
        g.takeTurn(p2, Move(1, 0))
        g.takeTurn(p1, Move(1, 2))
        g.takeTurn(p2, Move(2, 2))
        g.takeTurn(p1, Move(0, 2))
        g.takeTurn(p2, Move(0, 1))
        g.takeTurn(p1, Move(2, 1))
        assertEquals(null, g.getWinner())
    }

    @Test
    fun `test p1 winning`() {
        val p1 = HumanPlayer(name = "Lianne", id = 1, TextInputController())
        val p2 = HumanPlayer(name = "Paul", id = 2, TextInputController())
        val g = TicTacToe(p1, p2)
        val gameState = """
            X O X
            O X .
            . O .
        """.trimIndent()
        g.setState(gameState)
        g.takeTurn(p1, Move(2, 2))
        assertEquals(p1, g.getWinner())
    }

    @Test
    fun `test p2 winning`() {
        val p1 = HumanPlayer(name = "Lianne", id = 1, TextInputController())
        val p2 = HumanPlayer(name = "Paul", id = 2, TextInputController())
        val g = TicTacToe(p1, p2)
        val gameState = """
            X O X
            . . X
            . O .
        """.trimIndent()
        g.setState(gameState)
        g.takeTurn(p2, Move(1, 1))
        assertEquals(p2, g.getWinner())
    }

    @Test
    fun `test AnyOpenSquare strategy`() {
        val p1 = HumanPlayer(name = "Lianne", id = 1, TextInputController())
        val p2 = ComputerPlayer(name = "robot", id = 2, AnyOpenSquare())
        val g = TicTacToe(p1, p2)
        // val get valid moves list. make board available
        val gameState = """
                X O X
                O X .
                . O .
            """.trimIndent()
        g.setState(gameState)
        g.takeTurn(p1, Move(1, 2))
        g.takeTurn(p2, p2.makeMove(g.board, g.board.getValidMoves()))
        assertEquals(1, g.board.getValidMoves().size)
    }

    @Test
    fun `test Defensive strategy`() {
        val p1 = HumanPlayer(name = "Lianne", id = 1, TextInputController())
        val p2 = ComputerPlayer(name = "robot", id = 2, Defensive())
        val g = TicTacToe(p1, p2)
        // val get valid moves list. make board available
        val gameState = """
                O X .
                . X .
                . O .
            """.trimIndent()
        g.setState(gameState)
        g.takeTurn(p1, Move(1, 0))
        assertEquals(p2.makeMove(g.board, g.board.getValidMoves()), Move(1, 2))
    }

    @Test
    fun `test Offensive strategy`() {
        val p1 = HumanPlayer(name = "Lianne", id = 1, TextInputController())
        val p2 = ComputerPlayer(name = "robot", id = 2, Offensive())
        val g = TicTacToe(p1, p2)
        // val get valid moves list. make board available
        val gameState = """
                    O . O
                    X X .
                    . . X
                """.trimIndent()
        g.setState(gameState)
        assertEquals(p2.makeMove(g.board, g.board.getValidMoves()), Move(0, 1))
    }
}
