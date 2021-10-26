package com.example.githubuser.view.ui

import android.os.Bundle
import android.view.*
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
import com.example.githubuser.util.NetworkConstant.testingLog
import com.example.githubuser.view.adapter.HomeAdapter
import com.example.githubuser.view.vm.HomeViewModel
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

        binding.swipeRefreshHome.apply {
            setOnRefreshListener {
                viewModel.refresh()
                isRefreshing = false
            }
        }

        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = homeAdapter
        }

        viewModel.dataUsers.observe(viewLifecycleOwner, {
            testingLog("Home : $it")
            when (it) {
                is LocalSealed.Loading -> {
                    binding.pbHome.visibility = View.VISIBLE
                    binding.rvUsers.visibility = View.GONE
                    binding.tvErrorHome.visibility = View.GONE
                }
                is LocalSealed.Value -> {
                    homeAdapter.setItems(it.data as ArrayList<UserModel>)
                    binding.rvUsers.visibility = View.VISIBLE
                    binding.pbHome.visibility = View.GONE
                    binding.tvErrorHome.visibility = View.GONE
                }
                is LocalSealed.Error -> {
                    binding.pbHome.visibility = View.GONE
                    binding.tvErrorHome.text =
                        resources.getString(R.string.error_message, it.message)
                    binding.tvErrorHome.visibility = View.VISIBLE
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
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
                )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_appbar, menu)
        val itemShare = menu.findItem(R.id.appbar_share)
        itemShare.isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.appbar_search -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToSearchFragment()
                )
            }
        }
        return false
    }

    private fun universalObserver(observed: LocalSealed<List<UserModel>>) {
        when (observed) {
            is LocalSealed.Loading -> {
                binding.pbHome.visibility = View.VISIBLE
                binding.rvUsers.visibility = View.GONE
                binding.tvErrorHome.visibility = View.GONE
            }
            is LocalSealed.Value -> {
                homeAdapter.setItems(observed.data as ArrayList<UserModel>)
                binding.rvUsers.visibility = View.VISIBLE
                binding.pbHome.visibility = View.GONE
                binding.tvErrorHome.visibility = View.GONE
            }
            is LocalSealed.Error -> {
                binding.pbHome.visibility = View.GONE
                binding.tvErrorHome.text =
                    resources.getString(R.string.error_message, observed.message)
                binding.tvErrorHome.visibility = View.VISIBLE
            }
        }
    }

}