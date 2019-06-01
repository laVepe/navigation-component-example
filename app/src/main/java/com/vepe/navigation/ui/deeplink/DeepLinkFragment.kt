package com.vepe.navigation.ui.deeplink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vepe.navigation.R
import kotlinx.android.synthetic.main.frg_deeplink.*


class DeepLinkFragment : Fragment() {

    private lateinit var message: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        message = arguments?.let {
            DeepLinkFragmentArgs.fromBundle(it).deepLinkMessage
        } ?: "No message"
        return inflater.inflate(R.layout.frg_deeplink, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deepLinkMessage.text = message
    }
}