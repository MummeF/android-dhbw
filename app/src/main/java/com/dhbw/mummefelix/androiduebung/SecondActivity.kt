package com.dhbw.mummefelix.androiduebung

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var greetings = intent.getStringExtra("Greetings")

        if (greetings == null) {
            displayGreetings.text = "Boring!!!"
        } else {
            displayGreetings.text = greetings
        }

        var benz = intent.getParcelableExtra<Karre>("Benz")

        if (benz != null) {
            benzBezeichnung.text = benz.bezeichnung
            benzPS.text = benz.ps.toString() + " PS"
            benzFarbe.text = benz.farbe
        }

        sendMail.setOnClickListener {

            var mailContentText = mailContent.text.toString();
            if (!mailContentText.equals("")) {
                val mailIntent = Intent(Intent.ACTION_SEND)
                mailIntent.type = "text/plain"

                mailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("droidmule123@gmail.com"))
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Mobile Anwendungen 2020 – Uebung2")
                mailIntent.putExtra(Intent.EXTRA_TEXT, mailContentText)

                try {
                    startActivity(Intent.createChooser(mailIntent, "Choose Email Client..."))
                }
                catch (e: Exception){
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                }
            } else {
                mailContent.setTextColor(Color.parseColor("#FF0101"))
                mailContent.setHintTextColor(Color.parseColor("#FF0101"))
            }

        }
    }
}