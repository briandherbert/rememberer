package com.burningaltar.rememberer

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
    fun userRepo() : IUserRepo {
        return FirebaseUserRepo();
    }
}