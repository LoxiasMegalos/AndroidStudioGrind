package com.generation.dicefragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playButton = findViewById<ImageButton>(R.id.playButton)

        val intentPlay = Intent(this, SecondaryActivity::class.java)

        playButton.setOnClickListener{
            startActivity(intentPlay)
        }

    }
}