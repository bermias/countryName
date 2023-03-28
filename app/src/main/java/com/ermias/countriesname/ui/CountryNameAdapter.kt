package com.ermias.countriesname.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ermias.countriesname.R
import com.ermias.countriesname.json.CountriesCurrency

class CountryNameAdapter:
    RecyclerView.Adapter<CountryNameAdapter.ViewHolderCountryName>() {

    private var countriesCurrencies = CountriesCurrency()

    fun setCountriesData(data: CountriesCurrency){
        this.countriesCurrencies = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCountryName {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent,false)
        val viewHolderCountryName = ViewHolderCountryName(itemView)
        return viewHolderCountryName
    }

    override fun getItemCount(): Int {
        return countriesCurrencies.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolderCountryName, position: Int) {
        val item = countriesCurrencies[position]
        holder.textView!!.text = item.name +", "+item.region +"      " + item.code + "  |" +
                "\n" + item.capital
    }

    class ViewHolderCountryName(itemView: View): RecyclerView.ViewHolder(itemView){

        var textView: TextView? =itemView.findViewById<View>(R.id.data_textview) as TextView
    }
}