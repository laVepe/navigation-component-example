package com.vepe.navigation.presentation.login

import android.arch.lifecycle.ViewModel


class LoginViewModel: ViewModel() {

    fun areValidCredentials(username: String?, password: String?): Boolean {
        return username != null && password != null && username.length > 4 && password.length > 4
    }
}