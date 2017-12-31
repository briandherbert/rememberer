package com.burningaltar.rememberer

import android.arch.lifecycle.MutableLiveData

/**
 * Created by bherbert on 12/27/17.
 */
class StaticUserRepo : IUserRepo {
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