package com.burningaltar.rememberer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.base_activity.*
import java.util.*
import javax.inject.Inject

/**
 * Created by bherbert on 11/20/17.
 */
class FirebaseLoginActivity : AppCompatActivity() {
    val RC_LOGIN = 1;

    @Inject
    lateinit var mUserRepo: IUserRepo   // We are the source of the repo's data since FirebaseAuth uses onActivityResult

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    public override fun onCreate(bundle: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(bundle)
        setContentView(R.layout.base_activity)

        val userViewModel: UserViewModel = ViewModelProviders.of(this, mViewModelFactory).get(UserViewModel::class.java)
        userViewModel.getUserData().observe(this, Observer<User> { user -> if (user == null) onLoggedOut() else onLoggedIn() })
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_LOGIN) {
            mUserRepo.notifyUpdated()   // Anyone can get the FirebaseAuth user from its singleton, but we'll just tell the repo to check it.
        }
    }

    fun onLoggedOut() {
        lblMessage.text = "Not logged in"
        btnLoginOrOut.text = "Log in"
        btnLoginOrOut.setOnClickListener({
            // This is the whole reason we need an activity or fragment for a repo op
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(Arrays.asList(AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                    .build(),
                    RC_LOGIN)
        })
    }

    fun onLoggedIn() {
        Log.v("blarg", "login")

        lblMessage.text = FirebaseAuth.getInstance().currentUser!!.displayName
        btnLoginOrOut.text = "Log out"
        btnLoginOrOut.setOnClickListener({
            FirebaseAuth.getInstance().signOut()
            mUserRepo.notifyUpdated()
        })
    }
}
