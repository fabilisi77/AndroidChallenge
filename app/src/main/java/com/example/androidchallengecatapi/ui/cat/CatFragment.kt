package com.example.androidchallengecatapi.ui.cat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.androidchallengecatapi.data.model.Cat
import com.example.androidchallengecatapi.data.remote.CatDataSource
import com.example.androidchallengecatapi.databinding.FragmentCatBinding
import com.example.androidchallengecatapi.presentation.CatViewModel
import com.example.androidchallengecatapi.presentation.CatViewModelFactory
import com.example.androidchallengecatapi.repository.CatRepositoryImpl
import com.example.androidchallengecatapi.repository.RetrofitClient
import com.example.androidchallengecatapi.ui.adapters.CatAdapter

class CatFragment : Fragment(), CatAdapter.OnCatClickListener {

    private var _binding: FragmentCatBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CatViewModel by viewModels {
        CatViewModelFactory(CatRepositoryImpl(CatDataSource(RetrofitClient.webService)))
    }
    private lateinit var catAdapter: CatAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

