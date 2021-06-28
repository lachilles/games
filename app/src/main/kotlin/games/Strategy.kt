package games

import kotlin.random.Random

interface Strategy {
    fun makeMove(player: ComputerPlayer, validMoves: List<Move>): Move
}

//// Classes for individual strategies
class AnyOpenSquare: Strategy {
    override fun makeMove(player: ComputerPlayer, validMoves: List<Move>): Move {
        // randomint takeTurn if move is empty.
        val guess = Random.nextInt(validMoves.size)
        return validMoves[guess]
    }
}


//class AnyOpenCorner(Strategy) {
//    fun apply(b: Board) {
//        // if any corner is open, taketurn
//    }
//}
//
//class BlockOpponent(Strategy) {
//    fun apply(b: Board) {
//        // if any corner is open, taketurn
//    }
//}
