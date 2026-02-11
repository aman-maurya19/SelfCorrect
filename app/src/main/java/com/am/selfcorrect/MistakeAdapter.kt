package com.am.selfcorrect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
class MistakeAdapter :
    ListAdapter<MistakeEntity, MistakeAdapter.MistakeViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MistakeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mistake, parent, false)
        return MistakeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MistakeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MistakeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvLesson = itemView.findViewById<TextView>(R.id.tvLesson)

        fun bind(item: MistakeEntity) {
            tvTitle.text = item.title
            tvLesson.text = item.lesson
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MistakeEntity>() {
        override fun areItemsTheSame(oldItem: MistakeEntity, newItem: MistakeEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MistakeEntity, newItem: MistakeEntity): Boolean {
            return oldItem == newItem
        }
    }
}

