package com.dezc.coffeesaleapp.ui.views.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.dezc.coffeesaleapp.ui.views.product.ProductListFragment
import com.dezc.coffeesaleapp.ui.views.profile.ProfileFragment
import com.dezc.coffeesaleapp.ui.views.wish.WishFragment

class SectionsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ProductListFragment()
            1 -> WishFragment()
            else -> ProfileFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}
