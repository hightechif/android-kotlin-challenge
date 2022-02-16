package com.fadhil.challenge.utils

import com.fadhil.challenge.data.source.local.entity.StudentEntity
import com.fadhil.challenge.domain.model.Student

object DataMapperStudent {

    fun mapStudentToDomain(input: StudentEntity) =
        Student(
            id = input.id,
            name = input.name,
            gender = input.gender,
            gpa = input.gpa
        )

    fun mapStudentsToDomain(input: List<StudentEntity>) =
        input.map {
            mapStudentToDomain(it)
        }

    fun mapStudentToEntity(input: Student) =
        StudentEntity(
            id = input.id,
            name = input.name,
            gender = input.gender,
            gpa = input.gpa
        )

}