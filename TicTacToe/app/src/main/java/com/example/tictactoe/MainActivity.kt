package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.btnPvp.setOnClickListener {
            val intent = Intent(this, PlayerNamesActivity::class.java)
            intent.putExtra("mode", "pvp")
            startActivity(intent)
        }

        binding.btnPvc.setOnClickListener {
            val intent = Intent(this, PlayerNamesActivity::class.java)
            intent.putExtra("mode", "pvc")
            startActivity(intent)
        }
    }
}

