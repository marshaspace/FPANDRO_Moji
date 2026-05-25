package com.moji.v1.ui.mood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.moji.v1.R
import com.moji.v1.adapter.MoodAdapter
import com.moji.v1.databinding.FragmentMoodPickerBinding
import com.moji.v1.model.Mood

class MoodPickerFragment : Fragment() {

    private var _binding: FragmentMoodPickerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoodPickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TopAppBar back button
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val adapter = MoodAdapter(Mood.values().toList()) { mood ->
            // Safe Args — kirim mood ke JournalFragment
            val bundle = android.os.Bundle().apply {
                putString("selectedMood", mood.name)
            }
            findNavController().navigate(
                R.id.action_moodPicker_to_journal,
                bundle
            )
        }

        binding.rvMoodPicker.layoutManager = GridLayoutManager(requireContext(), 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val totalItems = Mood.values().size
                    return if (totalItems % 2 != 0 && position == totalItems - 1) 2 else 1
                }
            }
        }
        binding.rvMoodPicker.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}