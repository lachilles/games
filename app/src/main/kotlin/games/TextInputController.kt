package games

class TextInputController: InputController {
    override fun getMoveFromPlayer(p: Player): Move {
        print("It's your turn " + p.name + " ")
        // Need some validation to make sure move is on the board
        while(true) {
            try {
                println("Make your move ")
                // ? is a safe operator and trim will convert null to empty string
                val input = readLine()?.trim()?:""
                return convertCoords(input)
            } catch (e: Exception) {
                println("invalid input, try again")
            }
        }
    }
    fun convertCoords(rc: String) : Move {
        if (!(rc[0].isDigit() && rc[1].isLetter()) || (rc[1].isDigit() && rc[0].isLetter())) {
            throw InvalidInput("expecting a digit and letter")
        }
        if (rc[0].isDigit()) {
            return Move(convertLetter(rc[0]), convertDigit(rc[1]))
        } else {
            return Move(convertLetter(rc[1]), convertDigit(rc[0]))
        }
    }
    fun convertLetter(a: Char) : Int {
        return validateRange(a.toLowerCase() - 'a', a)
    }
    fun convertDigit(a: Char) : Int {
        return validateRange(a - '1', a)
    }
    fun validateRange(r:Int, source: Char) :Int {
        if (r < 0 || r > 3) {
            throw InvalidInput(source.toString())
        }
        return r
    }
}
