package com.eric.karschner.ultimatetictactoe

import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.eric.karschner.ultimatetictactoe.databinding.BoardBinding
import com.eric.karschner.ultimatetictactoe.model.Player
import com.eric.karschner.ultimatetictactoe.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.board.*
import kotlinx.android.synthetic.main.row.view.*


class SmallGameFragment : Fragment() {

    companion object {
        fun newInstance() = SmallGameFragment()
    }

    lateinit var viewModel: GameViewModel
    lateinit var binding: BoardBinding
    lateinit var player1: Player
    lateinit var player2: Player

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.board, container, false)

        val name1 = arguments!!.getString("name1")
        val symbol1 = arguments!!.getString("symbol1")
        val name2 = arguments!!.getString("name2")
        val symbol2 = arguments!!.getString("symbol2")
        player1 = Player(name1, symbol1)
        player2 = Player(name2, symbol2)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        binding.viewModel = viewModel
    }

    fun dimSquare(){
        board_root.alpha = 0.3f
    }

    fun showSquare(){
        board_root.alpha = 1f
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun showWinner(){
        board_root.removeAllViews()
        val winner_tv = TextView(context)
        winner_tv.text = viewModel.game.currentPlayer.symbol
        winner_tv.setTextAppearance(R.style.BigSpaceTheme)
        board_root.addView(winner_tv)
    }
}
