package com.burningaltar.rememberer

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import javax.inject.Inject

/**
 * Created by bherbert on 12/23/17.
 */
class UserViewModel @Inject constructor(userRepo : IUserRepo) : ViewModel() {
    var mUserRepo : IUserRepo = userRepo

    init {
        Log.v("blarg", "init w repo " + userRepo.getName())
    }

    fun getUser() : MutableLiveData<User> {
        return mUserRepo.getUser()
    }

    fun getName() : String {
        return mUserRepo.getName() + " vm "
    }
}