package com.fadhil.challenge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.challenge.data.source.local.entity.Student
import com.fadhil.challenge.databinding.ItemRowStudentBinding
import com.fadhil.challenge.view.callback.StudentDeleteOneCallback

class StudentAdapter(private val onItemClicked: (Student) -> Unit, private val studentList: MutableList<Student>) : ListAdapter<Student, StudentAdapter.StudentViewHolder>(DiffCallback) {

    lateinit var onDeleteOneClicked: StudentDeleteOneCallback

    fun setOnDeleteCallback(callback: StudentDeleteOneCallback) {
        this.onDeleteOneClicked = callback
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class StudentViewHolder(private var binding: ItemRowStudentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student, position: Int) {
            binding.tvItemId.text = student.id.toString()
            binding.tvItemName.text = student.name
            binding.tvItemGender.text = student.gender.toString()
            binding.tvItemGpa.text = student.gpa.toString()
            binding.btnDeleteStudent.setOnClickListener {
                onDeleteOneClicked.onItemClicked(studentList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val viewHolder = StudentViewHolder(
            ItemRowStudentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

}