package com.example.countrieslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrieslist.data.CountryModel
import com.example.countrieslist.network.RetroInstance
import com.example.countrieslist.network.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    var liveDataList: MutableLiveData<List<CountryModel>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<CountryModel>> {
        return liveDataList
    }

//    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

//    val countries = currentQuery.switchMap { queryString ->
//        getSearchResults(queryString).cachedIn(viewModelScope)
//    }

//    fun searchCountries(query: String) {
//        currentQuery.value = query
//    }

    fun getSearchResults(query: String) {
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.searchCountries(query)
        call.enqueue(object : Callback<List<CountryModel>> {
            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
                liveDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                liveDataList.postValue(null)
            }
        })
    }

    fun makeApiCall(){
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getCountryList()
        call.enqueue(object : Callback<List<CountryModel>> {
            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
                liveDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                liveDataList.postValue(null)
            }

        })
    }

    companion object {
        private const val DEFAULT_QUERY = ""
    }

}