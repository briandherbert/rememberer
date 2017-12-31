package com.burningaltar.rememberer

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import android.support.annotation.NonNull



/**
 * Created by bherbert on 12/28/17.
 */
@Module
class AppModule(private val mainApp: FirebaseApplication) {
//    @Singleton
//    @Provides
//    fun provideApplication(): Application {
//        return mainApp
//    }
}