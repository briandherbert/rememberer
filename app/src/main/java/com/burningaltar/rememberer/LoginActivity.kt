package com.burningaltar.rememberer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.base_activity.*
import javax.inject.Inject

/**
 * Created by bherbert on 11/20/17.
 */
class LoginActivity : AppCompatActivity() {
    @Inject
    lateinit var mUserRepo: UserRepo   // We are the source of the repo's data since FirebaseAuth uses onActivityResult

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    public override fun onCreate(bundle: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(bundle)
        setContentView(R.layout.base_activity)

        val userViewModel: UserViewModel = ViewModelProviders.of(this, mViewModelFactory).get(UserViewModel::class.java)
        userViewModel.getUserData().observe(this, Observer<User> { user -> if (user == null) onLoggedOut() else onLoggedIn(user) })
    }

    fun onLoggedOut() {
        lblMessage.text = "Not logged in"
        btnLoginOrOut.text = "Log in"
        btnLoginOrOut.setOnClickListener({
            mUserRepo.login(null)
        })
    }

    fun onLoggedIn(user : User) {
        Log.v("blarg", "login")

        lblMessage.text = user.name
        btnLoginOrOut.text = "Log out"
        btnLoginOrOut.setOnClickListener({
            mUserRepo.logout()
        })
    }
}
