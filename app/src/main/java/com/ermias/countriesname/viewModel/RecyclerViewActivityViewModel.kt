package com.ermias.countriesname.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ermias.countriesname.json.CountriesCurrency
import com.ermias.countriesname.json.ICountriesCall
import com.ermias.countriesname.json.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewActivityViewModel: ViewModel() {

    lateinit var recyclerItems: MutableLiveData<CountriesCurrency>

    init{
        recyclerItems = MutableLiveData()
    }
    fun getRecyclerItemsObserver(): MutableLiveData<CountriesCurrency>{
        return recyclerItems
    }

    fun makeApiCall(){
        val retrofitInstance = RetrofitInstance.getRetrofitInstance()
            .create(ICountriesCall::class.java)
        val call = retrofitInstance.getCountriesData()
        call.enqueue(object: Callback<CountriesCurrency>{
            override fun onResponse(call: Call<CountriesCurrency>,
                response: Response<CountriesCurrency>
            ) {
                if(response.isSuccessful){
                    recyclerItems.postValue(response.body())
                } else {
                    recyclerItems.postValue(null)
                }
            }

            override fun onFailure(call: Call<CountriesCurrency>, t: Throwable) {
                recyclerItems.postValue(null)
            }
        })
    }
}