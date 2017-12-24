package com.burningaltar.rememberer

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.base_activity.*
import java.util.*
import javax.inject.Inject

/**
 * Created by bherbert on 11/20/17.
 */
class FirebaseLoginActivity : AppCompatActivity() {
    val RC_LOGIN = 1;

    lateinit var mFirebaseLoginViewModel: FirebaseLoginViewModel

    @Inject
    lateinit var userRepo : IUserRepo

    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.base_activity)

        DaggerUserComponent.builder().userRepoModule(UserRepoModule()).build().inject(this);
        Log.v("blarg", "repo name : " + userRepo.getName())

        // TODO: Make generic
        mFirebaseLoginViewModel = ViewModelProviders.of(this).get(FirebaseLoginViewModel::class.java)
        mFirebaseLoginViewModel.firebaseUserLiveData.observe(this, Observer<FirebaseUser> { user -> onUserChanged(user) })
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.v("blarg", "on result " + requestCode + ":" + resultCode)

        if (requestCode == RC_LOGIN && resultCode == Activity.RESULT_OK) {
            onLoggedIn()
        } else {
            onLoggedOut()
        }
    }

    fun onUserChanged(user: FirebaseUser?) {
        if (user == null) onLoggedOut() else onLoggedIn()
    }

    fun onLoggedOut() {
        lblMessage.text = "Not logged in"
        btnLoginOrOut.text = "Log in"
        btnLoginOrOut.setOnClickListener({
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(Arrays.asList(AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                    .build(),
                    RC_LOGIN)
        })
    }

    fun onLoggedIn() {
        lblMessage.text = FirebaseAuth.getInstance().currentUser!!.displayName
        btnLoginOrOut.text = "Log out"
        btnLoginOrOut.setOnClickListener({
            FirebaseAuth.getInstance().signOut()
            onLoggedOut()
        })
    }
}
