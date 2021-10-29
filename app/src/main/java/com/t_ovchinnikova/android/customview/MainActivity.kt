package com.t_ovchinnikova.android.customview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.t_ovchinnikova.android.customview.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var isFirstPlayer = true

    private lateinit var binding: ActivityMainBinding

    private val token = Any()
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ticTacToeField.ticTacToeField = TicTacToeField(10, 10)

        binding.ticTacToeField.actionListener = { row, column, field ->
            val cell = field.getCell(row, column)
            if (cell == Cell.EMPTY) {
                if (isFirstPlayer) {
                    field.setCell(row, column, Cell.PLAYER_1)
                } else {
                    field.setCell(row, column, Cell.PLAYER_2)
                }
                isFirstPlayer = !isFirstPlayer
            }
        }

        binding.randomFieldButton.setOnClickListener {
            binding.ticTacToeField.ticTacToeField = TicTacToeField(
                Random.nextInt(3, 10),
                Random.nextInt(3, 10)
            )
            /*for (i in 0..binding.ticTacToeField.ticTacToeField!!.rows) {
                for (j in 0..binding.ticTacToeField.ticTacToeField!!.columns) {
                    if (Random.nextBoolean()) {
                        binding.ticTacToeField.ticTacToeField!!.setCell(i, j, Cell.PLAYER_1)
                    } else {
                        binding.ticTacToeField.ticTacToeField!!.setCell(i, j, Cell.PLAYER_2)
                    }
                }
            }*/
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(token)
    }
}