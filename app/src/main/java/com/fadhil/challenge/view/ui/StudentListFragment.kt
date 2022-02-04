package com.fadhil.challenge.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.challenge.data.entities.Student
import com.fadhil.challenge.databinding.FragmentStudentListBinding
import com.fadhil.challenge.view.adapter.StudentAdapter
import com.fadhil.challenge.view.callback.StudentDeleteOneCallback
import com.fadhil.challenge.viewmodels.StudentListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudentListFragment : Fragment() {

    private var _binding: FragmentStudentListBinding? = null
    private val binding get() = _binding!!
    private var list: MutableList<Student> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private val viewModel: StudentListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStudentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddStudent.setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentListFragmentToStudentInsertionFragment()
            view.findNavController().navigate(action)
        }

        binding.btnDeleteAllStudents.setOnClickListener {
            viewModel.deleteAll()
        }

        recyclerView = binding.rvStudent
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val studentAdapter = StudentAdapter(this::onItemViewClicked, list)
        recyclerView.adapter = studentAdapter

        studentAdapter.setOnDeleteCallback(object : StudentDeleteOneCallback {
            override fun onItemClicked(data: Student) {
                viewModel.deleteOne(data)
            }
        })

        // submitList() is a call that accesses the database. To prevent the
        // call from potentially locking the UI, you should use a
        // coroutine scope to launch the function. Using GlobalScope is not
        // best practice, and in the next step we'll see how to improve this.
        lifecycle.coroutineScope.launch {
            subscribeUi(studentAdapter)
        }
    }

    private fun subscribeUi(adapter: StudentAdapter) {
        viewModel.studentsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun onItemViewClicked(it: Student) {
        val bundle = Bundle()
        bundle.putInt("student_id", it.id)
        bundle.putString("student_name", it.name)
        bundle.putSerializable("student_gender", it.gender)
        bundle.putFloat("student_gpa", it.gpa)
        val action = StudentListFragmentDirections.actionStudentListFragmentToStudentEditFragment()
        view?.findNavController()?.navigate(action.actionId, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}