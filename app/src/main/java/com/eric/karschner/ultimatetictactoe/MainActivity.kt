package com.eric.karschner.ultimatetictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onStartGameClick(v: View){
        if (allFieldsAllValid()){
            val name1 = player_1_name_et.text.toString()
            val symbol1 = player_1_symbol_et.text.toString()
            val name2 = player_2_name_et.text.toString()
            val symbol2 = player_2_symbol_et.text.toString()
            Log.i("MainActivity", "Should launch activity")

            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("name1", name1)
            intent.putExtra("symbol1", symbol1)
            intent.putExtra("name2", name2)
            intent.putExtra("symbol2", symbol2)
            startActivity(intent)

        }
    }

    fun allFieldsAllValid():Boolean{
        return player_1_name_et.text.toString() != "" &&
                player_1_symbol_et.text.toString() != "" &&
                player_2_name_et.text.toString() != "" &&
                player_2_symbol_et.text.toString() != ""
    }
}
