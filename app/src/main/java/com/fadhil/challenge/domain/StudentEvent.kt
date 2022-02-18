package com.fadhil.challenge.domain

sealed class StudentEvent {
    object ShowStudentList: StudentEvent()
}
