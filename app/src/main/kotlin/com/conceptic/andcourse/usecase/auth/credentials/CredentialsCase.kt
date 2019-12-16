package com.conceptic.andcourse.usecase.auth.credentials

import com.conceptic.andcourse.data.api.ApiExecutorFactory
import com.conceptic.andcourse.data.model.Credentials
import com.conceptic.andcourse.data.model.Gender
import com.conceptic.andcourse.data.model.Role
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.coroutineScope
import java.util.*

class CredentialsCase(
    executorFactory: ApiExecutorFactory
) : UseCase<Unit, Credentials> {
    private val authApiExecutor = executorFactory.authExecutor()

    override suspend fun execute(param: Unit): Credentials = coroutineScope {
        val response = authApiExecutor.credentials()
        Credentials(
            id = response.id,
            mail = response.mail,
            gender = Gender.of(response.gender),
            role = Role.of(response.role),
            dateBirth = Date(response.dateBirth),
            dateRegistered = Date(response.dateRegistered)
        )
    }
}