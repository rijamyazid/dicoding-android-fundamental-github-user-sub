package com.example.githubuser.view.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentHomeBinding
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.view.adapter.HomeAdapter
import com.example.githubuser.view.vm.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = object : HomeAdapter() {
            override fun onBindData(viewHolder: UserViewHolder, data: UserModel) {
                bindToRow(view, viewHolder, data)
            }
        }

        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }

        if (viewModel.query.value.isNullOrEmpty()) {
            Log.d("TESTING_PURPOSE", "1")
            viewModel.dataUsers.observe(viewLifecycleOwner, {
                universalObserver(it)
            })
        } else {
            Log.d("TESTING_PURPOSE", "2")
            viewModel.dataUsersByQuery.observe(viewLifecycleOwner, {
                universalObserver(it)
            })
        }

    }

    private fun bindToRow(view: View, viewHolder: HomeAdapter.UserViewHolder, data: UserModel) {
        with(viewHolder) {
            binding.tvNameItem.text = data.name
            binding.tvCompanyItem.text = data.company
            binding.tvLocationItem.text = data.location
            Glide.with(view.context)
                .load(data.avatar)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .circleCrop()
                .into(binding.imgUserItem)

            binding.root.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
                )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_appbar, menu)
        val itemShare = menu.findItem(R.id.appbar_share)
        itemShare.isVisible = false

        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.appbar_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setQuery(viewModel.query.value, false)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("TESTING_PURPOSE", "4")
                viewModel.setQuery(newText ?: "")
                viewModel.dataUsersByQuery.observe(viewLifecycleOwner, {
                    universalObserver(it)
                })
                return true
            }
        })
    }

    private fun universalObserver(observed: LocalSealed<List<UserModel>>) {
        when (observed) {
            is LocalSealed.Loading -> {
                binding.pbHome.visibility = View.VISIBLE
                binding.rvUsers.visibility = View.GONE
            }
            is LocalSealed.Value -> {
                homeAdapter.setItems(observed.data as ArrayList<UserModel>)
                binding.rvUsers.visibility = View.VISIBLE
                binding.pbHome.visibility = View.GONE
            }
            is LocalSealed.Error -> {
                Snackbar.make(
                    requireView(),
                    "Oops, ada kesalahan teknis. Reason: ${observed.message}",
                    Snackbar.LENGTH_LONG
                ).show()
                binding.rvUsers.visibility = View.VISIBLE
                binding.pbHome.visibility = View.GONE
            }
        }
    }

}