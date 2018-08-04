package com.vepe.navigation.ui.login

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.vepe.navigation.R
import com.vepe.navigation.presentation.login.LoginViewModel
import com.vepe.navigation.ui.main.MainActivityArgs
import kotlinx.android.synthetic.main.frg_login.*


class LoginFragment: Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.frg_login, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        loginButton.setOnClickListener{ login(loginUsernameText.text.toString(), loginPasswordText.text.toString()) }
    }

    private fun login(username: String?, password: String?) {
        val valid = viewModel.areValidCredentials(username, password)
        if (valid) {
            // call finish so login activity won't show up after back button clicked in home fragment
            activity?.finish()
            navController.navigate(R.id.action_start_main,
                    MainActivityArgs.Builder()
                            .setUsername(username ?: "anonym")
                            .build()
                            .toBundle())
        }
        else Toast.makeText(context, getString(R.string.invalid_credentials_message), Toast.LENGTH_SHORT).show()
    }
}