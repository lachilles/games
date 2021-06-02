package games

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
class InvalidInput(message: String): Exception(message)
fun main() {
    print(convertCoords("a1"))
    print(convertCoords("c3"))
    print(convertCoords("A3"))
    print(convertCoords("3A"))
}
