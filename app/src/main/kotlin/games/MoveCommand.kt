package games

class MoveCommand(val b: Board, val p: Player, val m: Move)  {
    // command pattern
    fun apply() {
        b.takeTurn(p, m)
    }

    fun undo() {
        b.empty(m)
    }
}
