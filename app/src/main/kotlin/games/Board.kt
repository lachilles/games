package games

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
    }


    fun takeTurn(player: Player, move: Move) {
        // handle conversion of string to int
        // python pseudocode

        elements[move.row][move.column].setState(player.id)
    }

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
