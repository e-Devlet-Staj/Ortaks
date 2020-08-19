package com.example.projedeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    addImgBut.setOnClickListener(){
        addRouteAct()
    }
        addButText.setOnClickListener(){
            addRouteAct()
        }
    }

    fun addRouteAct() {
        val intent = Intent(this, AddRouteMap::class.java)
        startActivity(intent)
    }
}