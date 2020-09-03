package com.example.zadshare

import android.content.Context
import android.content.SharedPreferences



class PrefManager(val context: Context,val mode:Int) {

    var pref: SharedPreferences
    var editor: SharedPreferences.Editor

    // Shared preferences file name
    private val PREF_NAME = "zadShareWelcome"

    private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"

    init{
        pref = context?.getSharedPreferences(PREF_NAME,mode)
        editor = pref.edit()
    }

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        editor.commit()
    }

    fun isFirstTimeLaunch(): Boolean {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
    }
}