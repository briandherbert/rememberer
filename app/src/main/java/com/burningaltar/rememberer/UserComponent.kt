package com.burningaltar.rememberer

import dagger.Component
import dagger.Module
import javax.inject.Singleton

/**
 * Created by bherbert on 12/23/17.
 */
@Singleton
@Component(modules = arrayOf(UserRepoModule::class))
interface UserComponent {
    fun inject(obj : FirebaseLoginActivity)
}