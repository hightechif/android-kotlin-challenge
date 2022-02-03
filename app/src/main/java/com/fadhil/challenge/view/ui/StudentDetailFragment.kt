package com.fadhil.challenge.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fadhil.challenge.R
import com.fadhil.challenge.constant.Gender
import com.fadhil.challenge.data.entities.Student
import com.fadhil.challenge.databinding.FragmentStudentDetailBinding
import com.fadhil.challenge.viewmodels.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentDetailFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentStudentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var spinner: Spinner
    private lateinit var selectedGender: Gender
    private var genderOption = arrayOf(Gender.MALE, Gender.FEMALE)
    private lateinit var student: Student
    private val viewModel: StudentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_student_detail, container, false)
        student = Student(
            arguments?.getInt("student_id")!!,
            arguments?.getString("student_name")!!,
            arguments?.getSerializable("student_gender") as Gender,
            arguments?.getFloat("student_gpa")!!,
        )
        binding.student = student
        binding.selected = genderOption.indexOf(student.gender)
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
        binding.btnUpdateStudent.setOnClickListener {
            updateStudent()
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.etStudentName.text.toString(),
            genderOption[binding.spnStudentGender.selectedItemPosition].toString(),
            binding.etStudentGpa.text.toString()
        )
    }

    private fun updateStudent() {
        if (isEntryValid()) {
            viewModel.updateStudent(
                student.id,
                binding.etStudentName.text.toString(),
                genderOption[binding.spnStudentGender.selectedItemPosition],
                binding.etStudentGpa.text.toString().toFloat(),
            )
        }
        val action =
            StudentDetailFragmentDirections.actionStudentDetailFragmentToAllStudentsFragment()
        findNavController().navigate(action)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedGender = genderOption[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        selectedGender = arguments?.getSerializable("student_gender") as Gender
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}