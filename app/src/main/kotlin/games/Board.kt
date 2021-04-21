package games

class Board {
    fun getElementIterator(): Iterator<List<BoardElement>> {
        return elements.iterator()
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
