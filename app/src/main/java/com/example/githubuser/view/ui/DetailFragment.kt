package com.example.githubuser.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentDetailBinding
import com.example.githubuser.util.Helpers
import com.example.githubuser.view.vm.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userDetail = args.userDetail
        viewModel.setUserDetail(userDetail)

        viewModel.userDetail.observe(viewLifecycleOwner, {
            binding.tvName.text = it.name
            binding.tvUsername.text = it.username
            binding.tvLocation.text = it.location
            binding.tvCompanyContent.text = it.company
            binding.tvRepositoryContent.text = it.repository.toString()
            binding.tvFollowersContent.text = it.follower.toString()
            binding.tvFollowingContent.text = it.following.toString()

            Glide.with(view.context)
                .load(
                    ResourcesCompat.getDrawable(
                        resources,
                        Helpers.getDrawableFromStr(it.avatar)
                            ?: R.drawable.ic_account_circle_24,
                        null
                    )
                )
                .circleCrop()
                .into(binding.imgUser)
        })
    }

}