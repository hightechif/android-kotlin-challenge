package com.fadhil.challenge.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.challenge.MainApplication
import com.fadhil.challenge.adapter.StudentAdapter
import com.fadhil.challenge.databinding.FragmentAllStudentsBinding
import com.fadhil.challenge.viewmodels.StudentViewModel
import com.fadhil.challenge.viewmodels.StudentViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class AllStudentsFragment : Fragment() {

    private var _binding: FragmentAllStudentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    private val viewModel: StudentViewModel by activityViewModels {
        StudentViewModelFactory(
            (activity?.application as MainApplication).database.studentDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllStudentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddStudent.setOnClickListener {
            val action = AllStudentsFragmentDirections.actionAllStudentsFragmentToAddStudentFragment()
            view.findNavController().navigate(action)
        }

        binding.btnDeleteAllStudents.setOnClickListener {
            lifecycle.coroutineScope.launch {
                viewModel.deleteAll()
            }
        }

        recyclerView = binding.rvStudent
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val studentAdapter = StudentAdapter {
            val bundle = Bundle()
            bundle.putInt("student_id", it.id)
            bundle.putString("student_name", it.name)
            bundle.putSerializable("student_gender", it.gender)
            bundle.putFloat("student_gpa", it.gpa)
            val action = AllStudentsFragmentDirections.actionAllStudentsFragmentToStudentDetailFragment()
            view.findNavController().navigate(action.actionId, bundle)
        }
        recyclerView.adapter = studentAdapter

        // submitList() is a call that accesses the database. To prevent the
        // call from potentially locking the UI, you should use a
        // coroutine scope to launch the function. Using GlobalScope is not
        // best practice, and in the next step we'll see how to improve this.
        lifecycle.coroutineScope.launch {
            viewModel.allStudents().collect() {
                studentAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}