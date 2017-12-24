package com.burningaltar.rememberer

import android.arch.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Created by bherbert on 12/22/17.
 */
class FirebaseUserRepo : IUserRepo {
    override fun getName() : String {
        return "firebase user repo"
    }

    private val userData : MutableLiveData<User> = MutableLiveData();

    override fun getUser() : MutableLiveData<User> {
        return userData;
    }

    override fun notifyUpdated() {
        userData.value = toUser(FirebaseAuth.getInstance().currentUser)
    }

    fun toUser(fbUser : FirebaseUser?) : User? {
        if (fbUser == null) {
            return null
        } else {
            return User(fbUser.displayName ?: "No name")
        }
    }
}