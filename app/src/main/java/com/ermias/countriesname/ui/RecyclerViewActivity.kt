package com.ermias.countriesname.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ermias.countriesname.R
import com.ermias.countriesname.json.CountriesCurrency
import com.ermias.countriesname.viewModel.RecyclerViewActivityViewModel

class RecyclerViewActivity : AppCompatActivity() {
     lateinit var recyclerView1: RecyclerView
     lateinit var  countryNameAdapater: CountryNameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        recyclerView1 = findViewById(R.id.recyclerView)
        initRecyclerView()
        createData()
    }

    fun initRecyclerView(){
        recyclerView1.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            countryNameAdapater = CountryNameAdapter()
            adapter = countryNameAdapater

            val decoration = DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(decoration)
        }
    }

    fun createData(){
        val viewModel = ViewModelProvider(this).get(RecyclerViewActivityViewModel::class.java)
        viewModel.getRecyclerItemsObserver().observe(this, Observer<CountriesCurrency> {

            if(it != null){
                countryNameAdapater.setCountriesData(it)
                countryNameAdapater.notifyDataSetChanged()
            } else {
                Toast.makeText(this@RecyclerViewActivity, "Error is getting data from api.",
                Toast.LENGTH_LONG).show()
            }
        })

        viewModel.makeApiCall()
    }
}