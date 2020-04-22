package com.udacity.shoestore.modules.splash


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentSplashScreenBinding
import com.udacity.shoestore.sharedpreferences.PreferencesHelper
import java.util.*
import kotlin.concurrent.schedule

const val SPLASHSCREEN_DELAY_NAME = "splashscreen_delay_name"
const val SPLASHSCREEN_DELAY = 2000L


/**
 * Fragment dedicated to display open animation and decide witch wold be the next fragment
 * in the navigation
 */
class SplashScreenFragment : Fragment() {

    /**
     * Fordward delay [TimerTask] for the animation to shine
     */
    private lateinit var timerTask: TimerTask

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSplashScreenBinding.inflate(
            layoutInflater, container, false).root
    }

    /**
     * Stop [SplashScreenFragment.timerTask], it wold be confusing for the user switching views if
     * app its on the background
     */
    override fun onResume() {
        super.onResume()
        startCountDown()
    }

    /**
     * Re-start [SplashScreenFragment.timerTask] count down, time resets as well
     */
    override fun onPause() {
        super.onPause()
        timerTask.cancel()
    }

    /**
     * Start [SplashScreenFragment.timerTask] count down of [SPLASHSCREEN_DELAY]
     */
    private fun startCountDown() {
        timerTask = Timer(SPLASHSCREEN_DELAY_NAME, false)
            .schedule(SPLASHSCREEN_DELAY) { forward() }
    }

    /**
     * Go to the destination [SplashScreenFragment.getNavitationAction] returns
     */
    private fun forward() {
        findNavController().navigate(getNavitationAction())
    }

    /**
     * Retuns the destination based on [PreferencesHelper.isUserLoggedIn] and
     * [PreferencesHelper.isTutorialCompleted] states
     */
    private fun getNavitationAction(): NavDirections {

        return if (PreferencesHelper.isUserLoggedIn && PreferencesHelper.isTutorialCompleted)
            SplashScreenFragmentDirections.actionSplashScreenFragmentToShoeListFragment()
        else if (PreferencesHelper.isUserLoggedIn && !PreferencesHelper.isTutorialCompleted)
            SplashScreenFragmentDirections.actionSplashScreenFragmentToOnboardingWelcomeFragment()
        else
            SplashScreenFragmentDirections.actionSplashScreenFragmentToLoginFragment()

    }
}
