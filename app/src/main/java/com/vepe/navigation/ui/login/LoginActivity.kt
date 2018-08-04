package com.vepe.navigation.ui.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.findNavController
import com.vepe.navigation.R


class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_login)
    }

    // back button support
    override fun onSupportNavigateUp() = this.findNavController(R.id.my_nav_host_login_fragment).navigateUp()
}