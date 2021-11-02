package com.asakao.sunnyweather.logic

import androidx.lifecycle.liveData
import com.asakao.sunnyweather.logic.model.Place
import com.asakao.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

object Repository {

    // Dispatchers.IO 线程转换
    fun searchPlaces(query: String) = liveData(Dispatchers.IO){
        val result = try{
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if(placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e: Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }

}