package com.dhbw.mummefelix.uebung04

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var jsonData = readJson()

        userGreetingTextView.text = getString(R.string.greetingText, jsonData.user)
        lastLoginTextView.text = getString(R.string.loginText, jsonData.lastLogin)




        viewManager = GridLayoutManager(this, 2)
        viewAdapter = MySupaFancyAdapta(jsonData.movies)
        recyclerView = findViewById<RecyclerView>(R.id.moviesRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.randomNumber -> {
                var randomNumber = Random.nextInt(1,6)
                var toastText = getString(R.string.randomToast, randomNumber)
                Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun readJson(): JSONData {
        val jsonString = getJsonAsString()
        val gson = Gson()
        return gson.fromJson(jsonString, JSONData::class.java)
    }

    fun getJsonAsString(): String {
        var jsonString: String
        jsonString = resources.openRawResource(R.raw.movies).bufferedReader().use { it.readText() }
        return jsonString
    }

}