package com.example.countrieslist

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countrieslist.data.CountryModel
import com.example.countrieslist.databinding.CountryListRowBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class CountryListAdapter(
    private val activity: Activity,
    private val listener: OnItemClickListener
    ) :
    RecyclerView.Adapter<CountryListAdapter.MyViewHolder>() {

    private var countryList: List<CountryModel>? = null

    fun setCountryList(countryList: List<CountryModel>?) {
        this.countryList = countryList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {

        val binding =
            CountryListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(countryList?.get(position)!!, activity)
    }

    override fun getItemCount(): Int {
        return if (countryList == null) 0
        else countryList?.size!!
    }

    inner class MyViewHolder(binding: CountryListRowBinding) :
        RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private val flagImage = binding.flagImage
        private val tvName = binding.tvName
        private val tvCapital = binding.tvCapital
        private val tvRegion = binding.tvRegion

        fun bind(data: CountryModel, activity: Activity) {
            tvName.text = data.name + "(" + data.alpha2Code + ")"
            tvCapital.text = "Capital: " + data.capital
            tvRegion.text = "Region: " + data.region
            GlideToVectorYou.justLoadImage(activity, Uri.parse(data.flag), flagImage)

        }

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, countryList!![position])
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, country: CountryModel)
    }

}