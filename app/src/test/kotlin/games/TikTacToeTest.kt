package games

import kotlin.test.Test
import kotlin.test.assertEquals

class TikTacToeTest {

    @Test
    fun `test no winner`() {
        val p1 = Player("Lianne", 1)
        val p2 = Player("Paul", 2)
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
        val p1 = Player("Lianne", 1)
        val p2 = Player("Paul", 2)
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
        val p1 = Player("Lianne", 1)
        val p2 = Player("Paul", 2)
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

}