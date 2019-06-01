package com.vepe.navigation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vepe.navigation.R
import com.vepe.navigation.presentation.main.MainViewModel
import kotlinx.android.synthetic.main.frg_main.view.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.frg_main, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
            setRecyclerItemsObserver()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set fab listener to start an action
        view.addNewButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_create, null))

        recycler = view.recycler
        setupRecycler()
    }

    private fun setRecyclerItemsObserver() {
        viewModel.getItems().observe(this, Observer { list ->
            list?.let {
                (recycler.adapter as? HomeItemAdapter)?.submitList(it)
            }
        })
    }

    private fun setupRecycler() {
        recycler.layoutManager = LinearLayoutManager(context)
        val itemDecor = DividerItemDecoration(context, RecyclerView.VERTICAL)
        recycler.addItemDecoration(itemDecor)
        recycler.adapter = HomeItemAdapter(viewModel)
        viewModel.prepareItems()
    }
}
