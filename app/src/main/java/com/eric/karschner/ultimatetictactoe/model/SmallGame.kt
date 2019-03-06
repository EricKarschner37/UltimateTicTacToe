package com.eric.karschner.ultimatetictactoe.model

class SmallGame {
    var board: Board = Board()
    var winningPlayer: Player? = null
    var isTied = false
    var isOver = false

    fun makeMove(player:Player, index: Int){
        board.markSpace(player.symbol, index)
        checkForGameEnd(player)
    }

    fun moveIsValidAt(index:Int):Boolean{
        return (board[index] == " ")
    }

    fun checkForGameEnd(player:Player): Boolean{
        return when {
            board.symbolHasWon(player.symbol) -> {
                winningPlayer = player
                isOver = true
                true
            }
            board.isFull -> {
                isTied = true
                isOver = true
                true
            }
            else -> false
        }
    }
}

class Board{
    private val spaces = arrayListOf(" ", " ", " ", " ", " ", " ", " ", " ", " ")
    var isFull = false

    fun markSpace(symbol: String, index:Int){
        spaces[index] = symbol
        if (" " !in spaces){
            isFull = true
        }
    }

    fun symbolHasWon(symbol:String): Boolean{
        return winsHorizontally(symbol) || winsVertically(symbol) || winsDiagonally(symbol)
    }

    fun winsHorizontally(symbol:String): Boolean{
        return spaces[0] == spaces[1] && spaces[1] == spaces[2] && spaces[0] == symbol ||
                spaces[3] == spaces[4] && spaces[4] == spaces[5] && spaces[3] == symbol ||
                spaces[6] == spaces[7] && spaces[7] == spaces[8] && spaces[6] == symbol
    }

    fun winsVertically(symbol:String): Boolean{
        return spaces[0] == spaces[3] && spaces[3] == spaces[6] && spaces[0] == symbol ||
                spaces[1] == spaces[4] && spaces[4] == spaces[7] && spaces[1] == symbol ||
                spaces[2] == spaces[5] && spaces[5] == spaces[8] && spaces[2] == symbol
    }

    fun winsDiagonally(symbol:String): Boolean{
        return spaces[0] == spaces[4] && spaces[4] == spaces[8] && spaces[0] == symbol ||
                spaces[2] == spaces[4] && spaces[4] == spaces[6] && spaces[2] == symbol
    }

    operator fun get(index: Int): String{
        return spaces[index]
    }

    operator fun set(index: Int, symbol:String){
        spaces[index] = symbol
    }
}

data class Player(val nickname:String, val symbol:String)