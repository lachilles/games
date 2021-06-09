package games

class BoardElement  {    // put row and column in the constructor
    private var state = 0

    public fun setState(x:Int){
        state = x
    }

    public fun getValue():Int {
        return state
    }

    public fun isEmpty():Boolean {
        return state == 0
    }
}
