package com.example.githubuser.view.ui.viewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentFollowersBinding
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.view.adapter.HomeAdapter
import com.example.githubuser.view.vm.FollowersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemAdapter: HomeAdapter
    private val viewModel: FollowersViewModel by viewModels()

    companion object {
        fun newInstance(param: String): FollowersFragment {
            val bundle = bundleOf("username" to param)
            val fragment = FollowersFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemAdapter = object : HomeAdapter() {
            override fun onBindData(viewHolder: UserViewHolder, data: UserModel) {
                bindToRow(view, viewHolder, data)
            }
        }
        binding.rvFollowers.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = itemAdapter
        }

        val bundle = arguments
        viewModel.setUsername(bundle?.getString("username", "null") ?: "null")
        viewModel.dataFollowers.observe(viewLifecycleOwner, {
            when (it) {
                is LocalSealed.Loading -> {
                    binding.pbFollowers.visibility = View.VISIBLE
                    binding.rvFollowers.visibility = View.GONE
                    binding.tvErrorFollowers.visibility = View.GONE
                }
                is LocalSealed.Value -> {
                    itemAdapter.setItems(it.data as ArrayList<UserModel>)
                    binding.pbFollowers.visibility = View.GONE
                    binding.rvFollowers.visibility = View.VISIBLE
                    binding.tvErrorFollowers.visibility = View.GONE
                }
                is LocalSealed.Error -> {
                    binding.pbFollowers.visibility = View.GONE
                    binding.tvErrorFollowers.text =
                        resources.getString(R.string.error_message, it.message)
                    binding.tvErrorFollowers.visibility = View.VISIBLE
                }
            }
        })

    }

    private fun bindToRow(view: View, viewHolder: HomeAdapter.UserViewHolder, data: UserModel) {
        with(viewHolder) {
            binding.tvNameItem.text = data.name
            binding.tvLocationItem.text = data.location
            binding.tvCompanyItem.text = data.company
            Glide.with(view.context)
                .load(data.avatar)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .circleCrop()
                .into(binding.imgUserItem)
        }
    }

}