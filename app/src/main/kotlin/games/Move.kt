package games

open class Move(val row: Int, val column: Int) {

    override fun equals(other: Any?): Boolean {
        /// check the type is a Move type
        return other is Move && row == other.row && column == other.column
    }
}

class WeightedMove(val weight: Double, row: Int, column: Int): Move(row, column) {
    constructor(weight: Double, move: Move) : this(weight, move.row, move.column)
}
