package com.sunnyweather.android.logic

import Place
import androidx.lifecycle.liveData
import com.sunnyweather.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

object Reponsitory {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {

            val placeResponse = SunnyWeatherNetwork.searchPlace(query)

            if (placeResponse.statue == "ok") {
                val places = placeResponse.place
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.statue}"))
            }

        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }

        emit(result)
    }

}