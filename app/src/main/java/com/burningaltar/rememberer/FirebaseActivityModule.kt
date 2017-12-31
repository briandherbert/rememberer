package com.burningaltar.rememberer

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by bherbert on 12/28/17.
 */
@Module
abstract class FirebaseActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeFirebaseLoginActivityInjector() : FirebaseLoginActivity

    @ContributesAndroidInjector
    abstract fun contributeLoginActivityInjector() : LoginActivity
}