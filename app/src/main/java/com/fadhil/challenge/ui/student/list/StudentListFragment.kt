package com.fadhil.challenge.ui.student.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.challenge.databinding.FragmentStudentListBinding
import com.fadhil.challenge.domain.model.Student
import com.fadhil.challenge.ui.student.StudentAdapter
import com.fadhil.challenge.ui.student.StudentAdapterDelegate
import com.fadhil.challenge.ui.student.StudentEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentListFragment : Fragment() {

    private var _binding: FragmentStudentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private val viewModel: StudentListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddStudent.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("student_event", StudentEvent.ADD_NEW_STUDENT)
            val action = StudentListFragmentDirections.actionStudentListFragmentToStudentAddEditFragment()
            view.findNavController().navigate(action.actionId, bundle)
        }


        recyclerView = binding.rvStudent
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val studentAdapter = StudentAdapter(this::onItemViewClicked)
        recyclerView.adapter = studentAdapter

        loadData(studentAdapter)

        studentAdapter.setOnDeleteCallback(object : StudentAdapterDelegate {
            override fun onItemClicked(data: Student) {
                loadData(studentAdapter)
                viewModel.deleteOne(data.id)
            }
        })

        binding.btnDeleteAllStudents.setOnClickListener {
            viewModel.deleteAll()
        }
    }

    private fun loadData(adapter: StudentAdapter) {
        // submitList() is a call that accesses the database. To prevent the
        // call from potentially locking the UI, you should use a
        // coroutine scope to launch the function. Using GlobalScope is not
        // best practice, and in the next step we'll see how to improve this.
        viewModel.getStudents().observe(viewLifecycleOwner) {
            adapter.submitList(it ?: mutableListOf())
        }
    }

    private fun onItemViewClicked(it: Student) {
        val bundle = Bundle()
        bundle.putString("student_event", StudentEvent.EDIT_STUDENT)
        bundle.putLong("student_id", it.id)
        val action = StudentListFragmentDirections.actionStudentListFragmentToStudentAddEditFragment()
        view?.findNavController()?.navigate(action.actionId, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}