package com.fadhil.challenge.domain.usecase.session

import com.fadhil.challenge.domain.repository.ISessionRepository

class SessionInteractor(
    private val sessionRepository: ISessionRepository
) : SessionUseCase {
    override fun login(username: String, password: String) =
        sessionRepository.login(username, password)

    override fun logout() =
        sessionRepository.logout()
}