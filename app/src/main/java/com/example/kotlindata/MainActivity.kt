package com.example.kotlindata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindata.fragment.MonsterGridFragment
import com.example.kotlindata.ui.main.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val monster=Monster("Harsh","Bla Bla Bla Bla Bla", "Yo yo yo", "My Description",30.20,2)
//        Log.i(LOG_VAL, monster.toString())

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,MonsterGridFragment()).commit()

    }
}