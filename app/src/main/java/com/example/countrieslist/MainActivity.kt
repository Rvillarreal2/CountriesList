package com.example.countrieslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countrieslist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

//    private var _binding : ActivityMainBinding? = null
//    private val binding get() = _binding!!

//    lateinit var recyclerAdapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        _binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHost.findNavController()

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

//        initRecyclerView()
//        initViewModel()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

//    private fun initRecyclerView() {
//        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
//        binding.countryListRecyclerView.layoutManager = layoutManager
//        recyclerAdapter = CountryListAdapter(this)
//        binding.countryListRecyclerView.adapter = recyclerAdapter
//
//    }
//
//    private fun initViewModel() {
//        val viewModel: MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
//        viewModel.getLiveDataObserver().observe(this, Observer {
//            if (it != null) {
//                recyclerAdapter.setCountryList(it)
//                recyclerAdapter.notifyDataSetChanged()
//            } else {
//                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
//            }
//        })
//        viewModel.makeApiCall()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }

}