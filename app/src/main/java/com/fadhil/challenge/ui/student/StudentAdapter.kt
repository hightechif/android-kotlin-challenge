package com.fadhil.challenge.ui.student

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.challenge.databinding.ItemRowStudentBinding
import com.fadhil.challenge.domain.model.Student

class StudentAdapter(private val onItemClicked: (Student) -> Unit) : ListAdapter<Student, StudentAdapter.StudentViewHolder>(
    DiffCallback
) {

    lateinit var onDeleteOneClicked: StudentAdapterDelegate

    fun setOnDeleteCallback(callback: StudentAdapterDelegate) {
        this.onDeleteOneClicked = callback
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldStudent: Student, newStudent: Student): Boolean {
                return oldStudent.id == newStudent.id
            }

            override fun areContentsTheSame(oldStudent: Student, newStudent: Student): Boolean {
                return oldStudent == newStudent
            }
        }
    }

    inner class StudentViewHolder(private var binding: ItemRowStudentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            binding.tvItemId.text = student.id.toString()
            binding.tvItemName.text = student.name
            binding.tvItemGender.text = student.gender.toString()
            binding.tvItemGpa.text = student.gpa.toString()
            binding.btnDeleteStudent.setOnClickListener {
                onDeleteOneClicked.onItemClicked(student)
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
        holder.bind(getItem(position))
    }

}