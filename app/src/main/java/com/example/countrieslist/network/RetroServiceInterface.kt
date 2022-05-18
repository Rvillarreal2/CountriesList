package com.example.countrieslist.network

import com.example.countrieslist.data.CountryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetroServiceInterface {
    @GET("v2/all")
    fun getCountryList(): Call<List<CountryModel>>

    @GET("v2/name/{NAME_PATH}")
    fun searchCountries(
        @Path("NAME_PATH") name: String
    ): Call<List<CountryModel>>

}