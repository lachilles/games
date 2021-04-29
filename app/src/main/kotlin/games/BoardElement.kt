package games

class BoardElement {
    private var state = 0

    public fun setState(x:Int){
        state = x
    }

    public fun getValue():Int {
        return state
    }
}
