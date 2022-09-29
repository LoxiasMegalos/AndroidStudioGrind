package com.generation.desenvolvmed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import com.generation.desenvolvmed.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    //private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.imageLogo)
        val slogan = findViewById<ImageView>(R.id.imageSlogan)
        logo.alpha = 0f
        slogan.alpha = 0f

        logo.animate().setDuration(3000).alpha(1f).withEndAction{
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        slogan.animate().setDuration(3000).alpha(1f).withEndAction{
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        logo.animate().setDuration(3000).alpha(1f).withEndAction{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }


    }
}