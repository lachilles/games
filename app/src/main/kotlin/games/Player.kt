package games

abstract class Player(val name: String, val id: Int) {

    private var opponent:Player? = null

    abstract fun makeMove(board: Board, validMoves: List<Move>): Move

    fun setOpponent(opponent:Player) {
        this.opponent = opponent
    }

    fun opponent() : Player? {
        return opponent
    }

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
