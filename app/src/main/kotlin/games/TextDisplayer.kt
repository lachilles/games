package games

class TextDisplayer {
    fun display(board:Board){
        for (row in board.getElementIterator()) {
            println(row)
        }
    }
}
