package com.dhbw.uebung01

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var counter = 0
    private val COUNTER_NAME = "COUNTER"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences(COUNTER_NAME, 0)

        counter = sharedPref.getInt(COUNTER_NAME, 0)


        leftBox.setText("Counter: " + counter)
        leftbtn.setOnClickListener{
            counter++
            leftBox.setText("Counter: " + counter)
        }
        rightbtn.setOnClickListener{
            counter--
            leftBox.setText("Counter: " + counter)
        }


    }

    override fun onStop() {
        val sharedPref = getSharedPreferences(COUNTER_NAME, 0)
        val edit = sharedPref.edit()
        edit.putInt(COUNTER_NAME, counter)
        edit.apply()
        super.onStop()
    }
}