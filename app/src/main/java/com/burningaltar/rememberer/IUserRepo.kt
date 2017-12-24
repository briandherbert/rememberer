package com.burningaltar.rememberer

import android.arch.lifecycle.MutableLiveData

/**
 * Created by bherbert on 12/22/17.
 */
interface IUserRepo {
    fun getUser() : MutableLiveData<User>;
    fun notifyUpdated();
    fun getName() : String;
}