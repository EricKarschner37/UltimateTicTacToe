package com.eric.karschner.ultimatetictactoe.model

class LargeGame(val player1:Player, val player2:Player) {

    val games = ArrayList<SmallGame>()
    val board = Board()
    var isOver = false
    var isTied = false

    var currentPlayer = player1
    lateinit var winningPlayer: Player

    init{
        games.add(SmallGame())
        games.add(SmallGame())
        games.add(SmallGame())
        games.add(SmallGame())
        games.add(SmallGame())
        games.add(SmallGame())
        games.add(SmallGame())
        games.add(SmallGame())
        games.add(SmallGame())
    }

    fun makeMoveOnSmallerBoard(boardIndex:Int, spaceIndex:Int){
        if (!games[boardIndex].isOver && games[boardIndex].moveIsValidAt(spaceIndex) && !isOver){
            games[boardIndex].makeMove(currentPlayer, spaceIndex)

            if (games[boardIndex].isOver && !games[boardIndex].isTied){
                makeMoveOnLargeBoard(boardIndex)
            }

            switchCurrentPlayer()
        }
    }

    private fun makeMoveOnLargeBoard(spaceIndex:Int){
        board[spaceIndex] = currentPlayer.symbol

        if (board.symbolHasWon(currentPlayer.symbol)){
            isOver = true
            winningPlayer = currentPlayer
        } else if (board.isFull){
            isOver = true
            isTied = true
        }
    }

    fun switchCurrentPlayer() {
        currentPlayer = if (currentPlayer == player1) player2 else player1
    }
}