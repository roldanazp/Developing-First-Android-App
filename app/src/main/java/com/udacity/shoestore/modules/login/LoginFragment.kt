package com.udacity.shoestore.modules.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.modules.login.interfaces.LoginPresenter
import com.udacity.shoestore.sharedpreferences.PreferencesHelper

/**
 * A illustrative fragment only, does not perform validations upon user and password, just moves
 * forward
 */
class LoginFragment : Fragment(), LoginPresenter {

    /**
     * Inflates and link the view to the [LoginPresenter]
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.presenter = this
        return binding.root
    }

    /**
     * Updates the states of the frags [PreferencesHelper.isUserLoggedIn] and
     * [PreferencesHelper.isTutorialCompleted] and always goes to
     * [LoginFragmentDirections.actionLoginFragmentToOnboardingWelcomeFragment] action
     */
    private fun forward() {
        PreferencesHelper.isUserLoggedIn = true
        PreferencesHelper.isTutorialCompleted = false
        val action = LoginFragmentDirections.actionLoginFragmentToOnboardingWelcomeFragment()
        findNavController().navigate(action)
    }

    /**
     * All view events are connected through data binding [LoginPresenter] interface
     */

    /**
     * Mock [LoginPresenter.onLogin] event
     */
    override fun onLogin() {
        forward()
    }

    /**
     * Mock [LoginPresenter.onLoginWithGoogle] event
     */
    override fun onLoginWithGoogle() {
        forward()
    }

}
