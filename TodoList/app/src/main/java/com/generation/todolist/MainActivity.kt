package com.generation.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sair = findViewById<ImageButton>(R.id.buttonSair)

        sair.setOnClickListener{
            finish()
        }

    }
}