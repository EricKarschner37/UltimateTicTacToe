package com.eric.karschner.ultimatetictactoe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eric.karschner.ultimatetictactoe.model.LargeGame
import com.eric.karschner.ultimatetictactoe.model.SmallGame
import com.eric.karschner.ultimatetictactoe.model.Player

class GameViewModel(val player1:Player, val player2:Player): ViewModel() {

    var game = LargeGame(player1, player2)
    var board = game.board
    var endMessage = ""


    fun spaceTouchedAt(boardIndex:Int, spaceIndex:Int){
        game.makeMoveOnSmallerBoard(boardIndex, spaceIndex)
    }

    fun updateBoardState(){
        this.board = game.board
    }

    fun restartGame(){
        game = LargeGame(player1, player2)
        updateBoardState()
        endMessage = ""
    }
}
class GameViewModelFactory(private val player1: Player, private val player2: Player) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GameViewModel(player1, player2) as T
    }

}