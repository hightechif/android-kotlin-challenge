package com.fadhil.challenge.ui.student.addedit

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
import com.fadhil.challenge.databinding.FragmentStudentAddEditBinding
import com.fadhil.challenge.ui.student.StudentEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentAddEditFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentStudentAddEditBinding? = null
    private val binding get() = _binding!!
    private lateinit var spinner: Spinner
    private lateinit var selectedGender: Gender
    private var genderOption = arrayOf(Gender.MALE, Gender.FEMALE)
    private val viewModel: StudentAddEditViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_add_edit, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinner = binding.spnStudentGender
        spinner.onItemSelectedListener = this

        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        val studentEvent = arguments?.getString("student_event")!!
        onEvent(studentEvent)
    }

    private fun onEvent(event: String) {
        when (event) {
            StudentEvent.ADD_NEW_STUDENT -> {
                binding.btnSaveStudent.visibility = View.VISIBLE
                binding.btnUpdateStudent.visibility = View.GONE
                binding.btnSaveStudent.setOnClickListener {
                    addNewStudent()
                }
            }
            StudentEvent.EDIT_STUDENT -> {
                binding.btnSaveStudent.visibility = View.GONE
                binding.btnUpdateStudent.visibility = View.VISIBLE
                val id = arguments?.getInt("student_id")!!
                viewModel.getStudent(id).observe(viewLifecycleOwner) {
                    binding.student = it
                    binding.selected = genderOption.indexOf(binding.student?.gender)
                }
                binding.btnUpdateStudent.setOnClickListener {
                    updateStudent(id)
                }
            }
            else -> {

            }
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.etStudentName.text.toString(),
            genderOption[binding.spnStudentGender.selectedItemPosition].toString(),
            binding.etStudentGpa.text.toString()
        )
    }

    private fun addNewStudent() {
        if (isEntryValid()) {
            viewModel.addNewStudent(
                binding.etStudentName.text.toString(),
                genderOption[binding.spnStudentGender.selectedItemPosition],
                binding.etStudentGpa.text.toString().toFloat(),
            )
        }
        findNavController().navigateUp()
    }

    private fun updateStudent(id: Int) {
        if (isEntryValid()) {
            viewModel.updateStudent(
                id,
                binding.etStudentName.text.toString(),
                genderOption[binding.spnStudentGender.selectedItemPosition],
                binding.etStudentGpa.text.toString().toFloat(),
            )
        }
        findNavController().navigateUp()
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