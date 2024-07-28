package com.example.tictactoe

class TicTacToe {
    private val board = Array(3) { IntArray(3) }
    private var currentPlayer = 1

    init {
        reset()
    }

    fun makeMove(x: Int, y: Int): Boolean {
        if (board[x][y] == 0) {
            board[x][y] = currentPlayer
            currentPlayer = 3 - currentPlayer
            return true
        }
        return false
    }

    fun checkWinner(): Int {
        for (i in 0..2) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0]
            }
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i]
            }
        }
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0]
        }
        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2]
        }
        return 0
    }

    fun getCellValue(x: Int, y: Int): Int {
        return board[x][y]
    }

    fun reset() {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = 0
            }
        }
        currentPlayer = 1
    }

    fun isBoardFull(): Boolean {
        for (i in 0..2) {
            for (j in 0..2) {
                if (board[i][j] == 0) {
                    return false
                }
            }
        }
        return true
    }

}
