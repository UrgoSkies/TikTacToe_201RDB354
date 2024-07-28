package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.ActivityPlayerNamesBinding

class PlayerNamesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerNamesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerNamesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mode = intent.getStringExtra("mode")

        if (mode == "pvc") {
            binding.etPlayer2Name.setText("AI")
        }

        binding.btnStart.setOnClickListener {
            val player1Name = binding.etPlayer1Name.text.toString()
            val player2Name = binding.etPlayer2Name.text.toString()

            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("player1Name", player1Name as String?)
            intent.putExtra("player2Name", player2Name as String?)
            intent.putExtra("mode", mode)
            startActivity(intent)
        }
    }
}
