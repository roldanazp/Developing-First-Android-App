package com.udacity.shoestore.onboarding.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.global.onboarding.welcome.OnboardingWelcomeFragmentDirections
import com.udacity.shoestore.onboarding.welcome.interfaces.OnboardingWelcomePresenter
import com.udacity.shoestore.onboarding.databinding.FragmentOnboardingWelcomeBinding

/**
 * Welcome screen after user sign in
 */
class OnboardingWelcomeFragment : Fragment(), OnboardingWelcomePresenter {

    /**
     * Inflates and link the view to the [OnboardingWelcomePresenter]
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOnboardingWelcomeBinding
            .inflate(layoutInflater, container, false)
        binding.presenter = this
        return binding.root
    }

    /**
     * [OnboardingWelcomePresenter.onContinue] event navigates to
     * [OnboardingWelcomeFragmentDirections.actionOnboardingWelcomeFragmentToOnboardingInstructionsFragment] event
     */
    override fun onContinue() {
        val action = OnboardingWelcomeFragmentDirections
            .actionOnboardingWelcomeFragmentToOnboardingInstructionsFragment()
        findNavController().navigate(action)
    }

}
