package com.example.moodfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Context
import com.example.moodfit.databinding.FragmentCheckInBinding

class CheckInFragment : Fragment() {

    private var _binding: FragmentCheckInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitCheckInButton.setOnClickListener {
            val sleepHours = binding.sleepSlider.progress
            val moodLevel = binding.moodSlider.progress
            val activityMinutes = binding.activitySlider.progress
            val hydrationCups = binding.hydrationSlider.progress

            // Save the values to SharedPreferences
            saveCheckInData(sleepHours, moodLevel, activityMinutes, hydrationCups)

            // Navigate back to MainActivity
            requireActivity().supportFragmentManager.popBackStackImmediate()
        }
    }

    private fun saveCheckInData(sleep: Int, mood: Int, activity: Int, hydration: Int) {
        val sharedPref = requireActivity().getSharedPreferences("CheckInData", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("SleepHours", sleep)
            putInt("MoodLevel", mood)
            putInt("ActivityMinutes", activity)
            putInt("HydrationCups", hydration)
            apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}