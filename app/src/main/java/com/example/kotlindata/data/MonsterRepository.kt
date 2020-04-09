package com.example.kotlindata.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.kotlindata.LOG_VAL
import com.example.kotlindata.WEB_SERVICE_URL
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MonsterRepository(private val application: Application) {

    val monsterData = MutableLiveData<List<Monster>>()
    private val listType = Types.newParameterizedType(List::class.java, Monster::class.java)

    init {
//        getMonsterData()
        refreshData()
//        Log.i(LOG_VAL,"Network available: ${networkAvailable()}")
    }

    @WorkerThread
    suspend fun callWebService() {
        if (networkAvailable()) {
            Log.i(LOG_VAL,"Calling Web Service")
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(MonsterService::class.java)
            val serviceData = service.getMonsterData().body() ?: emptyList()
            monsterData.postValue(serviceData)
        }
    }

//    fun getMonsterData() {
//        val text: String = FileHelper.getTextFromAssets(application, "monster_data.json")
//
//        val moshi = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()
//        val adapter: JsonAdapter<List<Monster>> = moshi.adapter(listType)
//        monsterData.value = adapter.fromJson(text) ?: emptyList()
//    }

    @Suppress("DEPRECATION")
    fun networkAvailable(): Boolean {
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo?.isConnectedOrConnecting ?: false
    }

    fun refreshData() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }
}