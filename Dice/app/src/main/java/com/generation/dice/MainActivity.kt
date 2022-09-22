package com.generation.dice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        findViewById()
         */

        val buttonD6 = findViewById<Button>(R.id.buttonD6)

        val buttonD12 = findViewById<Button>(R.id.buttonD12)

        val buttonD20 = findViewById<Button>(R.id.buttonD20)

        val imgButtonD6 = findViewById<ImageButton>(R.id.dado6BotaoImg)

        buttonD6.setOnClickListener {
            //Toast.makeText(this, "Botão D6 Clicado", Toast.LENGTH_LONG).show()
            rolarDados(6)
        }


        buttonD12.setOnClickListener{
            //Toast.makeText(this, "Botão D12 Clicado", Toast.LENGTH_LONG).show()
            rolarDados(12)
        }

        buttonD20.setOnClickListener{
            //Toast.makeText(this, "Botão D20 Clicado", Toast.LENGTH_LONG).show()
            rolarDados(20)
        }

        imgButtonD6.setOnClickListener{
            rolarDados(6)
        }


    }

    private fun rolarDados(lados: Int) {
        val rolagem = (1..lados).random()

        if(rolagem == 1){
            val imgDado1 = findViewById<ImageView>(R.id.imageView)
            imgDado1.setImageResource(R.drawable.numero1)

            val textDado = findViewById<TextView>(R.id.textDado)
            textDado.text = rolagem.toString()
        } else if(rolagem in 2..19){

            val imgDado1 = findViewById<ImageView>(R.id.imageView)
            imgDado1.setImageResource(R.drawable.einstein)

            val textDado = findViewById<TextView>(R.id.textDado)
            textDado.text = rolagem.toString()

        } else{
            val imgDado1 = findViewById<ImageView>(R.id.imageView)
            imgDado1.setImageResource(0)

            val textDado = findViewById<TextView>(R.id.textDado)
            textDado.text = rolagem.toString() + "... Sortudo"
        }


    }


}