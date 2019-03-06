package com.eric.karschner.ultimatetictactoe

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.eric.karschner.ultimatetictactoe.databinding.ActivityGameBinding
import com.eric.karschner.ultimatetictactoe.model.Player
import com.eric.karschner.ultimatetictactoe.viewmodel.GameViewModel
import com.eric.karschner.ultimatetictactoe.viewmodel.GameViewModelFactory
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity(){

    lateinit var mViewModel: GameViewModel
    lateinit var binding: ActivityGameBinding
    lateinit var fm: FragmentManager

    lateinit var blockViews: ArrayList<View>

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

        fm = supportFragmentManager

        message_tv.text = "It's ${mViewModel.game.currentPlayer.nickname}'s turn"
    }

    fun onBoardClick(v: View){
        Log.i("GameActivity", "${v.tag}")
    }

    fun onSpaceClick(v: View){

    }

    fun onRestartClick(v: View){
        mViewModel.restartGame()
        binding.invalidateAll()
        large_board.alpha = 1f

        for (view in blockViews){
            view.visibility = View.VISIBLE
            view.setOnClickListener { onBoardClick(it) }
        }
    }

    fun onNewPlayersClick(v: View){
        finish()
        startActivity(intent)
    }

}