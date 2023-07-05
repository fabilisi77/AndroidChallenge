package com.example.androidchallengecatapi.ui.cat


import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.androidchallengecatapi.R
import com.example.androidchallengecatapi.data.model.Cat
import com.example.androidchallengecatapi.data.remote.CatDataSource
import com.example.androidchallengecatapi.databinding.FragmentCatBinding
import com.example.androidchallengecatapi.presentation.CatViewModel
import com.example.androidchallengecatapi.presentation.CatViewModelFactory
import com.example.androidchallengecatapi.repository.CatRepositoryImpl
import com.example.androidchallengecatapi.repository.RetrofitClient
import com.example.androidchallengecatapi.ui.adapters.CatAdapter

class CatFragment : Fragment(R.layout.fragment_cat), CatAdapter.OnCatClickListener {

    private var _binding: FragmentCatBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CatViewModel> {
        CatViewModelFactory(
            CatRepositoryImpl(
                CatDataSource(RetrofitClient.webService)
            )
        )
    }

    private lateinit var catAdapter: CatAdapter

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCatBinding.bind(view)

        catAdapter = CatAdapter(this)

        binding.rvCatList.adapter = catAdapter

        viewModel.catList.observe(viewLifecycleOwner, Observer { cats ->
            cats?.let {
                catAdapter.submitList(cats)
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        })

        viewModel.getRandomCats()

        binding.svCats.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchCatsByBreed(query)
                }
                hideKeyboard()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCatClick(cat: Cat) {
        val action = CatFragmentDirections.actionCatFragmentToCatDetailFragment(cat.url)
        findNavController().navigate(action)
    }
}