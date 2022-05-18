package com.example.countrieslist

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.countrieslist.databinding.FragmentDetailsBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailsBinding.bind(view)

        binding.apply {
            val flagImg = args.country.flag

            GlideToVectorYou.justLoadImage(requireActivity(), Uri.parse(flagImg), flagImage)
            tvCapital.text = args.country.capital
            tvRegion.text = args.country.region
            tvName.text = args.country.name
        }
    }

}