package com.dezc.coffeesaleapp.ui.views.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dezc.coffeesaleapp.ui.components.ProductFragment
import com.dezc.coffeesaleapp.ui.components.ProfileFragment
import com.dezc.coffeesaleapp.ui.components.WishFragment

class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ProductFragment.newInstance()
            1 -> WishFragment.newInstance()
            else -> ProfileFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}
