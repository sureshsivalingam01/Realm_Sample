package com.mrright.realm_example.data.realm.repositories

import android.util.Log
import com.mrright.realm_example.domain.realm_repos.RealmRepository
import com.mrright.realm_example.models.Daos
import com.mrright.realm_example.models.Student
import io.realm.Realm
import javax.inject.Inject

class RealmRepoImpl @Inject constructor(
    private val dao: Daos,
) : RealmRepository {

    companion object {

        val TAG: String = RealmRepoImpl::class.java.simpleName

        private lateinit var realm: Realm

    }

    override suspend fun insertStudent(student: Student): Boolean {

        return try {
            realm = Realm.getDefaultInstance()

            dao.student.insert(realm, student)
            Log.i(TAG, "insertStudent || Success")
            true
        } catch (e: Exception) {
            Log.e(TAG, "insertStudent || Failed", e)
            false
        } finally {
            realm.close()
        }
    }

}