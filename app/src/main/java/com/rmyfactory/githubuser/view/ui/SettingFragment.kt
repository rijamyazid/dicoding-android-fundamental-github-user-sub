package com.rmyfactory.githubuser.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rmyfactory.githubuser.databinding.FragmentSettingBinding
import com.rmyfactory.githubuser.view.vm.MainViewModel

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
                viewModel.saveThemeSetting(isChecked)
            }
        }

        viewModel.getThemeSettings().observe(viewLifecycleOwner, { isDarkMode ->
            binding.switchDarkMode.isChecked = isDarkMode
        })
    }

}