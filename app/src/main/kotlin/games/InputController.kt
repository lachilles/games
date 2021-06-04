package games

interface InputController {

    fun getMoveFromPlayer(p: Player, validMoves: List<Move>) : Move

}
