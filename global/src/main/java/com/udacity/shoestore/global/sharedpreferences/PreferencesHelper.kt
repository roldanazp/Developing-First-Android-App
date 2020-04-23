package com.udacity.shoestore.global.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.udacity.shoestore.global.extension.edit

object PreferencesHelper{

    private lateinit var preferences: SharedPreferences
    private const val PREFERENCES_NAME = "preferences"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"
    private const val KEY_IS_TUTORIAL_COMPLETE = "is_tutorial_complete"

    var isUserLoggedIn: Boolean
        get() = preferences.getBoolean(
            KEY_IS_LOGGED_IN, false)
        set(value) = preferences.edit {
            it.putBoolean(KEY_IS_LOGGED_IN, value)
        }

    var isTutorialCompleted: Boolean
        get() = preferences.getBoolean(
            KEY_IS_TUTORIAL_COMPLETE, false)
        set(value) = preferences.edit {
            it.putBoolean(KEY_IS_TUTORIAL_COMPLETE, value)
        }

    fun init(context: Context) {
        preferences = context.getSharedPreferences(
            PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

}