package com.example.projedeneme.HomeActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projedeneme.R
import com.example.projedeneme.RegisterActivity.SignIn
import com.example.projedeneme.RegisterActivity.SignUp

import kotlinx.android.synthetic.main.activity_welcome.*

class Welcome : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

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