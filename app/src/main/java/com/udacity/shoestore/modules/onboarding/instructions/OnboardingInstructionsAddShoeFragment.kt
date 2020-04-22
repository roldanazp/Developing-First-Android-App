package com.udacity.shoestore.modules.onboarding.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentOnboardingInstructionsAddShoeBinding
import com.udacity.shoestore.modules.onboarding.instructions.interfaces.OnboardingInstructionsPresenter
import com.udacity.shoestore.modules.onboarding.welcome.OnboardingWelcomeFragmentDirections
import com.udacity.shoestore.sharedpreferences.PreferencesHelper

/**
 * Add show instructions screen
 */
class OnboardingInstructionsAddShoeFragment : Fragment(), OnboardingInstructionsPresenter {

    /**
     * Inflates and link the view to the [OnboardingInstructionsPresenter]
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOnboardingInstructionsAddShoeBinding
            .inflate(layoutInflater, container, false)
        binding.presenter = this
        return binding.root
    }

    /**
     * [OnboardingInstructionsPresenter.onContinue] event navigates to
     * [OnboardingInstructionsAddShoeFragmentDirections.actionOnboardingInstructionsAddShoeFragmentToOnboardingInstructionsFillShoeFieldsFragment] action
     */
    override fun onContinue() {
        PreferencesHelper.isTutorialCompleted = true
        val action = OnboardingInstructionsAddShoeFragmentDirections
            .actionOnboardingInstructionsAddShoeFragmentToOnboardingInstructionsFillShoeFieldsFragment()
        findNavController().navigate(action)
    }

    /**
     * [OnboardingInstructionsPresenter.onBack] event navigates back from
     * [OnboardingWelcomeFragmentDirections.actionOnboardingWelcomeFragmentToOnboardingInstructionsFragment] action
     */
    override fun onBack() {
        findNavController().popBackStack()
    }

}
