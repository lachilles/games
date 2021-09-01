package games

class MoveCommand(val board: Board, val player: Player, val move: Move)  {
    // command pattern
    fun apply() {
        board.takeTurn(player, move)
    }

    fun undo() {
        board.empty(move)
    }
}
