package com.generation.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class PortaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_porta)

        val botaoPorta = findViewById<ImageButton>(R.id.buttonPorta)

        val intentMain = Intent(this, MainActivity::class.java)

        botaoPorta.setOnClickListener{
            startActivity(intentMain)
        }
    }
}