package com.fadhil.challenge.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.fadhil.challenge.MainApplication
import com.fadhil.challenge.R
import com.fadhil.challenge.constant.Gender
import com.fadhil.challenge.data.source.local.entity.Student
import com.fadhil.challenge.databinding.FragmentStudentInsertionBinding
import com.fadhil.challenge.viewmodels.StudentViewModel
import com.fadhil.challenge.viewmodels.StudentViewModelFactory

class StudentInsertionFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentStudentInsertionBinding? = null
    private val binding get() = _binding!!
    private lateinit var spinner: Spinner
    private lateinit var selectedGender: Gender
    private var genderOption = arrayOf(Gender.MALE, Gender.FEMALE)

    private val viewModel: StudentViewModel by activityViewModels {
        StudentViewModelFactory(
            (activity?.application as MainApplication).database
                .studentDao()
        )
    }

    lateinit var student: Student

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentInsertionBinding.inflate(inflater, container, false)
        spinner = binding.spnStudentGender
        spinner.onItemSelectedListener = this
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSaveStudent.setOnClickListener {
            addNewStudent()
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.etStudentName.text.toString(),
            selectedGender.toString(),
            binding.etStudentGpa.text.toString()
        )
    }

    private fun addNewStudent() {
        if (isEntryValid()) {
            viewModel.addNewStudent(
                binding.etStudentName.text.toString(),
                selectedGender,
                binding.etStudentGpa.text.toString().toFloat(),
            )
        }
        val action = StudentInsertionFragmentDirections.actionAddStudentFragmentToAllStudentsFragment()
        findNavController().navigate(action)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedGender = genderOption[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        selectedGender = Gender.MALE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}