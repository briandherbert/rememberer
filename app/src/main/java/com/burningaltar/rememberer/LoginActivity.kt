package com.burningaltar.rememberer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.burningaltar.rememberer.CoreUser.LoginFragment
import com.burningaltar.rememberer.CoreUser.User
import com.burningaltar.rememberer.CoreUser.UserViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.base_activity.*
import javax.inject.Inject

/**
 * This is an example of a UI component that updates when the user changes login state. Incidentally, this activity
 * also has buttons to login/logout, impl either through the LoginFragment or directly via methods on mUserRepo
 * Created by bherbert on 11/20/17.
 */
class LoginActivity : AppCompatActivity() {

    /// Uncomment this and user repo to use this directly
//    @Inject
//    lateinit var mUserRepo: UserRepo   // We are the source of the repo's data since FirebaseAuth uses onActivityResult

    lateinit var mLoginFragment : LoginFragment

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    public override fun onCreate(bundle: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(bundle)
        setContentView(R.layout.base_activity)

        val userViewModel: UserViewModel = ViewModelProviders.of(this, mViewModelFactory).get(UserViewModel::class.java)
        userViewModel.getUserData().observe(this, Observer<User> { user -> if (user == null) onLoggedOut() else onLoggedIn(user) })

        mLoginFragment = LoginFragment.newInstance()
        supportFragmentManager.beginTransaction().add(mLoginFragment, "loginfragment").commitNow()
    }

    fun onLoggedOut() {
        lblMessage.text = "Not logged in"
        btnLoginOrOut.text = "Log in"
        btnLoginOrOut.setOnClickListener({
            //mUserRepo.login(null)
            mLoginFragment.login()
        })
    }

    fun onLoggedIn(user : User) {
        Log.v("blarg", "login")

        lblMessage.text = user.name
        btnLoginOrOut.text = "Log out"
        btnLoginOrOut.setOnClickListener({
            //mUserRepo.logout()
            mLoginFragment.logout()
        })
    }
}
