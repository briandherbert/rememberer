package com.burningaltar.rememberer

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v4.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Created by bherbert on 11/21/17.
 */
class FirebaseLoginViewModel : ViewModel() {
    val firebaseUserLiveData:MutableLiveData<FirebaseUser> = MutableLiveData()

    init {
        updateUser()
    }

    fun getCurrentUserName(): String? {
        return firebaseUserLiveData.value?.displayName;
    }

    fun onGoogleLoginFinished() {

    }

    private fun updateUser() {
        firebaseUserLiveData.value = FirebaseAuth.getInstance().currentUser
    }

    fun loginGoogle(fragmentManager: FragmentManager) {

    }
}