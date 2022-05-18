package com.example.countrieslist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countrieslist.data.CountryModel
import com.example.countrieslist.databinding.FragmentGalleryBinding

class GalleryFragment: Fragment(R.layout.fragment_gallery), CountryListAdapter.OnItemClickListener {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerAdapter: CountryListAdapter
    private val viewModel by viewModels<MainActivityViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGalleryBinding.bind(view)

        initRecyclerView()
        initViewModel()
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_gallery, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.countryListRecyclerView.scrollToPosition(0)
                    viewModel.getSearchResults(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun initRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        binding.countryListRecyclerView.layoutManager = layoutManager
        recyclerAdapter = CountryListAdapter(requireActivity(), this)
        binding.countryListRecyclerView.adapter = recyclerAdapter

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner) {
            if (it != null) {
                recyclerAdapter.setCountryList(it)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.makeApiCall()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(position: Int, country: CountryModel) {
        Toast.makeText(context, "Item $position clicked \n Name ${country.name}", Toast.LENGTH_SHORT).show()
        val action = GalleryFragmentDirections.actionGalleryFragmentToDetailsFragment(country)
        findNavController().navigate(action)

    }

}