package com.example.kotlindata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindata.ui.main.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val monster=Monster("Harsh","Bla Bla Bla Bla Bla", "Yo yo yo", "My Description",30.20,2)
//
//        Log.i(LOG_VAL, monster.toString())

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.monsterData.observe(this, Observer {
            val monsterNames = StringBuilder()
            for (monster in it) {
//                Log.i(LOG_VAL, "${monster.name} (\$${monster.price})")
                monsterNames.append(monster.name).append("\n")
            }
            message.text = monsterNames
        })
    }
}