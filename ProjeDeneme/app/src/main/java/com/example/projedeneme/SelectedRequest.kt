package com.example.projedeneme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_selected_request.*

class SelectedRequest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_request)

        var newIntent = intent
        if (intent!=null){
            textView11.text=newIntent.getStringExtra("destination")
            textView13.text=newIntent.getStringExtra("from")
            textView15.text=newIntent.getStringExtra("date")
            textView17.text=newIntent.getStringExtra("time")

        }
    }
}