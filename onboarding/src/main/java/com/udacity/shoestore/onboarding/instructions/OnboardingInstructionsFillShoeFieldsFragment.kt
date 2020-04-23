package com.udacity.shoestore.onboarding.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.global.onboarding.instructions.OnboardingInstructionsFillShoeFieldsFragmentDirections
import com.udacity.shoestore.onboarding.instructions.interfaces.OnboardingInstructionsPresenter
import com.udacity.shoestore.global.sharedpreferences.PreferencesHelper
import com.udacity.shoestore.onboarding.databinding.FragmentOnboardingInstructionsFillShoeFieldsBinding
import timber.log.Timber

/**
 * Fill create shoe fields instructions screen
 */
class OnboardingInstructionsFillShoeFieldsFragment : Fragment(), OnboardingInstructionsPresenter {

    /**
     * Inflates and link the view to the [OnboardingInstructionsPresenter]
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOnboardingInstructionsFillShoeFieldsBinding
            .inflate(layoutInflater, container, false)
        binding.presenter = this
        return binding.root
    }

    /**
     * [OnboardingInstructionsPresenter.onContinue] navigates to
     * [OnboardingInstructionsFillShoeFieldsFragmentDirections.actionOnboardingInstructionsFillShoeFieldsFragmentToShoeListFragment] action
     */
    override fun onContinue() {
        PreferencesHelper.isTutorialCompleted = true
        val action = OnboardingInstructionsFillShoeFieldsFragmentDirections
            .actionOnboardingInstructionsFillShoeFieldsFragmentToShoeListFragment()
        findNavController().navigate(action)
        Timber.i("User finish the onboarding")
    }

    /**
     * [OnboardingInstructionsPresenter.onBack] event navigates back from
     * popBackStack action
     */
    override fun onBack() {
        findNavController().popBackStack()
    }

}
