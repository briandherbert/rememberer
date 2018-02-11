package com.burningaltar.rememberer

import android.app.Application
import com.burningaltar.rememberer.CoreUser.FirebaseActivityModule
import com.burningaltar.rememberer.CoreUser.UserRepoModule
import com.burningaltar.rememberer.CoreUser.ViewModelModule
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by bherbert on 12/28/17.
 */
@Singleton
@Component(modules = arrayOf(FirebaseActivityModule::class, ViewModelModule::class, UserRepoModule::class, AndroidSupportInjectionModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: FirebaseApplication)
}