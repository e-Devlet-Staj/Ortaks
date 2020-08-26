package com.example.projedeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        val background = object : Thread() {
            override fun run() {
                try {
                        Thread.sleep(3000)
                    val intent = Intent(baseContext,Main::class.java)
                    startActivity(intent)
                }
                catch (e : Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}