package com.udacity.shoestore.timber

import android.util.Log
import timber.log.Timber

class ReleaseTree: Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when(priority){
            Log.VERBOSE, Log.DEBUG -> return
            else -> { /*FIREBASE CRASH REPORT OR SOMETHING*/ }
        }
    }
}
