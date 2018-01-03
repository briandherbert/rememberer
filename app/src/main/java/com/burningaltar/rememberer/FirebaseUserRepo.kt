package com.burningaltar.rememberer

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Created by bherbert on 12/22/17.
 */
class FirebaseUserRepo(app :Application) : UserRepo(app) {
    val TAG = "FirebaseUserRepo"
    private val userData: MutableLiveData<User> = MutableLiveData();

    override fun getName(): String {
        return "firebase user repo "
    }

    init {
        Log.v(TAG, "firebase repo init " + this + " app is " + app)
        notifyUpdated()
    }

    override fun getUserData(): MutableLiveData<User> {
        Log.v(TAG, "returning user data " + userData + " on repo " + this);
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

    override fun login(creds : Array<String>?) {
        app.startActivity(Intent(app, FirebaseLoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    override fun logout() {
        FirebaseAuth.getInstance().signOut()
        notifyUpdated()
    }
}