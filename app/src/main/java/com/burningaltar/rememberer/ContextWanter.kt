package com.burningaltar.rememberer

import android.app.Application
import android.util.Log
import javax.inject.Inject

/**
 * Created by bherbert on 12/31/17.
 */
class ContextWanter {
    @Inject
    lateinit var mApp : Application
    init {
        Log.v("blarg", "app " + mApp)
    }
}