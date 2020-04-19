package com.rascarlo.astroeye

import android.app.Application
import timber.log.Timber

/**
 * application class to plant a Timber debug tree
 */
class AstroEyeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}