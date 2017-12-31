package com.burningaltar.rememberer

import android.app.Application
import android.arch.lifecycle.MutableLiveData

/**
 * Created by bherbert on 12/22/17.
 */
abstract class UserRepo(var app : Application) {
    abstract fun getUserData() : MutableLiveData<User>;
    abstract fun notifyUpdated();
    abstract fun getName() : String;
    abstract fun login(creds : Array<String>?)
    abstract fun logout()
}