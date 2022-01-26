package com.fadhil.challenge.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        binding.btnAddStudent.setOnClickListener {
            val toast =
                Toast.makeText(context?.applicationContext, "Button Clicked", Toast.LENGTH_SHORT)
            toast.show()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rvStudent
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val studentAdapater = StudentAdapter {
            val action =
                AllStudentsFragmentDirections.actionFullScheduleFragmentToAddStudentFragment()
            view.findNavController().navigate(action)
        }
        recyclerView.adapter = studentAdapater

        // submitList() is a call that accesses the database. To prevent the
        // call from potentially locking the UI, you should use a
        // coroutine scope to launch the function. Using GlobalScope is not
        // best practice, and in the next step we'll see how to improve this.
        lifecycle.coroutineScope.launch {
            viewModel.allStudents().collect() {
                studentAdapater.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}