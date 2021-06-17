package games

class TextInputController: InputController {
    override fun getMoveFromPlayer(p: Player, validMoves: List<Move>): Move {
        print("It's your turn " + p.name + "! ")
        // Need some validation to make sure move is on the board
        while(true) {
            try {
                println("Make your move")
                // ? is a safe operator and trim will convert null to empty string
                val input = readLine()?.trim()?:""
                val candidateMove = convertCoords(input)
                // check if move is already taken.
                if (candidateMove in validMoves) {
                    return candidateMove
                } else {
                    println("That box is taken.")
                }
            } catch (e: Exception) {
                println("invalid input, try again" + e.localizedMessage)
            }
        }
    }
    fun convertCoords(rc: String) : Move {
        if (!((rc[0].isDigit() && rc[1].isLetter()) || (rc[1].isDigit() && rc[0].isLetter()))) {
            throw InvalidInput("expecting a digit and letter")
        }
        return when {
            rc[0].isLetter() -> Move(convertLetter(rc[0]), convertDigit(rc[1]))
            else -> Move(convertLetter(rc[1]), convertDigit(rc[0]))
        }
    }
    fun convertLetter(a: Char) : Int {
        return validateRange(a.toLowerCase() - 'a', a)
    }
    fun convertDigit(a: Char) : Int {
        return validateRange(a - '1', a)
    }
    fun validateRange(r:Int, source: Char) :Int {
        if (r < 0 || r >= 3) {
            throw InvalidInput(source.toString())
        }
        return r
    }
}
