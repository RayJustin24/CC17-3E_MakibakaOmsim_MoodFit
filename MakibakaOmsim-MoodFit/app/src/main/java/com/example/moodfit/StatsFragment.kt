package com.example.moodfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Context
import com.example.moodfit.databinding.FragmentStatsBinding

class StatsFragment : Fragment() {

    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadCheckInData()
    }

    private fun loadCheckInData() {
        val sharedPref = activity?.getSharedPreferences("CheckInData", Context.MODE_PRIVATE)
        val sleepHours = sharedPref?.getInt("SleepHours", 0) ?: 0
        val moodLevel = sharedPref?.getInt("MoodLevel", 0) ?: 0
        val activityMinutes = sharedPref?.getInt("ActivityMinutes", 0) ?: 0
        val hydrationCups = sharedPref?.getInt("HydrationCups", 0) ?: 0

        // Update the UI with the retrieved values
        binding.sleepStat.text = getString(R.string.sleep_stat, sleepHours)
        binding.moodStat.text = getString(R.string.mood_stat, moodLevel)
        binding.activityStat.text = getString(R.string.activity_stat, activityMinutes)
        binding.hydrationStat.text = getString(R.string.hydration_stat, hydrationCups)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}