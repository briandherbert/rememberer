package com.burningaltar.rememberer

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by bherbert on 12/23/17.
 */
@Module
class UserRepoModule {
    @Provides
    @Singleton
    fun userRepo(app : Application) : UserRepo {
        return FirebaseUserRepo(app);
    }
}