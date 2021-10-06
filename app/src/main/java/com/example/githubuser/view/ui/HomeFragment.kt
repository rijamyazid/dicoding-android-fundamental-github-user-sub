package com.example.githubuser.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.githubuser.databinding.FragmentHomeBinding
import com.example.githubuser.datasource.remote.RemoteSealed
import com.example.githubuser.datasource.remote.response.UserResponse
import com.example.githubuser.view.vm.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllUsers().observe(viewLifecycleOwner, {
            when (it) {
                is RemoteSealed.Value -> {
                    Log.d("TESTING_PURPOSE", it.data.toString())
                }
                is RemoteSealed.Error -> {
                    Log.d("TESTING_PURPOSE", it.message ?: "Terjadi error")
                }
            }
        })

        binding.btnDetail.setOnClickListener {
            val userData = UserResponse(
                1, 1, "RMY", "ABC", "HAHA", "d", 1, "hai"
            )
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    userData
                )
            )
        }

    }

}