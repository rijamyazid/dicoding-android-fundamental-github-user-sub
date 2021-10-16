package com.example.githubuser.view.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentHomeBinding
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.util.Helpers
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

        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }

        viewModel.getAllUsers().observe(viewLifecycleOwner, {
            when (it) {
                is LocalSealed.Value -> {
                    homeAdapter.setItems(it.data as ArrayList<UserModel>)
                    Log.d("TESTING_PURPOSE", it.data.toString())
                }
                is LocalSealed.Error -> {
                    Log.d("TESTING_PURPOSE", it.message ?: "Terjadi error")
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
                .load(
                    ResourcesCompat.getDrawable(
                        resources,
                        Helpers.getDrawableFromStr(data.avatar)
                            ?: R.drawable.ic_account_circle_24,
                        null
                    )
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

}