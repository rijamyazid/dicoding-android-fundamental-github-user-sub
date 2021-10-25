package com.example.githubuser.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentFavoriteBinding
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.view.adapter.HomeAdapter
import com.example.githubuser.view.vm.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteAdapter = object : HomeAdapter() {
            override fun onBindData(viewHolder: UserViewHolder, data: UserModel) {
                bindToRow(view, viewHolder, data)
            }
        }

        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteAdapter
        }

        viewModel.dataFavoriteUsers.observe(viewLifecycleOwner, {
            favoriteAdapter.setItems(it as ArrayList<UserModel>)
        })

    }

    private fun bindToRow(view: View, viewHolder: HomeAdapter.UserViewHolder, data: UserModel) {
        with(viewHolder.binding) {
            tvNameItem.text = data.name
            tvCompanyItem.text = data.company
            tvLocationItem.text = data.location
            Glide.with(view.context)
                .load(data.avatar)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .circleCrop()
                .into(imgUserItem)

            root.setOnClickListener {
                findNavController().navigate(
                    FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(data)
                )
            }
        }
    }

}