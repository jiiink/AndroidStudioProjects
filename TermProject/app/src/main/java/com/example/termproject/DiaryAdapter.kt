package com.example.termproject

import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DiaryAdapter(private val activity: AppCompatActivity) : ListAdapter<Diary, DiaryAdapter.DiaryViewHolder>(DiaryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_diary, parent, false)
        return DiaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val diary = getItem(position)
        holder.bind(diary)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ViewActivity::class.java).apply {
                putExtra("diaryId", diary.id)
                putExtra("title", diary.title)
                putExtra("content", diary.content)
                putExtra("date", diary.date)
                putExtra("image", diary.image)
            }
            activity.startActivityForResult(intent, MainActivity.VIEW_REQUEST_CODE)
        }
    }

    class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val diaryImageView: ImageView = itemView.findViewById(R.id.diaryImageView)

        fun bind(diary: Diary) {
            titleTextView.text = diary.title
            dateTextView.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(diary.date))
            diary.image?.let {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                diaryImageView.setImageBitmap(bitmap)
            }
        }
    }

    private class DiaryDiffCallback : DiffUtil.ItemCallback<Diary>() {
        override fun areItemsTheSame(oldItem: Diary, newItem: Diary): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Diary, newItem: Diary): Boolean {
            return oldItem == newItem
        }
    }
}
