package com.example.moodfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View // Add this import statement
import com.example.moodfit.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the bottom navigation listener
        binding.bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

        // Set initial fragment to HomeFragment
        replaceFragment(HomeFragment())

        // Restore click listeners for checkinTodayButton and viewStatsButton
        binding.checkinTodayButton.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, CheckInFragment())
            transaction.addToBackStack(null)
            transaction.commit()
            binding.checkinTodayButton.visibility = View.GONE
            binding.viewStatsButton.visibility = View.GONE
        }

        binding.viewStatsButton.setOnClickListener {
            replaceFragment(StatsFragment())
        }

        // Set up a back stack changed listener
        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (currentFragment is HomeFragment) {
                binding.checkinTodayButton.visibility = View.VISIBLE
                binding.viewStatsButton.visibility = View.VISIBLE
            } else {
                binding.checkinTodayButton.visibility = View.GONE
                binding.viewStatsButton.visibility = View.GONE
            }
        }
    }

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(HomeFragment())
                true
            }
            R.id.navigation_tasks -> {
                replaceFragment(TasksFragment())
                true
            }
            R.id.navigation_add_post -> {
                replaceFragment(AddPostFragment())
                true
            }
            R.id.navigation_message -> {
                replaceFragment(MessageFragment())
                true
            }
            R.id.navigation_profile -> {
                replaceFragment(ProfileFragment())
                true
            }
            else -> false
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        // Adjust visibility of the buttons based on the fragment
        if (fragment is HomeFragment) {
            binding.checkinTodayButton.visibility = View.VISIBLE
            binding.viewStatsButton.visibility = View.VISIBLE
        } else {
            binding.checkinTodayButton.visibility = View.GONE
            binding.viewStatsButton.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        // Commented out to prevent resetting to HomeFragment every time the activity resumes
        // replaceFragment(HomeFragment())
    }
}



