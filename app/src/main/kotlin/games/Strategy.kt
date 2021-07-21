package games

import java.util.stream.Collectors
import kotlin.random.Random

interface Strategy {
    fun makeMove(board: Board, player: ComputerPlayer, validMoves: List<Move>): Move
}

//// Classes for individual strategies
class AnyOpenSquare: Strategy {
    override fun makeMove(board: Board, player: ComputerPlayer, validMoves: List<Move>): Move {
        // randomint takeTurn if move is empty.
        val guess = Random.nextInt(validMoves.size)
        return validMoves[guess]
    }
}

// make it a abstract class since we don't need makeMove
abstract class SmartStrategy: Strategy {
    internal fun getTwoInARow(board: Board, player: Player): Move? {
        for (sequence in board.getWinningSequences()) {
            val empty = sequence.stream().filter { it.isEmpty() }.collect(Collectors.toList())
            val playerCells = sequence.stream().filter { it.getValue() == player.id }.count()
                    .toInt()
            if (empty.size == 1 && playerCells == 2) {
                return Move(empty[0].row, empty[0].col)
            }
        }
        return null
    }
}

class Offensive: SmartStrategy() {
    override fun makeMove(board: Board, player: ComputerPlayer, validMoves: List<Move>): Move {
        val winningMove = getTwoInARow(board, player)
        if (winningMove != null) {
            return winningMove
        } else {
            return AnyOpenSquare().makeMove(board, player, validMoves)
        }
    }
}

class Defensive: SmartStrategy() {
    override fun makeMove(board: Board, player: ComputerPlayer, validMoves: List<Move>): Move {
        val emergencyMove = getTwoInARow(board, player.opponent())
        if (emergencyMove != null) {
            return emergencyMove
        } else {
            return AnyOpenSquare().makeMove(board, player, validMoves)
        }
    }
}


