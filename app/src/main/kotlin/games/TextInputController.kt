package games

class TextInputController: InputController {
    override fun getMoveFromPlayer(p: Player): Move {
        print("It's your turn " + p.name)
        // Need some validation to make sure move is on the board
        var input: String? = null
        while(true) {
            try {
                println("Make your move ")
                input = readLine()
                if (input != null) {
                    var xy = convertCoords(input)
                    return Move(xy[0], xy[1])
                }
            } catch (e: Exception) {
                println("invalid input, try again")
            }
        }
    }
    fun convertCoords(rc: String) : List<Int> {
        try {
            return listOf(convertLetter(rc[0]), convertDigit(rc[1]))
        } catch (e: InvalidInput) {
            return listOf(convertLetter(rc[1]), convertDigit(rc[0]))
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
