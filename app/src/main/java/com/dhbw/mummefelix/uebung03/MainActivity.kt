package com.dhbw.mummefelix.uebung03

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var fancyList: ArrayList<MySupaFancyRaumSchiff> = ArrayList();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0 until 10) {
            fancyList.add(nyFunction())
        }

        toastBtn.setOnClickListener {
            var name = nameField.text.toString()
            if(name.equals("")){
                name = "Anonymous"
            }
            val toastMessage = getString(R.string.date_toast, name, Random.nextInt(100))
            Toast.makeText(applicationContext,toastMessage,Toast.LENGTH_SHORT).show()
        }

        addElementBtn.setOnClickListener {
            fancyList.add(nyFunction())
            viewAdapter.notifyItemInserted(fancyList.size-1)
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = MySupaFancyAdapta(fancyList)
        recyclerView = findViewById<RecyclerView>(R.id.myFancyRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

        }

    }

    fun nyFunction(): MySupaFancyRaumSchiff {
        val rndCol = Random.nextDouble(1.0)
        val rndImg = Random.nextDouble(1.0)
        val rndName = Random.nextDouble(1.0)
        var color: Int
        var img: Int
        var name: String

        if (rndCol < 0.33) {
            color = Color.GREEN
        } else if (rndCol < 0.66) {
            color = Color.CYAN
        } else {
            color = Color.MAGENTA
        }
        if (rndImg < 0.33) {
            img = R.drawable.dicker_benz
        } else if (rndImg < 0.66) {
            img = R.drawable.dickerer_benz
        } else {
            img = R.drawable.dickster_benz
        }
        if (rndName < 0.33) {
            name = "SupaFancyShuttle"
        } else if (rndName < 0.66) {
            name = "MegaSupaFancyShuttle"
        } else {
            name = "Can't-Believe-How-SupaMegaFancyShuttle"
        }
        return MySupaFancyRaumSchiff(name, color, img)
    }
}