package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onNextIntent(view: View) {
        val intent = Intent(applicationContext, GameActivity::class.java)
        intent.putExtra("min", findViewById<EditText>(R.id.begin).text.toString().toInt())
        intent.putExtra("max", findViewById<EditText>(R.id.end).text.toString().toInt())
        startActivity(intent)
    }
    fun onGuessClick(view: View) {
        onNextIntent(view)
    }
}