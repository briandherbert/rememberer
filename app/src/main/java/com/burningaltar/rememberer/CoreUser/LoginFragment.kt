package com.burningaltar.rememberer.CoreUser

import android.content.Context
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by bherbert on 2/9/18.
 */
class LoginFragment : Fragment() {
    @Inject
    lateinit var mUserRepo: UserRepo

    val TAG = "LoginFragment"

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    fun login() {
        if (!isAdded) {
            throw IllegalStateException("Not added")
        } else {
            mUserRepo.login(null)
        }
    }

    fun logout() {
        if (!isAdded) {
            throw IllegalStateException("Not added")
        } else {
            mUserRepo.logout()
        }
    }
}