package com.example.blcakjack

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.blcakjack.databinding.ActivityMainBinding
import com.google.androidgamesdk.GameActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartGame.setOnClickListener {
            val name = binding.etName.text.toString().trim().ifEmpty { "Player" }
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("player_name", name)
            startActivity(intent)

        }


    }
}