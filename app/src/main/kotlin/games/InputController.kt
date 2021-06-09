package games

import kotlin.jvm.Throws


class UndoException: Throwable()

interface InputController {

    @Throws(UndoException::class)
    fun getMoveFromPlayer(p: Player, validMoves: List<Move>) : Move


}
