package games

class TextDisplayer {
    val rowName = arrayOf("a", "b", "c")
    fun display(board:Board){
        for (i in 1..3) {
            print("  " + i + "  ")
        }
        println()
        for ((i, row) in board.getElementIterator().withIndex()) {
            print(rowName[i] + " | ")
            for (element in row) {
                displayElement(element)
            }
            println()
        }
    }
    fun displayElement(boardElement: BoardElement){
        when (boardElement.getValue()) {
            0 -> print(" ")
            1 -> print("X")
            2 -> print("O")
        }
        print(" | ")
    }
}
