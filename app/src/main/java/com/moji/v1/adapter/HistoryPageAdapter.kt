package com.moji.v1.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.moji.v1.ui.history.HistoryCalendarFragment
import com.moji.v1.ui.history.HistoryListFragment

class HistoryPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    val calendarFragment = HistoryCalendarFragment()
    val listFragment = HistoryListFragment()

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> calendarFragment
            1 -> listFragment
            else -> calendarFragment
        }
    }
}