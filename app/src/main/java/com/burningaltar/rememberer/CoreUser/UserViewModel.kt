package com.burningaltar.rememberer.CoreUser

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Created by bherbert on 12/23/17.
 */
class UserViewModel @Inject constructor(userRepo: UserRepo) : ViewModel() {
    var mUserRepo: UserRepo = userRepo

    fun getUserData(): MutableLiveData<User> {
        return mUserRepo.getUserData()
    }
}