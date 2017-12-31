package com.burningaltar.rememberer

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by bherbert on 12/22/17.
 */
class FirebaseUserRepo : IUserRepo {
    val TAG = "FirebaseUserRepo"
    private val userData: MutableLiveData<User> = MutableLiveData();

    override fun getName(): String {
        return "firebase user repo"
    }

    init {
        Log.v("blarg", "firebase repo init " + this + " app is ")

        notifyUpdated()
    }

    override fun getUserData(): MutableLiveData<User> {
        Log.v("blarg", "returning user data " + userData + " on repo " + this);
        return userData
    }

    override fun notifyUpdated() {
        userData.value = toUser(FirebaseAuth.getInstance().currentUser)
        Log.v(TAG, "updated user " + userData.value + " on object " + userData + " on repo " + this)
    }

    private fun toUser(fbUser: FirebaseUser?): User? {
        if (fbUser == null) {
            return null
        } else {
            return User(fbUser.displayName ?: "No name")
        }
    }
}