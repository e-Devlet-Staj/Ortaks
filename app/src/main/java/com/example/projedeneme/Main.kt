package com.example.projedeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.imageView2
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.main.*

class Main : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        button.setOnClickListener()
        {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
        textView5.setOnClickListener()
        {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }


    }
}