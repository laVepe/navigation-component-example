package com.vepe.navigation.ui.home

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.vepe.navigation.R
import com.vepe.navigation.ui.detail.DetailFragmentArgs


class HomeItemAdapter : ListAdapter<String, HomeItemAdapter.ViewHolder>(HomeItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_main, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.item_title)

        fun bind(item: String) {
            title.text = item
            view.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_detail,
                            DetailFragmentArgs.Builder()
                                    .setTitle(item)
                                    .build()
                                    .toBundle()))
        }
    }

    class HomeItemDiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String?, newItem: String?): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: String?, newItem: String?): Boolean = oldItem == newItem
    }
}