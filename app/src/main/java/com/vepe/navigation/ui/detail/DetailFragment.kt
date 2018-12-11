package com.vepe.navigation.ui.detail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vepe.navigation.R
import com.vepe.navigation.model.Category
import com.vepe.navigation.model.Item
import com.vepe.navigation.presentation.detail.DetailViewModel
import kotlinx.android.synthetic.main.frg_detail.view.*


class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.frg_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val safeArgs = DetailFragmentArgs.fromBundle(it)
            val item: Item? = safeArgs.item
            val category: Category = safeArgs.category
            val lastSeenItems: Array<Item>? = safeArgs.list

            item?.let {
                var lastSeenItemsNames = ""
                lastSeenItems?.forEach { lastSeenItemsNames += it.title + "\n" }
                view.detail_text.text = context?.getString(R.string.detail_message,
                        it.index, it.title, it.value, category.name, lastSeenItemsNames)
            }
        }
    }
}
