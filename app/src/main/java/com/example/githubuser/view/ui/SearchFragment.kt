package com.example.githubuser.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentSearchBinding
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.view.adapter.HomeAdapter
import com.example.githubuser.view.vm.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment() {

    override var bottomNavigationState: Int = View.GONE
    override var appbarState: Int = View.GONE
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchAdapter = HomeAdapter(view) { data ->
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToDetailFragment(data)
            )
        }

        with(binding) {
            swipeRefreshSearch.apply {
                setOnRefreshListener {
                    viewModel.refresh()
                    isRefreshing = false
                }
            }
            rvSearch.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = searchAdapter
            }
            backSearch.setOnClickListener {
                findNavController().popBackStack()
            }
            searchViewSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.setQuery(newText)
                    return true
                }

            })
        }

        viewModel.dataUsersByQuery.observe(viewLifecycleOwner, { observed ->
            when (observed) {
                is LocalSealed.Loading -> {
                    binding.pbSearch.visibility = View.VISIBLE
                    binding.rvSearch.visibility = View.GONE
                    binding.tvErrorSearch.visibility = View.GONE
                }
                is LocalSealed.Value -> {
                    searchAdapter.setItems(observed.data as ArrayList<UserModel>)
                    binding.rvSearch.visibility = View.VISIBLE
                    binding.pbSearch.visibility = View.GONE
                    binding.tvErrorSearch.visibility = View.GONE
                }
                is LocalSealed.Error -> {
                    binding.pbSearch.visibility = View.GONE
                    binding.tvErrorSearch.text =
                        resources.getString(R.string.error_message, observed.message)
                    binding.tvErrorSearch.visibility = View.VISIBLE
                }
            }
        })

    }

}