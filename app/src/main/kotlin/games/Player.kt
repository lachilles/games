package games

abstract class Player(val name: String, val id: Int) {

    abstract fun makeMove(board: Board, validMoves: List<Move>): Move

}

class HumanPlayer(name: String, id: Int, val inputController: InputController):Player(name, id) {
    override fun makeMove(board: Board, validMoves: List<Move>): Move {
        // get move from InputController
        return inputController.getMoveFromPlayer(this, validMoves)
    }
}


class ComputerPlayer(name: String, id: Int, val strategy: Strategy):Player(name, id) {
    override fun makeMove(board: Board, validMoves: List<Move>): Move {
        return strategy.makeMove(board,this, validMoves)
    }
}
