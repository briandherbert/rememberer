package com.burningaltar.rememberer.CoreUser

import android.app.Application
import android.arch.lifecycle.MutableLiveData

/**
 * Created by bherbert on 12/27/17.
 */
class StaticUserRepo(app : Application) : UserRepo(app) {
    override fun login(creds: Array<String>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun logout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserData(): MutableLiveData<User> {
        var userData : MutableLiveData<User> = MutableLiveData()
        userData.value = User("bob")
        return userData
    }

    override fun notifyUpdated() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getName(): String {
        return "static user repo"
    }

}