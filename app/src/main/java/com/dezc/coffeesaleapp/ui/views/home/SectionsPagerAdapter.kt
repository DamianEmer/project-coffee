package com.dezc.coffeesaleapp.ui.views.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dezc.coffeesaleapp.ui.views.product.ProductFragment
import com.dezc.coffeesaleapp.ui.views.profile.ProfileFragment
import com.dezc.coffeesaleapp.ui.views.wish.WishFragment

class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ProductFragment()
            1 -> WishFragment()
            else -> ProfileFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}
