package com.burningaltar.rememberer

import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by bherbert on 11/29/17.
 */
class FirebaseLoginFragment : Fragment() {
    val RC_LOGIN = 1;

    lateinit var mProviders: EnumSet<AuthProvider>

    enum class AuthProvider(val authName: String) {
        GOOGLE(AuthUI.GOOGLE_PROVIDER)
    }

    companion object {
        var KEY_PROVIDERS = "providers"

        fun loginWith(providers: EnumSet<AuthProvider>, fragmentManager: FragmentManager) {
            var loginFragment = FirebaseLoginFragment()
            var args = Bundle()
            args.putSerializable(KEY_PROVIDERS, providers)

            fragmentManager.beginTransaction().add(loginFragment, FirebaseLoginFragment.javaClass.simpleName).commitAllowingStateLoss()
        }
    }

    val firebaseUserLiveData: MutableLiveData<FirebaseUser> = MutableLiveData()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mProviders = arguments.getSerializable(KEY_PROVIDERS) as EnumSet<AuthProvider>
        var providers: ArrayList<String> = ArrayList()

        for (provider in mProviders) providers.add(provider.authName)

        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(Arrays.asList(AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                .build(),
                RC_LOGIN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (RC_LOGIN == requestCode) {
            firebaseUserLiveData.value = FirebaseAuth.getInstance().currentUser
        }
    }
}