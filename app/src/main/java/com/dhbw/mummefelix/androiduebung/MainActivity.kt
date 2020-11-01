package com.dhbw.mummefelix.androiduebung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        leftbtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            var greetingtext = greetings.text.toString();
            if (!greetingtext.equals("")) {
                intent.putExtra("Greetings",  greetingtext)
            }
            var benz = Karre("Mercedes-AMG CLA 45s 4MATIC+ Coupé", 421, "designo mountaingrau magno")
            intent.putExtra("Benz", benz)
            startActivity(intent)
        }


    }

}