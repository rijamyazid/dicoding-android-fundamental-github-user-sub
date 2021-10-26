package com.example.githubuser.view.ui

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.githubuser.MainActivity

abstract class BaseFragment: Fragment() {

    protected open var bottomNavigationState = View.VISIBLE
    protected open var appbarState = View.VISIBLE

    override fun onResume() {
        super.onResume()

        (requireActivity() as MainActivity).appbarState(appbarState)
        (requireActivity() as MainActivity).bottomNavigationState(bottomNavigationState)
    }

    override fun onStop() {
        super.onStop()
        if (bottomNavigationState == View.GONE)
                (requireActivity() as MainActivity).bottomNavigationState(View.VISIBLE)
        if (appbarState == View.GONE)
                (requireActivity() as MainActivity).appbarState(View.VISIBLE)
    }

}