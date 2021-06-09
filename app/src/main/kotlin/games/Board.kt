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
        // return a mutable list of board elements
        val result = mutableListOf<Move>()
        // use .iterator.withIndex which returns a tuple (i, j)
        for ((row, l) in elements.iterator().withIndex()) {
            for ((col, i) in l.iterator().withIndex()) {
                if (i.isEmpty()) {
                    result.add(Move(row, col))
                }
            }
        }
        return result
        // Paul's one-liner version of the above
//        return elements.stream()
//                .flatMap { m -> m.stream() }
//                .filter { it.isEmpty() }
//                .map { it -> Move(1, 1) }  // this bit needs fixing to be row, col
//                .collect(Collectors.toList())
    }


    fun takeTurn(player: Player, move: Move) {  //do it command apply function would be Board .taketurn
        // handle conversion of string to int
        // python pseudocode
        // takes a list of the previous state with the memento pattern

        elements[move.row][move.column].setState(player.id)
    }

    fun empty(move: Move) {
        elements[move.row][move.column].setState(0)
    }

    // add a fun undo which reverts to the former state.

    //    val elements = arrayOf(arrayOf(BoardElement(), BoardElement(), BoardElement()))
    val size = 3
    val row1 = List(size){
        BoardElement()
    }
    val row2 = List(size){
        BoardElement()
    }
    val row3 = List(size){
        BoardElement()
    }
    val elements = listOf(row1, row2, row3)
}
