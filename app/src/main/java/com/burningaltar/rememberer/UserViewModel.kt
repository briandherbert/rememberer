package com.burningaltar.rememberer

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import javax.inject.Inject

/**
 * Created by bherbert on 12/23/17.
 */
class UserViewModel @Inject constructor(userRepo: IUserRepo) : ViewModel() {
    var mUserRepo: IUserRepo = userRepo

    fun getUserData(): MutableLiveData<User> {
        return mUserRepo.getUserData()
    }
}