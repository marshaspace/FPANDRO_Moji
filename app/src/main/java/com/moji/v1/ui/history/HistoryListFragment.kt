package com.moji.v1.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.moji.v1.adapter.JournalAdapter
import com.moji.v1.data.DummyData
import com.moji.v1.databinding.FragmentHistoryListBinding

class HistoryListFragment : Fragment() {

    private var _binding: FragmentHistoryListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadEntries()
    }

    fun loadEntries() {
        val entries = DummyData.entries
        if (entries.isEmpty()) {
            binding.rvList.visibility = View.GONE
            binding.tvEmptyList.visibility = View.VISIBLE
        } else {
            binding.rvList.visibility = View.VISIBLE
            binding.tvEmptyList.visibility = View.GONE
            binding.rvList.layoutManager = LinearLayoutManager(requireContext())
            binding.rvList.adapter = JournalAdapter(entries) {}
        }
    }

    override fun onResume() {
        super.onResume()
        loadEntries()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}