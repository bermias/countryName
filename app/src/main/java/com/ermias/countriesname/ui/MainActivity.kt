package com.ermias.countriesname.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ermias.countriesname.R
import com.ermias.countriesname.json.CountriesCurrency
import com.ermias.countriesname.json.CountriesCurrencyItem
import com.ermias.countriesname.json.ICountriesCall
import com.ermias.countriesname.json.RetrofitInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {
    private val retrofitInstance: RetrofitInstance = RetrofitInstance()
    private lateinit var countryNameAdapter: CountryNameAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = applicationContext
        val countryCurrencies = ArrayList<CountriesCurrencyItem>()
        val recyclerView: RecyclerView = findViewById(R.id.reyclerview_1)
        // Retorfit
        val retrofit: Retrofit = retrofitInstance.getRetrofitInstance()
        val calls = retrofit.create(ICountriesCall::class.java)

        GlobalScope.launch {
            val calls: Call<CountriesCurrency> = calls.getCountriesDatas()
           calls.enqueue(object : Callback<CountriesCurrency>{
               @SuppressLint("SuspiciousIndentation")
               override fun onResponse(
                   call: Call<CountriesCurrency>,
                   response: Response<CountriesCurrency>
               ) {
                   if(!response.isSuccessful){
                       print(response.message())
                   }
               val countriesCurrencies:CountriesCurrency? = response.body()
               val listIterator =   countriesCurrencies!!.listIterator()

                   for(i in listIterator)
                   {
                       countryCurrencies.add(i)
                   }
                   countryNameAdapter = CountryNameAdapter(countryCurrencies)
                   val layoutManager = LinearLayoutManager(applicationContext)
                   recyclerView.layoutManager = layoutManager
                   recyclerView.adapter = countryNameAdapter
               }

               override fun onFailure(call: Call<CountriesCurrency>, t: Throwable) {
                   Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                   println("Retrofit 0 : " + t.message)
               }
           })
        }



    }
}