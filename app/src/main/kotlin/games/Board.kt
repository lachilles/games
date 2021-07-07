package games

import java.util.stream.Collectors

interface ValidMoveProvider {
    fun getValidMoves(): List<Move>
}


class Board :ValidMoveProvider{
    fun getElementIterator(): Iterator<List<BoardElement>> {
        return elements.iterator()
    }

    override fun getValidMoves(): List<Move> {
        return elements.stream()
                .flatMap { m -> m.stream() }
                .filter { it.isEmpty() }
                .map { Move(it.row, it.col) }
                .collect(Collectors.toList())
//        print(r)
//        // return a mutable list of board elements
//        val result = mutableListOf<Move>()
//        // use .iterator.withIndex which returns a tuple (i, j)
//        for ((row, l) in elements.iterator().withIndex()) {
//            for ((col, i) in l.iterator().withIndex()) {
//                if (i.isEmpty()) {
//                    result.add(Move(row, col))
//                }
//            }
//        }
//        return result
    }

    fun takeTurn(player: Player, move: Move) {  //do it command apply function would be Board .taketurn
        // handle conversion of string to int
        // python pseudocode
        // takes a list of the previous state with the memento pattern

        elements[move.row][move.column].setState(player.id)
    }

    fun getWinningSequences(): List<List<BoardElement>> {
        val result: MutableList<List<BoardElement>> = emptyList<List<BoardElement>>()
                .toMutableList()
        // get the rows
        for (r in 0..2) {
            result.add(elements[r])
        }
        // get the columns
        for (c in 0..2) {
            result.add(elements.stream()
                    .flatMap { m -> m.stream() }
                    .filter{ it.col == c }
                    .collect(Collectors.toList()))
        }
        // get the diagonal
        result.add(elements.stream()
                .flatMap { m -> m.stream() }
                .filter{ it.col == it.row }
                .collect(Collectors.toList()))
        // get the other diagonal
        result.add(elements.stream()
                .flatMap { m -> m.stream() }
                .filter{ it.col == 2 - it.row }
                .collect(Collectors.toList()))
        return result;
    }

    internal fun setState(gameState: String) {
        val it = gameState.splitToSequence(" ", "\n").iterator()
        for (r in 0..2) {
            for (c in 0..2) {
                elements[r][c].setState(toState(it.next()))
            }
        }
    }

    fun empty(move: Move) {
        elements[move.row][move.column].setState(0)
    }

    private fun toState(symbol: String): Int {
        return when (symbol) {
            "." -> 0
            "X" -> 1
            "O" -> 2
            else -> throw Exception("unrecognized symbol $symbol")
        }
    }

    val row1 = listOf(
            BoardElement(0, 0),
            BoardElement(0, 1),
            BoardElement(0, 2)
    )
    val row2 = listOf(
            BoardElement(1, 0),
            BoardElement(1, 1),
            BoardElement(1, 2)
    )
    val row3 = listOf(
            BoardElement(2, 0),
            BoardElement(2, 1),
            BoardElement(2, 2)
    )
    val elements = listOf(row1, row2, row3)
}
