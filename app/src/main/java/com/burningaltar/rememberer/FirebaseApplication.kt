package com.burningaltar.rememberer

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by bherbert on 12/28/17.
 */
public class FirebaseApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    lateinit var mActivityDispatchingAndroidInjector : DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var mSupportFragmentInjector : DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return mActivityDispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mSupportFragmentInjector
    }
}