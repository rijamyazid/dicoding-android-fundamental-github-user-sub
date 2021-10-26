package com.example.githubuser.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentDetailBinding
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.util.DataConstant.detailTabNames
import com.example.githubuser.view.ui.viewpager2.DetailPagerAdapter
import com.example.githubuser.view.vm.DetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    override var bottomNavigationState: Int = View.GONE
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var userDetail: UserModel
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var detailPagerAdapter: DetailPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userDetail = args.userDetail
        viewModel.insertUser(userDetail)

        detailPagerAdapter = DetailPagerAdapter(this)
        detailPagerAdapter.setArguments(userDetail.username)
        binding.viewPagerDetail.adapter = detailPagerAdapter
        TabLayoutMediator(binding.tabLayoutDetail, binding.viewPagerDetail) { tab, position ->
            tab.text = detailTabNames[position]
        }.attach()

        binding.fabFavorite.setOnClickListener {
            val userUpdated = userDetail.apply { favorite = !this.favorite }
            viewModel.updateUser(userUpdated)
        }

        viewModel.getUser(userDetail.username).observe(viewLifecycleOwner, {
            binding.tvName.text = it.name
            binding.tvUsername.text = it.username
            binding.tvLocation.text = it.location
            binding.tvCompanyContent.text = it.company
            binding.tvRepositoryContent.text = it.repository.toString()
            binding.tvFollowersContent.text = it.follower.toString()
            binding.tvFollowingContent.text = it.following.toString()
            binding.fabFavorite.setImageResource(
                if (it.favorite) {
                    R.drawable.ic_favorited_24
                } else {
                    R.drawable.ic_favorite_24
                }
            )

            Glide.with(view.context)
                .load(it.avatar)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .circleCrop()
                .into(binding.imgUser)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_appbar, menu)
        val itemSearch = menu.findItem(R.id.appbar_search)
        itemSearch.isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.appbar_share -> {
                val shareIntent = shareIntent()
                startActivity(shareIntent)
            }
        }
        return false
    }

    private fun shareIntent() = Intent().apply {
        val content =
            """
                #Pengguna GIT
                #Nama = ${userDetail.name}
                #Username = ${userDetail.username}
                #Perusahaan = ${userDetail.company}
            """
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, content.trimMargin("#"))
        type = "text/plain"
    }

}