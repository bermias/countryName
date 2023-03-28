package com.ermias.countriesname.json

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ICountriesCall {
    //var BASE_URL = "https://gist.githubusercontent.com/"
    //"https://data.cityofnewyork.us/"

    /* @GET("resource/f9bf-2cp4.json")
     fun getCountriesDatas(): Call<List<CountriesCurrencyItem>> {}*/
    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    fun getCountriesData(): Call<CountriesCurrency>
}