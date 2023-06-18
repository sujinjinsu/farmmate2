package com.example.farmmate1.network

import com.example.farmmate1.weathermodel.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface  WeatherApiService{
    @GET("weatherApiEndPint")//Api 엔드 포인트 변경 가능
    fun getWeatherData(
        @Query("TF4qcEfT6ObRPZkI8XPooCNJDS39DY2HhujIugD%2BGc6NxharY00o5rb4baYR04I%2BoRwTK5wpVKjQoKf5tC9Vzw%3D%3D") apiKey:String,
        @Query("location") location:String
    ): Call<WeatherResponse>//날씨 정보 담은 데이터 모델로 변경
}