package com.vepe.navigation.ui.premium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.vepe.navigation.R
import com.vepe.navigation.presentation.main.MainViewModel
import kotlinx.android.synthetic.main.frg_premium_step1.view.*


class PremiumStep1Fragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.frg_premium_step1, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.premiumButton.setOnClickListener {
            findNavController().navigate(R.id.action_premium_next)
        }
    }
}