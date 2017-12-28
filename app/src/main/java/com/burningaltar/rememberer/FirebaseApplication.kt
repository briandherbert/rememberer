package com.burningaltar.rememberer

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by bherbert on 12/28/17.
 */
public class FirebaseApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var mActivityDispatchingAndroidInjector : DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return mActivityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
    }
}