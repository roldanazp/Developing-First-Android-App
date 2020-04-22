package com.udacity.shoestore.modules.login.interfaces

/**
 * Represents all actions that can be perform on the login view
 */
interface LoginPresenter {

    /**
     * User performs login with a username and a password
     */
    fun onLogin()

    /**
     * User performs login with google authentication service
     */
    fun onLoginWithGoogle()

}

