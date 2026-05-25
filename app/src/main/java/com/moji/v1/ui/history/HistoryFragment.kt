package com.moji.v1.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.moji.v1.adapter.HistoryPagerAdapter
import com.moji.v1.data.DummyData
import com.moji.v1.databinding.FragmentHistoryBinding
import com.moji.v1.R

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = HistoryPagerAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Calendar"
                1 -> "List"
                else -> ""
            }
        }.attach()

        // Option menu
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_clear_all -> {
                    com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Clear All Journals")
                        .setMessage("Are you sure you want to delete all journal entries? This cannot be undone.")
                        .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                        .setPositiveButton("Delete") { _, _ ->
                            DummyData.entries.clear()
                            pagerAdapter.calendarFragment.refreshData()
                            pagerAdapter.listFragment.loadEntries()
                            com.google.android.material.snackbar.Snackbar.make(
                                binding.root,
                                "All journals deleted",
                                com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
                            ).show()
                        }
                        .show()
                    true
                }
                R.id.action_filter -> {
                    showFilterBottomSheet(pagerAdapter)
                    true
                }
                else -> false
            }
        }
    }

    private fun showFilterBottomSheet(pagerAdapter: HistoryPagerAdapter) {
        val bottomSheet = com.google.android.material.bottomsheet.BottomSheetDialog(requireContext())
        val sheetView = layoutInflater.inflate(R.layout.bottom_sheet_filter, null)
        bottomSheet.setContentView(sheetView)
        bottomSheet.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}