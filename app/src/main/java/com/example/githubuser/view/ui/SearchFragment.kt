package com.example.githubuser.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentSearchBinding
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.view.adapter.HomeAdapter
import com.example.githubuser.view.vm.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

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

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchAdapter = object : HomeAdapter() {
            override fun onBindData(viewHolder: UserViewHolder, data: UserModel) {
                bindToRow(view, viewHolder, data)
            }
        }

        binding.swipeRefreshSearch.apply {
            setOnRefreshListener {
                viewModel.refresh()
                isRefreshing = false
            }
        }

        binding.backSearch.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.rvSearch.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchAdapter
        }


        binding.searchViewSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setQuery(newText)
                return true
            }

        })

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
                    SearchFragmentDirections.actionSearchFragmentToDetailFragment(data)
                )
            }
        }
    }

}