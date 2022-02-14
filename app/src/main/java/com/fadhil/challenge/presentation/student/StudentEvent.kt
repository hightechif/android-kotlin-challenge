package com.fadhil.challenge.presentation.student

sealed class StudentEvent {
    companion object {
        const val EDIT_STUDENT = "EDIT_STUDENT"
        const val ADD_NEW_STUDENT = "ADD_NEW_STUDENT"
    }
}