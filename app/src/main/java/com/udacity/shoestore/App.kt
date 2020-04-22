package com.udacity.shoestore

import android.app.Application
import com.udacity.shoestore.sharedpreferences.PreferencesHelper
import com.udacity.shoestore.timber.ReleaseTree
import timber.log.Timber
/**
 * Extends [Application] to setup global configurations
 */
class App: Application() {

    /**
     * Initialize [PreferencesHelper] and [Timber]
     */
    override fun onCreate() {
        super.onCreate()
        PreferencesHelper.init(this)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }else{
            Timber.plant(ReleaseTree())
        }
    }
}