package com.example.androidchallengecatapi.ui.cat

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidchallengecatapi.R
import com.example.androidchallengecatapi.core.Resource
import com.example.androidchallengecatapi.data.model.Cat
import com.example.androidchallengecatapi.data.remote.CatDataSource
import com.example.androidchallengecatapi.databinding.FragmentCatBinding
import com.example.androidchallengecatapi.presentation.CatViewModel
import com.example.androidchallengecatapi.presentation.CatViewModelFactory
import com.example.androidchallengecatapi.repository.CatRepositoryImpl
import com.example.androidchallengecatapi.repository.RetrofitClient
import com.example.androidchallengecatapi.ui.adapters.CatAdapter


class CatFragment : Fragment(R.layout.fragment_cat), CatAdapter.OnCatClickListener {

    private lateinit var binding: FragmentCatBinding
    private val viewModel: CatViewModel by viewModels {
        CatViewModelFactory(CatRepositoryImpl(CatDataSource(RetrofitClient.webService)))
    }
    private lateinit var catAdapter: CatAdapter
    private var currentSearchQuery: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCatBinding.bind(view)

        catAdapter = CatAdapter(this)
        binding.rvCatList.adapter = catAdapter

        binding.svCats.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    currentSearchQuery = query
                    fetchCatsByBreed(query)
                }
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            private fun hideKeyboard() {
                val imm = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        })

        if (currentSearchQuery.isNullOrEmpty()) {
            fetchRandomCats()
        } else {
            fetchCatsByBreed(currentSearchQuery!!)
        }
    }

    private fun fetchRandomCats() {
        viewModel.fetchRandomCats().observe(viewLifecycleOwner) { result ->
            handleCatListResult(result)
        }
    }

    private fun fetchCatsByBreed(breedIds: String) {
        viewModel.fetchCatsByBreed(breedIds).observe(viewLifecycleOwner) { result ->
            handleCatListResult(result)
        }
    }

    private fun handleCatListResult(result: Resource<List<Cat>>) {
        when (result) {
            is Resource.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is Resource.Success -> {
                binding.progressBar.visibility = View.GONE
                catAdapter.submitList(result.data)
            }
            is Resource.Failure -> {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onCatClick(cat: Cat) {
        val action = CatFragmentDirections.actionCatFragmentToCatDetailFragment(cat.url)
        findNavController().navigate(action)
    }
}
