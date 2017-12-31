package com.burningaltar.rememberer

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by bherbert on 12/28/17.
 */
@Module
class AppModule(private val mainApp: FirebaseApplication) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Application {
        return mainApp
    }
}