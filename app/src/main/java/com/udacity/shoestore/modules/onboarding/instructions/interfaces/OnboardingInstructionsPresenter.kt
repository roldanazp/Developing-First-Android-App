package com.udacity.shoestore.modules.onboarding.instructions.interfaces

/**
 * Represents all actions that can be perform on the instructions views
 */
interface OnboardingInstructionsPresenter {

    /**
     * Performs continue event from the instructions views
     */
    fun onContinue()

    /**
     * Performs back event from the instructions views
     */
    fun onBack()

}

