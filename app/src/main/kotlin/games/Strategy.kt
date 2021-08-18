package games

import java.util.stream.Collectors
import kotlin.random.Random

interface Strategy {
    fun makeMove(board: Board, player: ComputerPlayer, validMoves: List<Move>): WeightedMove
}

/// Takes list of other strategies
class CompositeStrategy(val strats:List<Strategy>): Strategy {
    override fun makeMove(board: Board, player: ComputerPlayer, validMoves: List<Move>): WeightedMove {
        val possibleMoves = mutableListOf<WeightedMove>()
        for (x in strats) {
            val move = x.makeMove(board, player, validMoves)
            possibleMoves.add(move)  // Make move should return weighted move and will sort by
        // weights
        }
        val sorted = possibleMoves.sortedByDescending { it.weight }
        return sorted[0]

//        val weightComparator = compareBy<Pair<Double, Move>> {it.first}
//        return WeightedMove.sortedWith(weightComparator)
    }
}

//// Classes for individual strategies
class AnyOpenSquare: Strategy {
    override fun makeMove(board: Board, player: ComputerPlayer, validMoves: List<Move>): WeightedMove {
        // randomint takeTurn if move is empty.
        val guess = Random.nextInt(validMoves.size)
        return WeightedMove(0.0, validMoves[guess])
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
    
    internal fun getOneInARow(board: Board, player: Player): Move? {
        for (sequence in board.getWinningSequences()) {
            val empty = sequence.stream().filter { it.isEmpty() }.collect(Collectors.toList())
            val playerCells = sequence.stream().filter { it.getValue() == player.id }.count()
                    .toInt()
            if (empty.size == 2 && playerCells == 1) {
                return Move(empty[0].row, empty[0].col)
            }
        }
        return null
    }
}

class Offensive: SmartStrategy() {
    override fun makeMove(board: Board, player: ComputerPlayer, validMoves: List<Move>): WeightedMove {
        val winningMove = getTwoInARow(board, player)
        if (winningMove != null) {
            return WeightedMove(1.0, winningMove)
        }
        val betterMove = getOneInARow(board, player)
        // TO DO: getOneInARow could return a list of moves and then we can pick a corner first to
        // be the "betterMove"
        if (betterMove != null) {
            return WeightedMove(0.5, betterMove)
        }
        val centerSquare = Move(1, 1)
        // add if block to pick a corner
        if (validMoves.contains(centerSquare)) {
            return WeightedMove(0.6, centerSquare)
        }
        return AnyOpenSquare().makeMove(board, player, validMoves)
    }
}

class Defensive: SmartStrategy() {
    override fun makeMove(board: Board, player: ComputerPlayer, validMoves: List<Move>):
            WeightedMove {
        val emergencyMove = getTwoInARow(board, player.opponent())
        if (emergencyMove != null) {
            return WeightedMove(0.9, emergencyMove)
        } else {
            return AnyOpenSquare().makeMove(board, player, validMoves)
        }
    }
}


