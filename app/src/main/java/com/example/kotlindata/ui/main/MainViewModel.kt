package com.example.kotlindata.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.kotlindata.data.MonsterRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val dataRepo = MonsterRepository(application)
    val monsterData=dataRepo.monsterData

}