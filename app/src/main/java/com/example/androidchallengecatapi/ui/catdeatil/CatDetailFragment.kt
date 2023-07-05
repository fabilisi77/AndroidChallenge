package com.example.androidchallengecatapi.ui.catdeatil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.androidchallengecatapi.R
import com.example.androidchallengecatapi.databinding.FragmentCatDetailBinding

class CatDetailFragment : Fragment(R.layout.fragment_cat_detail) {
    private lateinit var binding: FragmentCatDetailBinding
    private val args by navArgs<CatDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCatDetailBinding.bind(view)

        Glide.with(requireContext()).load(args.url).centerCrop().into(binding.imgCatDetail)
    }
}