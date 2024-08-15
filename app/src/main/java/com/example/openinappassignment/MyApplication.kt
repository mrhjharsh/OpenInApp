package com.example.openinappassignment

import SharedPref
import android.app.Application

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref(this).setAPIKey()
        HelperClass.context = this
    }
}