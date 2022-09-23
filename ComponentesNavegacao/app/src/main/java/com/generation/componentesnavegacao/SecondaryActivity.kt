package com.generation.componentesnavegacao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        val buttonVoltar = findViewById<Button>(R.id.buttonVoltar)

        //val intentVoltar = Intent(this, MainActivity::class.java)
        //O intent iria sobrecarregar o app, criando outra instancia da activity main
        //que por sua vez criaria uma nova instancia da activity secondary

        buttonVoltar.setOnClickListener{
            finish()
        }
    }
}