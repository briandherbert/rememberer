package com.burningaltar.rememberer

import dagger.Module
import dagger.Provides

/**
 * Created by bherbert on 12/23/17.
 */
@Module
class UserRepoModule {
    @Provides
    fun userRepo() : IUserRepo {
        return FirebaseUserRepo();
    }
}