package com.eric.karschner.ultimatetictactoe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.eric.karschner.ultimatetictactoe.databinding.ActivityGameBinding
import com.eric.karschner.ultimatetictactoe.model.Player
import com.eric.karschner.ultimatetictactoe.viewmodel.GameViewModel
import com.eric.karschner.ultimatetictactoe.viewmodel.GameViewModelFactory
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.board.view.*
import kotlinx.android.synthetic.main.row.view.*

class GameActivity : AppCompatActivity(){

    lateinit var mViewModel: GameViewModel
    lateinit var binding: ActivityGameBinding

    var boards = ArrayList<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name1 = intent.getStringExtra("name1")
        val symbol1 = intent.getStringExtra("symbol1")
        val name2 = intent.getStringExtra("name2")
        val symbol2 = intent.getStringExtra("symbol2")

        val player1 = Player(name1, symbol1)
        val player2 = Player(name2, symbol2)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_game)

        mViewModel = ViewModelProviders.of(this, GameViewModelFactory(player1, player2))
            .get(GameViewModel::class.java)

        binding.setVariable(BR.viewModel, mViewModel)

        addBoards()

        message_tv.text = "It's ${mViewModel.game.currentPlayer.nickname}'s turn"
    }

    fun onSpaceClick(v: View){
        val index = (v.tag as String).toInt()
        val boardIndex = (v.parent.parent as View).tag as Int

        mViewModel.spaceTouchedAt(boardIndex, index)
        binding.invalidateAll()

        defocusBoard(boardIndex)
        handleBoardFocus(index)
    }

    fun onBoardClick(v: View){
        val boardIndex = v.tag as Int
        if (mViewModel.focusIsChangeable && !mViewModel.gameIsOverAt(boardIndex)) focusBoard(boardIndex)
    }

    fun focusBoard(boardIndex: Int){
        enableSpaces(boardIndex)
        boards[boardIndex].alpha = 1f
        for (i in 0..8){
            if (i != boardIndex) defocusBoard(i)
        }
    }

    fun defocusBoard(boardIndex: Int){
        //TODO - graphic
        disableSpaces(boardIndex)
        boards[boardIndex].alpha = 0.4f
    }

    fun focusAllBoards(){
        for (board in boards){
            board.alpha = 1f
        }
    }

    fun handleBoardFocus(index:Int){
        if(mViewModel.gameIsOverAt(index)) focusAllBoards()
        else focusBoard(index)
    }

    fun onRestartClick(v: View){
        mViewModel.restartGame()
        binding.invalidateAll()
    }

    fun onNewPlayersClick(v: View){
        finish()
        startActivity(intent)
    }

    private fun enableSpaces(boardIndex: Int){
        val board = boards[boardIndex]
        board.row_1.col_1.setOnClickListener { onSpaceClick(it) }
        board.row_1.col_2.setOnClickListener { onSpaceClick(it) }
        board.row_1.col_3.setOnClickListener { onSpaceClick(it) }
        board.row_2.col_1.setOnClickListener { onSpaceClick(it) }
        board.row_2.col_2.setOnClickListener { onSpaceClick(it) }
        board.row_2.col_3.setOnClickListener { onSpaceClick(it) }
        board.row_3.col_1.setOnClickListener { onSpaceClick(it) }
        board.row_3.col_2.setOnClickListener { onSpaceClick(it) }
        board.row_3.col_3.setOnClickListener { onSpaceClick(it) }
    }

    private fun disableSpaces(boardIndex: Int){
        val board = boards[boardIndex]
        board.row_1.col_1.setOnClickListener {  }
        board.row_1.col_2.setOnClickListener {  }
        board.row_1.col_3.setOnClickListener {  }
        board.row_2.col_1.setOnClickListener {  }
        board.row_2.col_2.setOnClickListener {  }
        board.row_2.col_3.setOnClickListener {  }
        board.row_3.col_1.setOnClickListener {  }
        board.row_3.col_2.setOnClickListener {  }
        board.row_3.col_3.setOnClickListener {  }

        board.row_1.col_1.isClickable = false
        board.row_1.col_2.isClickable = false
        board.row_1.col_3.isClickable = false
        board.row_2.col_1.isClickable = false
        board.row_2.col_2.isClickable = false
        board.row_2.col_3.isClickable = false
        board.row_3.col_1.isClickable = false
        board.row_3.col_2.isClickable = false
        board.row_3.col_3.isClickable = false
    }

    private fun addBoards(){
        boards.add(board_0)
        boards.add(board_1)
        boards.add(board_2)
        boards.add(board_3)
        boards.add(board_4)
        boards.add(board_5)
        boards.add(board_6)
        boards.add(board_7)
        boards.add(board_8)
    }
}