package com.mrright.realm_example.domain.realm_repos

import com.mrright.realm_example.models.Student
import kotlinx.coroutines.flow.Flow

interface RealmRepository {
    suspend fun insertStudent(student: Student): Boolean
}