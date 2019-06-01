package com.vepe.navigation.ui.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.vepe.navigation.R
import com.vepe.navigation.presentation.main.MainViewModel


class PremiumContentFragment : Fragment() {

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)

            if (!viewModel.hasPremium) findNavController().navigate(R.id.action_condition_not_met)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.frg_premium_content, container, false)
    }
}