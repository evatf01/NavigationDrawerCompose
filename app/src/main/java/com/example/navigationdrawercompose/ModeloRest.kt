package com.example.navigationdrawercompose

import retrofit2.Call
import com.example.navigationdrawercompose.modelo.ModeloArray

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val base_user = "http://iesayala.ddns.net/evatf01/"


interface ModeloRest {
    @GET("generarJson.php")
    fun albumInformation(): Call<ModeloArray>
}
object AlbumInstance {
    val albumInterface: ModeloRest
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(base_user)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        albumInterface = retrofit.create(ModeloRest::class.java)
    }


}