package com.am.sarojselfcorrect

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonVH>() {

    private val list = mutableListOf<MistakeEntity>()

    fun submitList(newList: List<MistakeEntity>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lesson, parent, false)
        return LessonVH(view)
    }

    override fun onBindViewHolder(holder: LessonVH, position: Int) {
        val item = list[position]

        // ✅ Lesson text safe handling
        holder.tvLesson.text = if (!item.lesson.isNullOrBlank()) {
            item.lesson
        } else {
            "No lesson added"
        }

        // ✅ Timestamp safe handling
        val time = item.date.takeIf { it > 0 } ?: System.currentTimeMillis()

        holder.tvDate.text = DateUtils.getRelativeTimeSpanString(
            time,
            System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS
        )
    }

    override fun getItemCount() = list.size

    class LessonVH(v: View) : RecyclerView.ViewHolder(v) {
        val tvLesson: TextView = v.findViewById(R.id.tvLesson)
        val tvDate: TextView = v.findViewById(R.id.tvDate)
    }
}