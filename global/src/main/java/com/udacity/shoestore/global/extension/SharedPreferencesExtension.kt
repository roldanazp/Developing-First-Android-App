package com.udacity.shoestore.global.extension

import android.content.SharedPreferences

/**
 * Help it to edit and recover values from shared preference
 */
inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = edit()
    operation(editor)
    editor.apply()
}