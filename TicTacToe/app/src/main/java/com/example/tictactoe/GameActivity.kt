package com.example.tictactoe

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.gridlayout.widget.GridLayout
import com.example.tictactoe.databinding.ActivityGameBinding


class GameActivity : AppCompatActivity() {
    private lateinit var mode: String
    private lateinit var player1Name: String
    private lateinit var player2Name: String
    private var currentPlayer: Int = 1
    private val game = TicTacToe()
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mode = intent.getStringExtra("mode") ?: "pvp"
        player1Name = intent.getStringExtra("player1Name") ?: "Player 1"
        player2Name = if (mode == "pvc") "AI" else intent.getStringExtra("player2Name") ?: "Player 2"

        createBoard()
        updatePlayerTurn()
    }

    private fun createBoard() {
        val boardCells = arrayOf(
            arrayOf(binding.cell00, binding.cell01, binding.cell02),
            arrayOf(binding.cell10, binding.cell11, binding.cell12),
            arrayOf(binding.cell20, binding.cell21, binding.cell22)
        )

        for (i in 0..2) {
            for (j in 0..2) {
                boardCells[i][j].setOnClickListener {
                    onCellClicked(i, j)
                }
            }
        }
    }

    private fun onCellClicked(x: Int, y: Int) {
        if (game.makeMove(x, y)) {
            updateBoard()
            val winner = game.checkWinner()
            if (winner != 0) {
                announceWinner(winner)
            } else if (game.isBoardFull()) {
                announceDraw()
            } else {
                currentPlayer = 3 - currentPlayer
                updatePlayerTurn()

                // if pvc then ai sac stardat
                if (mode == "pvc") {
                    makeAIMove()
                }
            }
        }
    }
    private fun makeAIMove() {
        val emptyCells = mutableListOf<Pair<Int, Int>>()

        // brivas cell
        for (i in 0..2) {
            for (j in 0..2) {
                if (game.getCellValue(i, j) == 0) {
                    emptyCells.add(Pair(i, j))
                }
            }
        }

        // random ai move , ja ir briva vieta
        if (emptyCells.isNotEmpty()) {
            val randomCell = emptyCells.random()
            game.makeMove(randomCell.first, randomCell.second)
            updateBoard()
            val winner = game.checkWinner()
            if (winner != 0) {
                announceWinner(winner)
            } else if (game.isBoardFull()) {
                announceDraw()
            } else {
                currentPlayer = 3 - currentPlayer
                updatePlayerTurn()
            }
        }
    }

    private fun updateBoard() {
        val boardCells = arrayOf(
            arrayOf(findViewById<AppCompatButton>(R.id.cell00), findViewById<AppCompatButton>(R.id.cell01), findViewById<AppCompatButton>(R.id.cell02)),
            arrayOf(findViewById<AppCompatButton>(R.id.cell10), findViewById<AppCompatButton>(R.id.cell11), findViewById<AppCompatButton>(R.id.cell12)),
            arrayOf(findViewById<AppCompatButton>(R.id.cell20), findViewById<AppCompatButton>(R.id.cell21), findViewById<AppCompatButton>(R.id.cell22))
        )

        for (i in 0..2) {
            for (j in 0..2) {
                val cellValue = game.getCellValue(i, j)
                boardCells[i][j].text = when (cellValue) {
                    1 -> "X"
                    2 -> "O"
                    else -> ""
                }
            }
        }
    }

    private fun updatePlayerTurn() {
        val currentPlayerName = if (currentPlayer == 1) player1Name else player2Name
        binding.tvPlayerTurn.text = "Turn: $currentPlayerName"
    }

    private fun announceWinner(winner: Int) {
        val winnerName = if (winner == 1) player1Name else player2Name
        binding.tvWinner.text = "Winner: $winnerName"
        binding.tvWinner.visibility = View.VISIBLE
        binding.btnRestart.visibility = View.VISIBLE
        binding.btnRestart.setOnClickListener {
            restartGame()
        }
    }

    private fun announceDraw() {
        binding.tvWinner.text = "Draw"
        binding.tvWinner.visibility = View.VISIBLE
        binding.btnRestart.visibility = View.VISIBLE
        binding.btnRestart.setOnClickListener {
            restartGame()
        }
    }

    private fun restartGame() {
        game.reset()
        updateBoard()
        currentPlayer = 1
        updatePlayerTurn()
        binding.tvWinner.visibility = View.GONE
        binding.btnRestart.visibility = View.GONE
    }
}
