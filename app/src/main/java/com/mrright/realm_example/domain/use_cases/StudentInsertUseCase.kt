package com.mrright.realm_example.domain.use_cases


import com.mrright.realm_example.domain.realm_repos.RealmRepository
import com.mrright.realm_example.models.Student
import com.mrright.realm_example.utils.validateAddress
import com.mrright.realm_example.utils.validateName
import com.mrright.realm_example.utils.validateStandard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.Executors
import javax.inject.Inject

class StudentInsertUseCase @Inject constructor(
    private val realmRepo: RealmRepository
) {

    suspend operator fun invoke(
        name: String,
        std: String,
        address: String,
    ): Flow<InsertResult> = flow {

        val nameErr = name.validateName()
        val stdErr = std.validateStandard()
        val addressErr = address.validateAddress()

        if (nameErr != null || stdErr != null || addressErr != null) {
            emit(
                InsertResult.FieldVal(
                    nameErr = nameErr,
                    stdErr = stdErr,
                    addressErr = addressErr,
                )
            )
            return@flow
        } else {
            emit(InsertResult.FieldVal())
        }

        val student = Student().apply {
            this.id = UUID.randomUUID().toString()
            this.name = name.trim()
            this.std = std.trim()
            this.address = address.trim()
        }

        val result = withContext(Dispatchers.IO) {
            realmRepo.insertStudent(student)
        }

        delay(1000L)
        emit(if (result) InsertResult.Success else InsertResult.Ex("Failed"))

    }

    companion object {
        private val TAG = StudentInsertUseCase::class.java.simpleName
    }

}

sealed class InsertResult {
    object Success : InsertResult()
    data class Ex(val ex: String) : InsertResult()
    data class FieldVal(
        val nameErr: String? = null,
        val stdErr: String? = null,
        val addressErr: String? = null,
    ) : InsertResult()
}