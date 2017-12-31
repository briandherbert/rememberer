package com.burningaltar.rememberer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ProgressBar
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
    val TAG : String = "FirebaseLoginActivity"
    val RC_LOGIN = 1;

    @Inject
    lateinit var mUserRepo: UserRepo   // We are the source of the repo's data since FirebaseAuth uses onActivityResult

    public override fun onCreate(bundle: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(bundle)
        setContentView(ProgressBar(this))

        Log.v(TAG, "Logging in w Firebase")
        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(Arrays.asList(AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                .build(),
                RC_LOGIN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.v(TAG, "Firebase login result " + resultCode)

        mUserRepo.notifyUpdated()   // Anyone can get the FirebaseAuth user from its singleton, but we'll just tell the repo to check it.
        finish()
    }
}
