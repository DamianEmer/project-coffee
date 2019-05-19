package com.dezc.coffeesaleapp.ui.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dezc.coffeesaleapp.databinding.FragmentHomeBinding
import com.dezc.coffeesaleapp.functions.getSupportFragmentManager
import com.dezc.coffeesaleapp.functions.setSupportActionBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    private lateinit var mBinding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentHomeBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)

        mSectionsPagerAdapter = SectionsPagerAdapter(getSupportFragmentManager())

        container.adapter = mSectionsPagerAdapter
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

}
