package games

class WinnerDetector {

    fun detectWinner(board: Board, player1 : Player, player2 : Player) : Player? {
        val winningId = detectWinner(board)
        if (winningId == 0) {
            return null
        }
        if (player1.id == winningId) {
            return player1
        } else if (player2.id == winningId) {
            return player2
        } else {
            throw Exception("befuddled")
        }
    }

    private fun detectWinner(board: Board) : Int {

        for(c in 0..2) {
            var sum = 0
            var nonZero = 0
            for( r in 0..2) {
                if (!board.elements[r][c].isEmpty()) {
                    nonZero ++
                }
                sum += board.elements[r][c].getValue()
            }
            if (nonZero == 3 && sum == 3 || sum == 6) {
                return sum / 3
            }
        }

        // check for any column complete
        for( r in 0..2) {
            var sum = 0
            var nonZero = 0
            for( c in 0..2) {
                if (!board.elements[r][c].isEmpty()) {
                    nonZero ++
                }
                sum += board.elements[r][c].getValue()
            }
            if (nonZero == 3 && sum == 3 || sum == 6) {
                return sum / 3
            }
        }

        // check for diagonal
        var sum = 0
        var nonZero = 0
        for( diag in 0..2) {
            if (!board.elements[diag][diag].isEmpty()) {
                nonZero ++
            }
            sum += board.elements[diag][diag].getValue()
        }
        if (nonZero == 3 && sum == 3 || sum == 6) {
            return sum / 3
        }

        // check for diagonal
        sum = 0
        nonZero = 0
        diag2@for( diag in 0..2) {
            if (!board.elements[diag][2 - diag].isEmpty()) {
                nonZero ++
            }
            sum += board.elements[diag][2 - diag].getValue()
        }
        if (nonZero == 3 && sum == 3 || sum == 6) {
            return sum / 3
        }

        return 0
    }
}

