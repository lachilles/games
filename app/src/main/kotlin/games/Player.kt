package games

abstract class Player(val name: String, val id: Int) {

    abstract fun makeMove(validMove: List<Move>): Move

}

class HumanPlayer(name: String, id: Int, val inputController: InputController):Player(name, id) {
    override fun makeMove(validMove: List<Move>): Move {
        // get move from InputController
        return inputController.getMoveFromPlayer(this, validMove)
    }
}


class ComputerPlayer(name: String, id: Int, val strategy: Strategy):Player(name, id) {
    override fun makeMove(validMoves: List<Move>): Move {
        return strategy.makeMove(this, validMoves)
    }
}
